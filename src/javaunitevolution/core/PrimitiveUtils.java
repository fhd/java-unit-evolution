package javaunitevolution.core;

import org.jgap.gp.CommandGene;

public class PrimitiveUtils {
    public static boolean isBoolean(Class<?> clazz) {
        return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
    }

    public static boolean isCharacter(Class<?> clazz) {
        return Character.class.equals(clazz) || char.class.equals(clazz);
    }

    public static boolean isDouble(Class<?> clazz) {
        return Double.class.equals(clazz) || double.class.equals(clazz);
    }

    public static boolean isFloat(Class<?> clazz) {
        return Float.class.equals(clazz) || float.class.equals(clazz);
    }

    public static boolean isInteger(Class<?> clazz) {
        return Integer.class.equals(clazz) || int.class.equals(clazz);
    }

    public static boolean isLong(Class<?> clazz) {
        return Long.class.equals(clazz) || long.class.equals(clazz);
    }

    public static boolean isVoid(Class<?> clazz) {
        return Void.class.equals(clazz) || void.class.equals(clazz);
    }

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
