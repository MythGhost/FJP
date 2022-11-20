package strategy;

public class SortingClient {
    public static void main(String[] args) {
        double[] list = {1, 2.4, 7.9, 3.2, 1.2, 0.2, 10.2, 22.5, 19.6, 14, 12, 16, 17};
        SortingContext context = new SortingContext();
    context.setSorter( new BubbleSort() );
//        context.setSorter(new QuickSort());

        context.sortDouble(list);

        for (double d : list) {
            System.out.println(d);
        }
    }
}
