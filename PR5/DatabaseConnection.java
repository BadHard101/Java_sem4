package PR5;

// С потокобезопасностью
public class DatabaseConnection {

    // все потоки будут видеть один и тот же экземпляр объекта (volatile)
    private static volatile DatabaseConnection instance = null;
    private static final Object lock = new Object();

    private DatabaseConnection() {
        // приватный конструктор
    }

    public static DatabaseConnection getInstance() {
        // проверяем, создан ли уже экземпляр класса
        if (instance == null) {
            // если нет, то мы блокируем доступ к объекту (synchronized)
            synchronized (lock) {
                // повторно проверяем, создан ли объект
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public void connect() {
        // подключение к базе данных
        System.out.println("Connection - ready");
    }
}
