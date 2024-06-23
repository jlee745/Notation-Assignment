import java.util.ArrayList;

/**
 * Implementation of a generic queue data structure.
 * @author Jaegyoon Lee
 * @param <T> data type
 */
public class MyQueue<T> implements QueueInterface<T>{
    
    /**
     * Data structure that behaves as queue
     */
    private ArrayList<T> queue;
    /**
     * Capacity of queue
     */
    private int size;
    
    /**
     * No-arg constructor with default values for the data fields.
     */
    public MyQueue() {
        queue = new ArrayList<>();
        size = 1000;
    }

    /**
     * One-arg constructor that instantiates queue and sets capacity value.
     * @param capacity maximum queue capacity
     */
    public MyQueue(int capacity) {
        queue = new ArrayList<>(capacity);
        this.size = capacity;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean isFull() {
        return queue.size() == size;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (!isEmpty()) {
            T element = queue.get(0);
            queue.remove(0);
            return element;
        } else { 
            throw new QueueUnderflowException();
        }
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (!isFull()) {
            return queue.add(e);
        } else {
            throw new QueueOverflowException();
        }
    }

    @Override
    public String toString() {
        String strQueue = "";
        for (T e : queue) {
            strQueue += e;
        }
        return strQueue;
    }
    
    @Override
    public String toString(String delimiter) {
        String strQueue = "";
        for (T e : queue) {
            strQueue += e + delimiter;
        }
        return strQueue.substring(0, strQueue.length() - 1);
    }

    @Override
    public void fill(ArrayList<T> list) {
        for (T element : list) {
            enqueue(element);
        }
    }
    
}