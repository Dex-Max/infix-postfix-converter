import java.util.ArrayList;

/**
 * First in first out queue
 * @author Randall Kim
 * @param <T> data type to be stored in the queue
 */
public class NotationQueue<T> implements QueueInterface<T>{
    private int size;
    private final int maxSize;
    private LinkedNode<T> front;
    private LinkedNode<T> back;

    /**
     * Constructor that creates a queue with default size
     */
    public NotationQueue(){
        this(Integer.MAX_VALUE);
    }

    /**
     * Constructor that creates a queue with given size
     * @param maxSize the maximum number of elements that the queue can hold
     */
    public NotationQueue(int maxSize){
        size = 0;
        this.maxSize = maxSize;
    }

    /**
     * Determines if the queue is empty
     * @return true if the queue is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Determines if the queue is full
     * @return true if the queue is full
     */
    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * Removes data from the front of the queue and returns the value
     * @return the data at the front of the queue
     * @throws QueueUnderflowException if there is no element to dequeue
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty()){
            throw new QueueUnderflowException();
        }

        T data = front.data;
        front = front.before;

        if(front == null){
            back = null;
        } else {
            front.next = null;
        }

        size--;
        return data;
    }

    /**
     * Determines the current size of the queue
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds an element to the end of the queue
     * @param e the element to add to the end of the Queue
     * @return true if an element was successfully added
     * @throws QueueOverflowException if the queue is full
     */
    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(isFull()){
            throw new QueueOverflowException();
        }

        LinkedNode<T> newNode = new LinkedNode<>(e);

        if(isEmpty()){
            front = newNode;
        } else {
            newNode.next = back;
            back.before = newNode;
        }

        size++;
        back = newNode;

        return true;
    }

    /**
     * Appends all elements from front to back to a string
     * @return a string with all of the elements in the queue from front to back
     */
    @Override
    public String toString(){
        return toString("");
    }

    /**
     * Appends all elements from front to back to a string with a delimiter between each element
     * @param delimiter a string to be included between each element
     * @return a string with all of the elements in the queue with the delimiter between each element
     */
    @Override
    public String toString(String delimiter) {
        if(isEmpty()){
            return "";
        }

        String total = front.data.toString();
        LinkedNode node = front.before;

        while(node != null){
            total = total.concat(delimiter + node.data.toString());
            node = node.before;
        }

        return total;
    }

    /**
     * Enqueues all of the elements in an ArrayList into the queue
     * @param list elements to be added to the Queue
     */
    @Override
    public void fill(ArrayList<T> list) {
        for(T data : list){
            enqueue(data);
        }
    }
}
