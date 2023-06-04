package PR3;

import java.util.concurrent.Semaphore;

public class ArrayList {
    private Object[] array;
    private int size;
    private final Semaphore semaphore = new Semaphore(1);

    public ArrayList() {
        try {
            semaphore.acquire();
            array = new Object[10];
            size = 0;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void add(Object o) {
        try {
            semaphore.acquire();
            if (size == array.length) {
                Object[] newArray = new Object[array.length * 2];
                for (int i = 0; i < array.length; i++) {
                    newArray[i] = array[i];
                }
                array = newArray;
            }
            array[size] = o;
            size++;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object get(int index) {
        return array[index];
    }

    public void remove(int index) {
        try {
            semaphore.acquire();
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        return size;
    }

}
