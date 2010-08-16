package javaunitevolution.samples.adding;

public abstract class Adding {
    public abstract int add(int a, int b);
    
    public static int operationAdd(int a, int b) {
        return a + b;
    }
    
    public static int operationSubtract(int a, int b) {
        return a - b;
    }
    
    public static int operationMultiply(int a, int b) {
        return a * b;
    }
}
