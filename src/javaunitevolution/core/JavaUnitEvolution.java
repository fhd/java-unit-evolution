package javaunitevolution.core;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.log4j.Logger;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

public class JavaUnitEvolution {
	static IGPProgram currentProgram;
	private static Logger LOGGER = Logger.getLogger(JavaUnitEvolution.class);
	private static GPGenotype gp;
	private static Method methodToEvolve;
	private static boolean finished = false;
	private static int timeout = 300000;
	private static int populationSize = 500;
	private static int maxInitDepth = 6;
	private static int maxCrossoverDepth = 17;
	private static int maxInitialNodes = 20; // XXX: Why is this necessary?
	
	public static <T> T evolve(Class<T> classToEvolve, Class<?> testClass) {
		if (gp == null) {
			// This is executed if the evolutionary process has not yet begun.
			methodToEvolve = null;
			for (Method method: classToEvolve.getMethods()) {
				methodToEvolve = method;
				break;
			}
			if (methodToEvolve == null)
				throw new RuntimeException("Unable to find a method suitable" +
						"for evolution on " + classToEvolve);
			try {
				GPProblem problem = new GenericProblem(
						createConfig(testClass), methodToEvolve);
				gp = problem.create();
				gp.setVerboseOutput(true);
				evolveProgram();
				finished = true;
			} catch (InvalidConfigurationException e) {
				throw new RuntimeException("Invalid configuration", e);
			}
		}

		if (!finished)
			// This is the implementation returned to each test case
			// during the fitness function's execution.
			return createImplementation(currentProgram, classToEvolve);

		// This is the final implementation, capable of passing all unit tests. 
		// TODO: Store the final implementation somewhere
		return createImplementation(gp.getAllTimeBest(), classToEvolve);
	}
	
	private static GPConfiguration createConfig(Class<?> testClass)
			throws InvalidConfigurationException {		
		GPConfiguration config = new GPConfiguration();
		config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
		config.setPopulationSize(populationSize);
		config.setMaxInitDepth(maxInitDepth);
		config.setMaxCrossoverDepth(maxCrossoverDepth);
		config.setFitnessFunction(new GenericFitnessFunction(testClass));
		config.setStrictProgramCreation(true);
		return config;
	}
	
	private static void evolveProgram()
			throws InvalidConfigurationException {
		LOGGER.info("Evolving an implementation of " + methodToEvolve +
				", this might take a while... (Timeout: " + timeout + " ms)");
		EvolutionThread et = new EvolutionThread(gp);
		TimeoutThread tt = new TimeoutThread(timeout, et);
		et.start();
		tt.start();
		try {
			et.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("Unexpected thread interruption", e);
		}
		tt.interrupt();
		if (et.isFinished())
			LOGGER.info("Program evolved.");
		else
			LOGGER.info("Unable to evolve a workable program within the " +
					"specified amount of time. Try increasing the timeout" +
					"or supplying more test cases.");
	}

	private static <T> T createImplementation(IGPProgram program,
			Class<T> clazz) {
		return clazz.cast(Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz },
				new GPProgramInvocationHandler(program, methodToEvolve)));		
	}

	public static void setTimeout(int timeout) {
		JavaUnitEvolution.timeout = timeout;
	}

	public static int getTimeout() {
		return timeout;
	}
	
	public static void setPopulationSize(int populationSize) {
		JavaUnitEvolution.populationSize = populationSize;
	}

	public static int getPopulationSize() {
		return populationSize;
	}

	public static void setMaxInitDepth(int maxInitDepth) {
		JavaUnitEvolution.maxInitDepth = maxInitDepth;
	}

	public static int getMaxInitDepth() {
		return maxInitDepth;
	}

	public static void setMaxCrossoverDepth(int maxCrossoverDepth) {
		JavaUnitEvolution.maxCrossoverDepth = maxCrossoverDepth;
	}

	public static int getMaxCrossoverDepth() {
		return maxCrossoverDepth;
	}

	public static void setMaxInitialNodes(int maxInitialNodes) {
		JavaUnitEvolution.maxInitialNodes = maxInitialNodes;
	}

	public static int getMaxInitialNodes() {
		return maxInitialNodes;
	}
}