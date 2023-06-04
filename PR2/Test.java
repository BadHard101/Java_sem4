package PR2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("My");
        input.add("Authors");
        input.add("Haruki Murakami");
        input.add("Franz Kafka");
        input.add("Charles Bukowski");
        Stream<String> stream = input.stream();
        stream.filter(str -> str.length() > 10)
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);

        int sum = Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> a + 2);// = 25
        System.out.println(sum);
    }
}
