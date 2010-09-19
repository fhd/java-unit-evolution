package de.ubercode.javaunitevolution.samples.adding;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

import de.ubercode.javaunitevolution.core.*;

/**
 * The test case that determines the fitness of {@link Adding}.
 */
@RunWith(Parameterized.class)
public class AddingTest {	
    private Adding adding = JavaUnitEvolution.evolve(Adding.class, getClass());
    private int a, b;

    @Parameters
    public static Collection<Object[]> numbers() {
        return Arrays.asList(new Object[][] {{ -849296252, -906988962 },
                                             { -1781465939, 990915249 },
                                             { -1117546399, 1742618736 },
                                             { 172873648, -1621628303 },
                                             { -18757947, -1440516941 },
                                             { -121778737, -971907160 },
                                             { -413554375, -461485640 },
                                             { -1579778366, 1173041371 },
                                             { 1700162360, 1540247662 },
                                             { 2138766818, -1846206534 }});
    }

    public AddingTest(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Test
    public void add() {
        assertEquals(a + b, adding.add(a, b));
    }
}
