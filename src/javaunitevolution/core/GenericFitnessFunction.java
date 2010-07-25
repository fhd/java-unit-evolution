package javaunitevolution.core;

import org.apache.log4j.Logger;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

class GenericFitnessFunction extends GPFitnessFunction {
	private static Logger LOGGER = Logger.getLogger(
			GenericFitnessFunction.class);
	private static final long serialVersionUID = 1L;
	private Class<?> testClass;
	
	public GenericFitnessFunction(Class<?> testClass) {
		this.testClass = testClass;
	}

	@Override
	protected double evaluate(IGPProgram subject) {
		JavaUnitEvolution.currentProgram = subject;
		Result result = JUnitCore.runClasses(testClass);
		if (result.getFailureCount() == 0)
			return 0.0;
		double delta = 0.0;
		for (Failure failure: result.getFailures()) {
			Throwable t = failure.getException();
			if (t instanceof AssertionError)
				delta += extractDelta((AssertionError) t);
		}
		LOGGER.debug("Calculated delta: " + delta);
		return delta;
	}
	
	/**
	 * Extracts the delta between the two values of an error.
	 * @param error the error from which to extract the delta.
	 * @return the calculated delta.
	 */
	private double extractDelta(AssertionError error) {
		// XXX: Make this more fault tolerant
		String message = error.getMessage();
		LOGGER.debug("Extracting assertion message: " + message);
		String expected = message.substring(
				message.indexOf("<") + 1, message.indexOf(">"));
		String actual = message.substring(
				message.lastIndexOf("<") + 1, message.lastIndexOf(">"));
		try {
			return Math.abs(Double.valueOf(expected) -
					Double.valueOf(actual));
		} catch (NumberFormatException e1) {
			try {
				return Math.abs(Float.valueOf(expected) -
						Float.valueOf(actual));
			} catch (NumberFormatException e2) {
				try {
					return Math.abs(Integer.valueOf(expected) -
							Integer.valueOf(actual));
				} catch (NumberFormatException e3) {
					// TODO: Try other datatypes
					return 0.0;
				}
			}
		}
	}
}
