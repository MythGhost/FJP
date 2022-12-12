package reflection;

public class FooImpl implements Foo {
    public Object bar(Object obj) throws Exception {
        System.out.println("Original method bar, returns foo");
        return "foo";
    }
}
