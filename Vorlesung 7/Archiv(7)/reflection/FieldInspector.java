package reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/*
 * 1. Definition of Student, Address + variable scopes
 * 2. Execution with getFields
 * 3. Execution with getDeclaredFields
 */
class FieldInspector {
    static void inspectAttributes(Object obj) throws IllegalArgumentException, IllegalAccessException {
        // start point
        Class<?> cls = obj.getClass();

        // 1. all public attributes with getFields inclusive superclass (guess?)
        // 2. everything in this class with getDeclaredFields
        Field[] fields = cls.getFields();
//        Field[] fields = cls.getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true); // access private attributes
            Object val = f.get(obj);

            if (Modifier.isStatic(f.getModifiers())) // only non-static attributes
                continue;

            if (f.getType().isPrimitive() || // primitive data type or String
                    f.getType().getName().equals("java.lang.String"))
                System.out.println(f.getType() + ": " + f.getName() + " " + val);
            else if (f.getType().isArray()) // array data type is special
            {
                Object array = f.get(obj);
                int length = Array.getLength(array);
                System.out.println("Array: " + f.getName() + ": ");
                for (int i = 0; i < length; i++) {
                    System.out.println("Array[" + i + "]" + " = " + Array.get(array, i));
                }
            } else {
                // call recursive on member attributes
                inspectAttributes(val);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Student student = new Student("Claire", "Werk",
                new Address("Lohengrinstr.", 11, 72729, "Neustadt"), 7, 83939L);
        System.out.println(student + "\n");

        inspectAttributes(student);
    }
}