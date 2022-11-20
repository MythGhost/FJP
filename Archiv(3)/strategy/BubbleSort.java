package strategy;

public class BubbleSort implements SortStrategy {
    @Override
    public void sort(double[] list) {
        System.out.println("BubbleSort");

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
}