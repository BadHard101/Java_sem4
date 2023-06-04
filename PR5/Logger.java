package PR5;

public class Logger {
    private static Logger instance = null;
    private Logger() {
        // приватный конструктор
    }
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    public void log(String message) {
        System.out.println(message);
    }
}
