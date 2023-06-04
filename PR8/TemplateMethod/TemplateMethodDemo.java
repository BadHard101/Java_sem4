package PR8.TemplateMethod;

// Абстрактный класс с шаблонным методом
abstract class AbstractClass {
    public void templateMethod() {
        // Шаг 1
        step1();
        
        // Шаг 2
        step2();
        
        // Шаг 3
        step3();
    }
    
    protected abstract void step1();
    
    protected abstract void step2();
    
    protected void step3() {
        System.out.println("Выполнение шага 3");
    }
}

// Конкретный класс, который наследует абстрактный класс
class ConcreteClass extends AbstractClass {
    protected void step1() {
        System.out.println("Выполнение шага 1");
    }
    
    protected void step2() {
        System.out.println("Выполнение шага 2");
    }
    
    protected void step3() {
        System.out.println("Выполнение шага 3 (измененная реализация)");
    }
}

// Тестирование шаблонного метода
public class TemplateMethodDemo {
    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClass();
        abstractClass.templateMethod();
    }
}
