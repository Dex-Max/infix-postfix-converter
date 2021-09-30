/**
 * Thrown when a user tries to pop or peek from an empty stack
 * @author Randall Kim
 */
public class StackUnderflowException extends RuntimeException{
    /**
     * Constructs a StackUnderflowException with default message
     */
    public StackUnderflowException(){
        super("Can not pop from an empty stack");
    }
}
