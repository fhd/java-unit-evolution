package javaunitevolution.core;

public class TimeoutThread extends Thread {
    private int timeout;
    private Thread thread;

    public TimeoutThread(int timeout, Thread thread) {
        this.timeout = timeout;
        this.thread = thread;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void run() {
        try {
            Thread.sleep(timeout);
            thread.stop();
        } catch (InterruptedException e) {
            // This may happen, it's OK
        }
    }
}
