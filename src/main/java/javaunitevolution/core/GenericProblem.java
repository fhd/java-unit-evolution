package javaunitevolution.core;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;

class GenericProblem extends GPProblem {
    private Method methodToEvolve;
    private List<Method> operations;

    public GenericProblem(GPConfiguration config, Method methodToEvolve,
                          List<Method> operations)
        throws InvalidConfigurationException {
        super(config);
        this.methodToEvolve = methodToEvolve;
        this.operations = operations;
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {
        // Generate operands from the method's interface
        Class<?>[] types = {
                PrimitiveUtils.toCommandGene(methodToEvolve.getReturnType())
        };
        
        List<Class<?>> argTypesList = new LinkedList<Class<?>>();
        for (Class<?> argType: methodToEvolve.getParameterTypes())
            argTypesList.add(PrimitiveUtils.toCommandGene(argType));

        Class<?>[][] argTypes = new Class<?>[][] {
                argTypesList.toArray(new Class<?>[argTypesList.size()])
        };

        // Generate operations from interface
        GPConfiguration config = getGPConfiguration();
        List<CommandGene> nodeSetOperations = new LinkedList<CommandGene>();
        for (Method operation: operations)
            nodeSetOperations.add(new GenericCommand(config, operation));
        
        CommandGene[][] nodeSets = { nodeSetOperations.toArray(
                new CommandGene[nodeSetOperations.size()])};

        return GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, JavaUnitEvolution.getMaxInitialNodes(), true);
    }
}
