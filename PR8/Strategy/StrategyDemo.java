package PR8.Strategy;

// Интерфейс стратегии
interface Strategy {
    int execute(int a, int b);
}

// Конкретная стратегия #1
class ConcreteStrategy1 implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Выполнение стратегии #1");
        return a + b; // сложение чисел
    }
}

// Конкретная стратегия #2
class ConcreteStrategy2 implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Выполнение стратегии #2");
        return a - b; // вычитание чисел
    }
}

// Контекст, который использует стратегию
class Context {
    private Strategy strategy;
    
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    
    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// Тестирование стратегий
public class StrategyDemo {
    public static void main(String[] args) {
        Context context = new Context(new ConcreteStrategy1());
        System.out.println("Результат выполнения стратегии #1: " + context.executeStrategy(3, 4));
        
        context.setStrategy(new ConcreteStrategy2());
        System.out.println("Результат выполнения стратегии #2: " + context.executeStrategy(3, 4));
    }
}
