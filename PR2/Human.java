package PR2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Human{
    private Integer age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Integer weight;

    public Human(String firstName, String lastName, LocalDate birthDate, Integer weight) {
        this.age = (int)ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public Human setAge(int years) {
        this.birthDate = birthDate.minusYears(years);
        this.age = (int)ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Human{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", weight=" + weight +
                '}';
    }
}
