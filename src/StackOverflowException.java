/**
 * Thrown when an element is pushed onto a full stack
 * @author Randall Kim
 */
public class StackOverflowException extends RuntimeException{
    /**
     * Constructs a StackOverflowException with default message
     */
    public StackOverflowException(){
        super("Can not push onto a full stack");
    }
}
