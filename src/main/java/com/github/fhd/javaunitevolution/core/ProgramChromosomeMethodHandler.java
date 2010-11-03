package com.github.fhd.javaunitevolution.core;

import java.lang.reflect.*;

import javassist.util.proxy.*;

import org.jgap.gp.impl.*;

/**
 * A method handler that invokes the supplied GP program chromosome.
 */
class ProgramChromosomeMethodHandler implements MethodHandler {
    ProgramChromosome chromosome;
    Method methodToInvoke;

    /**
     * Creates a new method handler.
     * @param program The GP program to invoke.
     * @param methodToInvoke The method whose invocations are processed by this
     *                       handler.
     */
    public ProgramChromosomeMethodHandler(ProgramChromosome chromosome,
                                            Method methodToInvoke) {
        this.chromosome = chromosome;
        this.methodToInvoke = methodToInvoke;
    }

    @Override
    public Object invoke(Object self, Method thisMethod,
                         Method proceed, Object[] args) throws Throwable {
        if (!methodToInvoke.equals(thisMethod))
            throw new RuntimeException("Test case invoked method "
                                       + thisMethod.toString()
                                       + " which was not subject to "
                                       + "evolution.");

        Class<?> returnType = thisMethod.getReturnType();

        if (PrimitiveUtils.isBoolean(returnType))
            return chromosome.execute_boolean(args);
        else if (PrimitiveUtils.isDouble(returnType))
            return chromosome.execute_double(args);
        else if (PrimitiveUtils.isFloat(returnType))
            return chromosome.execute_float(args);
        else if (PrimitiveUtils.isInteger(returnType))
            return chromosome.execute_int(args);
        else if (PrimitiveUtils.isVoid(returnType)) {
            chromosome.execute_void(args);
            return null;
        } else
            return chromosome.execute_object(args);
    }
}
