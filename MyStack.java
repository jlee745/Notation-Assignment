import java.util.ArrayList;

/**
 * Implementation of a generic stack data structure.
 * @author Jaegyoon Lee
 * @param <T> data type
 */
public class MyStack<T> implements StackInterface<T> {
    
    /**
     * Data structure that behaves as stack
     */
    private ArrayList<T> stack;
    /**
     * Capacity of stack
     */
    private int size;
    
    /**
     * No-arg constructor with default values for the data fields.
     */
    public MyStack() {
        stack = new ArrayList<>();
        size = 1000;
    }
    
    /**
     * One-arg constructor that instantiates queue and sets capacity value.
     * @param capacity maximum stack capacity
     */
    public MyStack(int capacity) {
        stack = new ArrayList<>(capacity);
        this.size = capacity;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return stack.size() == size;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (!isEmpty()) {
            T element = stack.get(size() - 1);
            stack.remove(size() - 1);
            return element;
        } else { 
            throw new StackUnderflowException();
        }
    }

    @Override
    public T peek() throws StackUnderflowException {
        if (!isEmpty()) {
            return stack.get(size() - 1);
        } else {
            throw new StackUnderflowException();
        }
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (!isFull()) {
            return stack.add(e);
        } else {
            throw new StackOverflowException();
        }
    }

    @Override
    public String toString() {
        String strStack = "";
        for (T e : stack) {
            strStack += e;
        }
        return strStack;
    }
    
    public T top() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException("Empty Stack");
		}
		return stack.get(stack.size() - 1);
	}

    
    @Override
    public String toString(String delimiter) {
        String strStack = "";
        for (T e : stack) {
            strStack += e + delimiter;
        }
        return strStack.substring(0, strStack.length() - 1);
    }

    @Override
    public void fill(ArrayList<T> list) throws StackOverflowException {
        for (T element : list) {
            push(element);
        }
    }

    
}