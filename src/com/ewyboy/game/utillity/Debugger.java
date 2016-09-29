package com.ewyboy.game.utillity;

/**
 * Created by EwyBoy
 **/
public class Debugger {

    public enum DebugLevel {
        INFO, WARNING, SEVERE
    }

    public void debug(DebugLevel level, String msg) {
        switch (level) {
            case INFO: System.out.println("[" + GameInfo.Info.NAME + "] " + msg);
                break;
            case WARNING: System.out.println("[" + GameInfo.Info.NAME + "] [WARNING] " + msg);
                break;
            case SEVERE: System.out.println("[" + GameInfo.Info.NAME + "] [SEVERE]" + msg);
                break;
        }
    }
}
