package PR5;

public class AppConfig {
    private AppConfig() {
        // приватный конструктор
    }

    private static class AppConfigHolder {
        private static final AppConfig INSTANCE = new AppConfig();
    }

    public static AppConfig getInstance() {
        return AppConfigHolder.INSTANCE;
    }

    public void doSomething() {
        // метод класса
        System.out.println("App love you ♥");
    }
}
