package com.ewyboy.game;

import com.ewyboy.game.utillity.GameInfo;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

@SuppressWarnings("serial")
public class GameLauncher extends Applet {

    private static GameLoop game = new GameLoop();

    @Override
    public void init() {
        setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
        setMaximumSize(GameInfo.Window.DIMENSION);
        setMinimumSize(GameInfo.Window.DIMENSION);
        setPreferredSize(GameInfo.Window.DIMENSION);
    }

    @Override
    public void start() {
        game.start();
    }

    @Override
    public void stop() {
        game.stop();
    }

    public static void main(String[] args) {
        game.setMaximumSize(GameInfo.Window.DIMENSION);
        game.setMinimumSize(GameInfo.Window.DIMENSION);
        game.setPreferredSize(GameInfo.Window.DIMENSION);

        game.frame = new JFrame(GameInfo.Info.NAME);

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLayout(new BorderLayout());

        game.frame.add(game, BorderLayout.CENTER);
        game.frame.pack();

        game.frame.setResizable(false);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
