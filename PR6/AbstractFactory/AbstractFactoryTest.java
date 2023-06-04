package PR6.AbstractFactory;

public class AbstractFactoryTest {
    interface AbstractProduct {
        void doSomething();
    }

    static class ConcreteProductA implements AbstractProduct {
        @Override
        public void doSomething() {
            System.out.println("ConcreteProductA is doing something.");
        }
    }

    static class ConcreteProductB implements AbstractProduct {
        @Override
        public void doSomething() {
            System.out.println("ConcreteProductB is doing something.");
        }
    }

    interface AbstractFactory {
        AbstractProduct createProductA();
        AbstractProduct createProductB();
    }

    static class ConcreteFactory implements AbstractFactory {
        @Override
        public AbstractProduct createProductA() {
            return new ConcreteProductA();
        }

        @Override
        public AbstractProduct createProductB() {
            return new ConcreteProductB();
        }
    }
    public static class Client {
        public static void main(String[] args) {
            AbstractFactory factory1 = new ConcreteFactory();
            AbstractProduct productA = factory1.createProductA();
            AbstractProduct productB = factory1.createProductB();
            productA.doSomething();
            productB.doSomething();
        }
    }
}

