package PR2;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    List<Human> people;

    public List<Human> getPeople() {
        return people;
    }

    public Main() {
        people = new ArrayList<>();
        people.add(new Human(
                "Michael",
                "Myers",
                LocalDate.of(2003, Month.JULY, 13),
                81)
        );
        people.add(new Human(
                "John",
                "Griffin",
                LocalDate.of(2001, Month.SEPTEMBER, 15),
                75)
        );
        people.add(new Human(
                "Jose",
                "Graham",
                LocalDate.of(2002, Month.JANUARY, 16),
                79)
        );

        people.add(new Human(
                "John",
                "Stephens",
                LocalDate.of(2004, Month.AUGUST, 22),
                72)
        );
    }

//    8) Фильтрация по возрасту больше чем 20,
//      сортировка по последней букве имени,
//      увеличение возраста каждого на 3,
//      вычисление среднего возраста всех элементов.
    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("---DATABASE-----------------------");
        Stream<Human> stream0 = main.getPeople().stream();
        stream0.forEach(System.out::println);

        System.out.println("---FILTERING-----------------------");
        Stream<Human> stream1 = main.getPeople().stream();
        stream1.filter(o -> o.getAge() > 20).forEach(System.out::println);

        System.out.println("---SORTING-----------------------");
        Stream<Human> stream2 = main.getPeople().stream();
        stream2.sorted(Comparator.comparingInt(
                o -> o.getFirstName().charAt(o.getFirstName().length()-1)
        )).forEach(System.out::println);

        // just forEach
        System.out.println("---INCREASE BY 3 (just forEach)-----------------------");
        Stream<Human> stream3 = main.getPeople().stream();
        stream3.forEach(human -> System.out.println(human.setAge(3)) );

        // by map
        System.out.println("---INCREASE BY 3 (by map)-----------------------");
        stream3 = main.getPeople().stream();
        stream3.map(human -> human.setAge(3)).forEach(System.out::println);

        // print (age + 3)
        System.out.println("---INCREASE BY 3 (just age+3)-----------------------");
        stream3 = main.getPeople().stream();
        stream3.mapToInt(Human::getAge).forEach((age) -> System.out.println(age + 3));


        System.out.println("---AVERAGE AGE-----------------------");
        Stream<Human> stream4 = main.getPeople().stream();
        System.out.println(stream4.mapToInt(Human::getAge).average().getAsDouble());
        //System.out.println(stream4.mapToInt(Human::getAge).sum() / main.getPeople().size());

    }
}
