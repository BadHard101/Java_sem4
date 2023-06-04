package PR3;

import java.util.HashMap;
import java.util.TreeMap;

public class TestMap {
    public static void pullPairs1(SafeMap<String, Integer> map) {
        for (int i = 0; i < 50000; i++) {
            map.put("Key" + i, i);
        }
    }
    public static void pullPairs2(SafeMap<String, Integer> map) {
        for (int i = 50000; i < 100000; i++) {
            map.put("Key" + i, i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SafeMap<String, Integer> map = new SafeMap<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                pullPairs1(map);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                pullPairs2(map);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map.size());
    }
}
