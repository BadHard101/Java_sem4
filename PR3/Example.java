package PR3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example {
    static volatile int buf;
    private static final Lock lock = new ReentrantLock();

    static void increment() {
        lock.lock(); // or synchronized, с lock работает медленнее (sleep больше ставить надо)
        buf++;
        lock.unlock();
    }

    private static final Semaphore semaphore = new Semaphore(1);
    static void increment1() {
        try {
            semaphore.acquire();
            buf++;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        buf = 0;
        Thread one = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        });
        Thread two = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                increment();
            }
        });
        one.start();
        two.start();
        Thread.sleep(200);
        System.out.println(buf);
    }
}
