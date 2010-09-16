package de.ubercode.javaunitevolution.core;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPGenotype;

public class EvolutionThread extends Thread {
    private static Logger logger = Logger.getLogger(EvolutionThread.class);
    private GPGenotype gp;
    private boolean finished = false;
    // OPTME: Find a more elegant locking solution
    private Lock finishedLock = new ReentrantLock();

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