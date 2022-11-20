package strategy;

public class SortingClientWithLambda {
    public void bubbleSort(double[] list) {
        System.out.println("BubbleSort in SortingClient");

        double temp;
        for (int i = 0; i < list.length; i++) {
            for (int j = i; j < list.length; j++) {
                if (list[i] > list[j]) {
                    temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        SortingClientWithLambda sc = new SortingClientWithLambda();

        double[] list = {1, 2.4, 7.9, 3.2, 1.2, 0.2, 10.2, 22.5, 19.6, 14, 12, 16, 17};
        SortingContext context = new SortingContext();

        // now start with sorting algorithm bubble sort as lambda per parameter
//    context.setSorter( new BubbleSort() );
        context.setSorter((double[] list2) -> {
            double temp;
            System.out.println("BubbleSortLambda");
            for (int i = 0; i < list2.length; i++) {
                for (int j = i; j < list2.length; j++) {
                    if (list2[i] > list2[j]) {
                        temp = list2[i];
                        list[i] = list2[j];
                        list2[j] = temp;
                    }
                }
            }
        });
        context.sortDouble(list);

        // now set sorting algorithm to external quick sort
        context.setSorter(new QuickSort()::sort);
        context.sortDouble(list);

        SortStrategy ss = new BubbleSort();
        context.setSorter(ss::sort);
        context.sortDouble(list);

        context.setSorter(sc::bubbleSort);
        context.sortDouble(list);
//    for( double d : list )
//    {
//      System.out.println( d );
//    }
    }
}
