package com.github.fhd.javaunitevolution.core;

/**
 * A thread that will wait for the specified time, keeping track of the elapsed
 * time.
 */
class Timeout extends Thread {
    private int passed;
    private int total;
    private boolean finished;

    /**
     * Creates a new timeout.
     * @param total The time (in milliseconds) to wait.
     */
    public Timeout(int total) {
        this.total = total;
    }

    @Override
    public void run() {
        try {
            for (passed = 0; passed < total; passed++)
                Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException("Unexpected exception while waiting on "
                                       + "thread", e);
        } finally {
            finished = true;
        }
    }

    /**
     * @return <code>true</code> if the specified time has passed.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @return The number of milliseconds that have passed since the thread
     *         started to wait.
     */
    public int getPassed() {
        return passed;
    }

    /**
     * @return The total number of milliseconds that the thread will wait.
     */
    public int getTotal() {
        return total;
    }
}
