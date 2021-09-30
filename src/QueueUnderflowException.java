/**
 * Thrown when user tries to dequeue from an empty queue
 * @author Randall Kim
 */
public class QueueUnderflowException extends RuntimeException{
    /**
     * Constructs a QueueUnderflowException with default message
     */
    public QueueUnderflowException(){
        super("Can not dequeue from an empty queue");
    }
}
