package proxy;

public class Proxy implements Subject { // mit Decorator-Pattern bereichert
    private Subject impl;
    private int invocationCount = 0;

    public Proxy() {
        this.impl = new RealSubject();
    }

    public void meth1() {
        impl.meth1();
    }

    public void meth2() {
        // zus. Funktionalitaet (Decorator)
        this.invocationCount++;
        // Delegation
        impl.meth2();
    }

    // Decoration
    public int getInvocationCount() {
        return this.invocationCount;
    }
}
