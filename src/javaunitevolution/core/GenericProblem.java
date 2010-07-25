package javaunitevolution.core;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

class GenericProblem extends GPProblem {
	private Method methodToEvolve;
	
	public GenericProblem(GPConfiguration config, Method methodToEvolve)
			throws InvalidConfigurationException {
		super(config);
		this.methodToEvolve = methodToEvolve;
	}

	@Override
	public GPGenotype create() throws InvalidConfigurationException {
		// Generate operands from the method's interface
		Class<?>[] types = { classToCommandGene(methodToEvolve.getReturnType()) };
		List<Class<?>> argTypesList = new LinkedList<Class<?>>();
		for (Class<?> argType: methodToEvolve.getParameterTypes())
			argTypesList.add(classToCommandGene(argType));
		Class<?>[][] argTypes = new Class<?>[][] {
			argTypesList.toArray(new Class<?>[argTypesList.size()])
		};
		
		// TODO: Generate operations from interface
		GPConfiguration config = getGPConfiguration();
		CommandGene[][] nodeSets = {{
				new Add(config, CommandGene.IntegerClass),
				new Subtract(config, CommandGene.IntegerClass),
				new Multiply(config, CommandGene.IntegerClass)
		}};

		return GPGenotype.randomInitialGenotype(config, types, argTypes,
				nodeSets, JavaUnitEvolution.getMaxInitialNodes(), true);
	}
	
	private static Class<?> classToCommandGene(Class<?> clazz) {
		if (PrimitiveUtils.isBoolean(clazz))
			return CommandGene.BooleanClass;
		else if (PrimitiveUtils.isCharacter(clazz))
			return CommandGene.CharacterClass;
		else if (PrimitiveUtils.isDouble(clazz))
			return CommandGene.DoubleClass;
		else if (PrimitiveUtils.isFloat(clazz))
			return CommandGene.FloatClass;
		else if (PrimitiveUtils.isInteger(clazz))
			return CommandGene.IntegerClass;
		else if (PrimitiveUtils.isLong(clazz))
			return CommandGene.LongClass;
		else if (PrimitiveUtils.isVoid(clazz))
			return CommandGene.VoidClass;
		return null;
	}
}
