package de.ubercode.javaunitevolution.core;

import org.jgap.gp.*;

/**
 * Utility methods to work with Java primitives.
 */
class PrimitiveUtils {
    /**
     * Checks whether the supplied class is a boolean type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is a boolean type,
     *         either primitive or object.
     */
    public static boolean isBoolean(Class<?> clazz) {
        return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    /**
     * Checks whether the supplied class is a character type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is a character type,
     *         either primitive or object.
     */
    public static boolean isCharacter(Class<?> clazz) {
        return Character.class.equals(clazz) || char.class.equals(clazz);
    }

    /**
     * Checks whether the supplied class is a double type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is a double type, either
     *         primitive or object.
     */
    public static boolean isDouble(Class<?> clazz) {
        return Double.class.equals(clazz) || double.class.equals(clazz);
    }
    
    /**
     * Checks whether the supplied class is a float type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is a float type, either
     *         primitive or object.
     */
    public static boolean isFloat(Class<?> clazz) {
        return Float.class.equals(clazz) || float.class.equals(clazz);
    }

    /**
     * Checks whether the supplied class is an integer type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is an integer type,
     *         either primitive or object.
     */
    public static boolean isInteger(Class<?> clazz) {
        return Integer.class.equals(clazz) || int.class.equals(clazz);
    }

    /**
     * Checks whether the supplied class is a long type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is a long type, either
     *         primitive or object.
     */
    public static boolean isLong(Class<?> clazz) {
        return Long.class.equals(clazz) || long.class.equals(clazz);
    }

    /**
     * Checks whether the supplied class is a void type.
     * @param clazz The class to check.
     * @return <code>true</code> if the supplied class is a void type, either
     *         primitive or object.
     */
    public static boolean isVoid(Class<?> clazz) {
        return Void.class.equals(clazz) || void.class.equals(clazz);
    }

    /**
     * Finds the respective {@link CommandGene} type for the supplied class. 
     * @param clazz The class to check. Should be a primitive type or one of
     *              the respective wrapper objects.
     * @return The {@link CommandGene} type that matches the supplied class.
     *         <code>null</code> if the class was not a primitive type.
     */
    static Class<?> toCommandGene(Class<?> clazz) {
        if (isBoolean(clazz))
            return CommandGene.BooleanClass;
        else if (isCharacter(clazz))
            return CommandGene.CharacterClass;
        else if (isDouble(clazz))
            return CommandGene.DoubleClass;
        else if (isFloat(clazz))
            return CommandGene.FloatClass;
        else if (isInteger(clazz))
            return CommandGene.IntegerClass;
        else if (isLong(clazz))
            return CommandGene.LongClass;
        else if (isVoid(clazz))
            return CommandGene.VoidClass;
        return null;
    }
}
