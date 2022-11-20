package aufgabe4_2;

class Sinus implements FunctionStrategy {
    public double fvonx(final double x) {
        return Math.sin(x);
    }

    public double fstrichvonx(final double x) {
        return Math.cos(x);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Sin(x)";
    }
}
