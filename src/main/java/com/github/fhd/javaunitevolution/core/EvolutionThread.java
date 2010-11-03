package com.github.fhd.javaunitevolution.core;

import java.util.concurrent.locks.*;

import org.apache.log4j.*;
import org.jgap.gp.*;
import org.jgap.gp.impl.*;

/**
 * The thread that will perform the evolution.
 */
class EvolutionThread extends Thread {
    private static Logger logger = Logger.getLogger(EvolutionThread.class);
    private GPGenotype gp;
    private boolean finished = false;
    // XXX: Find a more elegant locking solution
    private Lock finishedLock = new ReentrantLock();

    /**
     * Creates an evolution thread.
     * @param gp The genotype responsible for the evolution.
     */
    public EvolutionThread(GPGenotype gp) {
        this.gp = gp;
    }

    @Override
    public void run() {
        double bestFitness = Double.MAX_VALUE;
        for (int generation = 1; bestFitness > 0.0; generation++) {
            gp.evolve(1);
            IGPProgram bestProgram = gp.getAllTimeBest();
            bestFitness = bestProgram != null ? bestProgram.getFitnessValue()
                                              : 0;
            logger.info("Generation: " + generation
                        + ". Best fitness: " + bestFitness);
        }

        finishedLock.lock();
        finished = true;
        finishedLock.unlock();
    }

    /**
     * @return <code>true</code> if the evolution has ended.
     */
    public boolean isFinished() {
        finishedLock.lock();
        boolean b = finished;
        finishedLock.unlock();
        return b;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        finishedLock.lock();
        finished = true;
    }
}