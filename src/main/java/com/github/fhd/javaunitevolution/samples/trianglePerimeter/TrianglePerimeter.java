package com.github.fhd.javaunitevolution.samples.trianglePerimeter;

/**
 * This sample aims to evolve a method that will calculate the perimeter of a
 * triangle to demonstrate the basics of Java Unit Evolution.
 * <p>
 * The algorithm will simply have to add all the sides together. It can use the
 * following arithmetic methods:
 * <ul>
 * <li>Addition
 * <li>Subtraction
 * <li>Multiplication
 * </ul>
 */
public abstract class TrianglePerimeter {
    /**
     * Calculates the perimeter of a triangle from its sides.
     * @param a One side of the triangle.
     * @param b One side of the triangle.
     * @param c One side of the triangle.
     * @return The perimeter of the triangle.
     */
    public abstract int perimeter(int a, int b, int c);

    // TODO: The operations should each have only two parameters.

    public static int operationAdd(int a, int b, int c) {
        return a + b + c;
    }

    public static int operationSubtract(int a, int b, int c) {
        return a - b - c;
    }

    public static int operationMultiply(int a, int b, int c) {
        return a * b * c;
    }
}
