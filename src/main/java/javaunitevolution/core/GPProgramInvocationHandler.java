package javaunitevolution.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.jgap.gp.IGPProgram;

class GPProgramInvocationHandler implements InvocationHandler {

    IGPProgram program;
    Method methodToInvoke;

    public GPProgramInvocationHandler(IGPProgram program,
                                      Method methodToInvoke) {
        this.program = program;
        this.methodToInvoke = methodToInvoke;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        if (!methodToInvoke.equals(method))
            throw new RuntimeException("Test case invoked method "
                                       + method.toString()
                                       + " which was not subject to "
                                       + "evolution.");

        Class<?> returnType = method.getReturnType();

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