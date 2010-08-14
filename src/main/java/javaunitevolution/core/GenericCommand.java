package javaunitevolution.core;

import java.lang.reflect.Method;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

// TODO: Proper error handling
public class GenericCommand extends CommandGene {
    private static final long serialVersionUID = 1L;
    private Method operation;
    
    public GenericCommand(GPConfiguration conf, Method operation)
            throws InvalidConfigurationException {
        super(conf, operation.getParameterTypes().length,
              PrimitiveUtils.toCommandGene(operation.getReturnType()));
        this.operation = operation;
    }
    
    @Override
    public Object execute(ProgramChromosome c, int n, Object[] args) {
        return execute_object(c, n, args);
    }
    
    @Override
    public boolean execute_boolean(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isBoolean(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            return (Boolean) operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public double execute_double(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isDouble(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            return (Double) operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public float execute_float(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isFloat(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            return (Float) operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isInteger(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            return (Integer) operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public long execute_long(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isLong(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            return (Long) operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public Object execute_object(ProgramChromosome c, int n, Object[] args) {
        try {
            return (Object) operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public void execute_void(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isInteger(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            operation.invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return operation.getName();
    }
}
