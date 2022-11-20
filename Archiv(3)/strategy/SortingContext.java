package strategy;

public class SortingContext {
    private SortStrategy sorter = null;

    public void sortDouble(double[] list) {
        sorter.sort(list);
    }

    public SortStrategy getSorter() {
        return sorter;
    }

    public void setSorter(SortStrategy sorter) {
        this.sorter = sorter;
    }
}