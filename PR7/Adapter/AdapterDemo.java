package PR7.Adapter;

// Интерфейс, который должен быть адаптирован
interface Target {
    void request();
}

// Адаптируемый класс
class Adaptee {
    public void specificRequest() {
        System.out.println("Вызван метод specificRequest() адаптируемого класса.");
    }
}

// Адаптер класса Adaptee к интерфейсу Target
class Adapter implements Target {
    private Adaptee adaptee;
    
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    
    public void request() {
        adaptee.specificRequest();
    }
}

// Тестирование адаптера
public class AdapterDemo {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request(); // вызов метода request() через адаптер
    }
}
