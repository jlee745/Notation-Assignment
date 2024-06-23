/**
 * This exception is thrown when a pop or peek method is called on an empty stack.
 * @author Jaegyoon Lee
 */
public class StackUnderflowException extends RuntimeException {
    
    /**
     * No-arg constructor with default message for stack underflow
     * @param string 
     * @param string 
     */
    public StackUnderflowException() {
        super("The stack is empty");
    }
    
    public StackUnderflowException(String message) {
    	super(message);
    }
}