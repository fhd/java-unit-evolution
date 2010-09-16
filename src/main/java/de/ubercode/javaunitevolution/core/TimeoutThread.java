package de.ubercode.javaunitevolution.core;

/**
 * A thread that waits for a given time and then stop the supplied thread.
 */
public class TimeoutThread extends Thread {
    private int timeout;
    private Thread thread;

    /**
     * Creates a new thread that will watch the supplied thread and stop it
     * once the supplied time runs out.
     * @param timeout The time (in milliseconds) to wait before stopping the
     *                thread.
     * @param thread The thread to stop once the time runs out.
     */
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
