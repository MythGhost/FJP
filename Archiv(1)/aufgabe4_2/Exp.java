package aufgabe4_2;

class Exp implements FunctionStrategy {
    public double fvonx(final double x) {
        return Math.exp(x);
    }

    public double fstrichvonx(final double x) {
        return Math.exp(x);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Exp(x)";
    }
}