package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeComplexMethods {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        Class cls = Complex.class;

        Constructor ctor;
//    Constructor[] ctors = cls.getDeclaredConstructors();
//    ctor = ctors[0];

        // get constructor via reflection and call it
        ctor = cls.getDeclaredConstructor(double.class, double.class);
        System.out.println(ctor);
        ctor.setAccessible(true); // call private constructor!!!

        Complex c = (Complex) ctor.newInstance(-1.0, 1.0);
        Complex d = (Complex) ctor.newInstance(-1.0, 1.0);

        // is the same as
        // Complex c = new Complex(-1.0, 1.0);
        // Complex d = new Complex(1.0, -1.0);

        // get method via reflection and call it
        Method add = cls.getMethod("add", Complex.class);
        Complex sum = (Complex) add.invoke(c, d);
        System.out.println(sum);

        // is the same as
        // Complex sum = c.add(d);
    }
}
