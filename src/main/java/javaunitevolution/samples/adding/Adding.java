package javaunitevolution.samples.adding;

public interface Adding {
    int add(int a, int b);
    
    static class Operations {
        static int add(int a, int b) {
            return a + b;
        }
        
        static int subtract(int a, int b) {
            return a - b;
        }
        
        static int multiply(int a, int b) {
            return a * b;
        }   
    }
}
