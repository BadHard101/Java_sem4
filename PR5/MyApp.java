package PR5;

public class MyApp {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Привет, мир!");

        DatabaseConnection connection = DatabaseConnection.getInstance();
        connection.connect();

        AppConfig config = AppConfig.getInstance();
        config.doSomething();
    }
}
