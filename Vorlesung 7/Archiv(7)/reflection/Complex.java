package reflection;

public final class Complex {
    private final double re;
    private final double im;

    private Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public static Complex valueOf(double re, double im) {
        return new Complex(re, im);
    }

    public Complex add(Complex c) {
        return valueOf(this.re + c.re, this.im + c.im);
    }

    public Complex mult(Complex c) {
        return valueOf(this.re * c.re - this.im * c.im, this.re * c.im + this.im * c.re);
    }

    public Complex mult(double a) {
        return valueOf(this.re * a, this.im * a);
    }

    public String toString() {
        return "(" + this.re + ", " + this.im + ")";
    }

    @SuppressWarnings("unused")
    private String intern() {
        return "intern";
    }
}
