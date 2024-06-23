/**
 * This exception is thrown when the notation format is invalid.
 * @author Jaegyoon Lee
 */
public class InvalidNotationFormatException extends RuntimeException {
    
    /**
     * No-arg constructor with default message for invalid notation format
     */
    public InvalidNotationFormatException() {
        super("Notation format is invalid");
    }
}