package PR3;


public class TestArrayList {
    public static void pullInts(ArrayList list) {
        for (int i = 0; i < 50000; i++) {
            list.add(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayList list = new ArrayList();
        //ArrayList<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                pullInts(list);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                pullInts(list);
            }
        });

        t1.start();
        t2.start();

        //Thread.sleep(100);
        t1.join();
        t2.join();

        System.out.println(list.size());
    }

}
