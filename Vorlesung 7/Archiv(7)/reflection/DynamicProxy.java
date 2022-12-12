package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * http://java.sun.com/j2se/1.3/docs/guide/reflection/proxy.html
 * <p>
 * A dynamic proxy class is a class that implements a list of interfaces specified at runtime
 * such that a method invocation through one of the interfaces on an instance of the class
 * will be encoded and dispatched to another object through a uniform interface.
 */
public class DynamicProxy implements InvocationHandler {
    private final Object obj;

    private DynamicProxy(Object obj) {
        this.obj = obj;
    }

    // from interface InvocationHandler, will be called from dynamic proxy later as interceptor
    // for every method
    @Override
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        // here we can do what we want with all intercepted methods!!!
        Object result;
        try {
            System.out.println("before method " + m.getName());
            result = m.invoke(obj, args);     // original method call
            System.out.println("Result = " + result);
        } catch (InvocationTargetException e) {
            throw e.getCause();
//      throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
            System.out.println("after method " + m.getName());
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Foo fooOrg = new FooImpl();

        // nearly all Object methods and all interface methods of a selected object can be catched
        Foo fooWithProxy = (Foo) Proxy.newProxyInstance(fooOrg.getClass().getClassLoader(),
                fooOrg.getClass().getInterfaces(), new DynamicProxy(fooOrg));

        // call bar via proxy instance and intercept
        Object f = fooWithProxy.bar(null);
        System.out.println();

        // works also with Object.toString() method
        System.out.println("Call toString(): " + fooWithProxy.toString());
    }

}
