package aufgabe4_2;

class Cosinus implements FunctionStrategy {
    public double fvonx(final double x) {
        return Math.cos(x);
    }

    public double fstrichvonx(final double x) {
        return - Math.sin(x);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Cos(x)";
    }
}

