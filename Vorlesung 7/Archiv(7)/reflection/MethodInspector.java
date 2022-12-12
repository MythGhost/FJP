package reflection;

import java.lang.reflect.Method;

class MethodInspector {
    static void inspectMethods(Object obj) {
        Class<?> cls = obj.getClass();

        // Which methods do we get?
        Method[] methods = cls.getMethods();
//        Method[] methods = cls.getDeclaredMethods();

        for (Method m : methods) {
            System.out.println(m); // cheap version ;-)

            System.out.println("Name: " + m.getName());
            Class<?>[] paramTypes = m.getParameterTypes();
            System.out.print("  Parameter:");
            for (Class<?> c : paramTypes)
                System.out.print(" (" + c + ")");
            System.out.println();
            System.out.println("  Returns: " + m.getReturnType() + "\n");
        }
    }


    public static void main(String[] args) {
        Complex c = Complex.valueOf(-2.7, Math.PI);
        inspectMethods(c);
    }
}
