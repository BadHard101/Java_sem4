package PR7.Decorator;

// Интерфейс, который будет расширен декораторами
interface Component {
    void operation();
}

// Конкретная реализация интерфейса Component
class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("Вызван метод operation() в классе ConcreteComponent.");
    }
}

// Базовый класс декоратора
abstract class Decorator implements Component {
    private Component component;
    
    public Decorator(Component component) {
        this.component = component;
    }
    
    public void operation() {
        component.operation();
    }
}

// Конкретный декоратор #1
class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
    }
    
    public void operation() {
        super.operation();
        System.out.println("Вызван метод operation() в классе ConcreteDecorator1.");
    }
}

// Конкретный декоратор #2
class ConcreteDecorator2 extends Decorator {
    public ConcreteDecorator2(Component component) {
        super(component);
    }
    
    public void operation() {
        super.operation();
        System.out.println("Вызван метод operation() в классе ConcreteDecorator2.");
    }
}

// Тестирование декораторов
public class DecoratorDemo {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decorator1 = new ConcreteDecorator1(component);
        Component decorator2 = new ConcreteDecorator2(decorator1);
        decorator2.operation(); // вызов метода operation() через цепочку декораторов
    }
}
