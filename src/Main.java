public class Main {

    //Game Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = (1000 / 60);

    public static void main(String[] args) {
        System.out.println("");
    }

    public void threadInit() {
        super.notifyAll();
        if(thread.equals(null)) thread = new Thread((Runnable) this);
            thread.start();
    }

    public static void gameLoop() {

    }

    public static void graphicLoop() {

    }
}
