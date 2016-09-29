package com.ewyboy.game;

import com.ewyboy.game.handlers.TickHandler;
import com.ewyboy.game.utillity.Debugger;
import com.ewyboy.game.utillity.GameInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameLoop extends Canvas implements Runnable {

    private Thread thread;
    public JFrame frame;
    public static GameLoop gameLoop;
    public boolean running = false;

    Debugger debugger = new Debugger();
    TickHandler tickHandler = new TickHandler();

    private int ticks;
    private int frames;

    public void init() {
        gameLoop = this;
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        ticks = 0;
        frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        init();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                frames++;
                tickHandler.tick();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                ticks++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                debugger.debug(Debugger.DebugLevel.INFO, "FPS: " + frames + " | " + "Ticks: " + ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.drawString("FPS: 60", 20, 20);

        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, GameInfo.Info.NAME + "_main");
        thread.start();
    }

    public synchronized void stop() {
        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
