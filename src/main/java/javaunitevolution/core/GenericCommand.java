package javaunitevolution.core;

import java.lang.reflect.Method;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

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
        // TODO: Implement this
        return null;
    }
    
    @Override
    public boolean execute_boolean(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isBoolean(operation.getReturnType()))
            throw new UnsupportedOperationException();
        // TODO: Implement this
        return false;
    }
    
    @Override
    public double execute_double(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isDouble(operation.getReturnType()))
            throw new UnsupportedOperationException();
        // TODO: Implement this
        return 0;
    }
    
    @Override
    public float execute_float(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isFloat(operation.getReturnType()))
            throw new UnsupportedOperationException();
        // TODO: Implement this
        return 0;
    }
    
    @Override
    public int execute_int(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isInteger(operation.getReturnType()))
            throw new UnsupportedOperationException();
        try {
            return (Integer) operation.invoke(null, args);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    
    @Override
    public long execute_long(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isLong(operation.getReturnType()))
            throw new UnsupportedOperationException();
        // TODO: Implement this
        return 0;
    }
    
    @Override
    public Object execute_object(ProgramChromosome c, int n, Object[] args) {
        // TODO: Implement this
        return null;
    }
    
    @Override
    public void execute_void(ProgramChromosome c, int n, Object[] args) {
        if (!PrimitiveUtils.isInteger(operation.getReturnType()))
            throw new UnsupportedOperationException();
        // TODO: Implement this
    }
    
    @Override
    public String toString() {
        return operation.getName();
    }
}
