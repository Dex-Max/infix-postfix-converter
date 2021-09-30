/**
 * Thrown when an element is enqueued into a full queue
 * @author Randall Kim
 */
public class QueueOverflowException extends RuntimeException{
    /**
     * Constructs a QueueOverflowException with default message
     */
    public QueueOverflowException(){
        super("Can not enqueue into a full queue");
    }
}
