package de.ubercode.javaunitevolution.core;

import java.lang.reflect.*;

import javassist.util.proxy.*;

import org.jgap.gp.*;

/**
 * A method handler that invokes the supplied GP program.
 */
class GPProgramMethodHandler implements MethodHandler {
    IGPProgram program;
    Method methodToInvoke;

    /**
     * Creates a new method handler.
     * @param program The GP program to invoke.
     * @param methodToInvoke The method whose invocations are processed by this
     *                       handler.
     */
    public GPProgramMethodHandler(IGPProgram program,
                                  Method methodToInvoke) {
        this.program = program;
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
            return program.execute_boolean(0, args);
        else if (PrimitiveUtils.isDouble(returnType))
            return program.execute_double(0, args);
        else if (PrimitiveUtils.isFloat(returnType))
            return program.execute_float(0, args);
        else if (PrimitiveUtils.isInteger(returnType))
            return program.execute_int(0, args);
        else if (PrimitiveUtils.isVoid(returnType)) {
            program.execute_void(0, args);
            return null;
        } else
            return program.execute_object(0, args);
    }

}
