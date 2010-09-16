package de.ubercode.javaunitevolution.samples.triangle;

import static org.junit.Assert.*;

import org.junit.*;

import de.ubercode.javaunitevolution.core.*;

/**
 * The test case that determines the fitness of {@link TrianglePerimeter}.
 * Just execute this in a test runner to evolve an implementation.
 */
public class TrianglePerimeterTest {
    private TrianglePerimeter trianglePerimeter =
        JavaUnitEvolution.evolve(TrianglePerimeter.class, getClass());

    @Test
    public void trianglePerimeter1() {
        assertEquals(trianglePerimeter.perimeter(9, 2, 7), 18);
    }
    
    @Test
    public void trianglePerimeter2() {
        assertEquals(trianglePerimeter.perimeter(3, 99, 38), 140);
    }
}
