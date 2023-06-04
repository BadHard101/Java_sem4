package PR1;

import java.util.Arrays;

public class Main {
    @FunctionalInterface
    public interface Comparator<T> {
        int[] compare(T o1, T o2);
    }
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 5};
        int[] b = {1, 3, 2, 4};

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int[] compare(int[] o1, int[] o2) {
                if (o1.length >= o2.length)
                    return o1;
                else
                    return o2;
            }
        };

        System.out.println(Arrays.toString(comparator.compare(a, b)));
    }
}