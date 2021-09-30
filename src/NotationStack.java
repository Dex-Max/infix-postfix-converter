import java.util.ArrayList;

/**
 * Last in first out stack
 * @author Randall Kim
 * @param <T> data type to be stored in the stack
 */
public class NotationStack<T> implements StackInterface<T> {
    private int size;
    private final int maxSize;
    private LinkedNode<T> top;
    private LinkedNode<T> bottom;

    /**
     * Constructor that creates a stack with default size
     */
    public NotationStack(){
        this(Integer.MAX_VALUE);
    }

    /**
     * Constructor that creates a queue with given size
     * @param maxSize the maximum number of elements that the stack can hold
     */
    public NotationStack(int maxSize){
        this.maxSize = maxSize;
        size = 0;
    }

    /**
     * Determines if the stack is empty
     * @return true if the stack is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Determines if the stack is full
     * @return true if the stack is full
     */
    @Override
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * Removes data from the top of the stack and returns the value
     * @return the data at the top of the stack
     * @throws StackUnderflowException if there is no element that can be popped
     */
    @Override
    public T pop() throws StackUnderflowException {
        if(isEmpty()){
            throw new StackUnderflowException();
        }

        T data = top.data;
        top = top.next;

        if(top == null){
            bottom = null;
        } else {
            top.before = null;
        }

        size--;
        return data;
    }

    /**
     * Returns the top element of the stack but does not remove from the stack
     * @return the data at the top of the stack
     * @throws StackUnderflowException if there is no element in the stack
     */
    @Override
    public T top() throws StackUnderflowException {
        if(top == null){
            throw new StackUnderflowException();
        }

        return top.data;
    }

    /**
     * Determines the current size of the stack
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Adds an element to the top of the stack
     * @param e the element to add to the top of the Stack
     * @return true if an element was successfully added
     * @throws StackOverflowException if the stack is full
     */
    @Override
    public boolean push(T e) throws StackOverflowException {
        if(isFull()){
            throw new StackOverflowException();
        }

        LinkedNode<T> newNode = new LinkedNode<>(e);

        if(isEmpty()){
            bottom = newNode;
        } else {
            top.before = newNode;
            newNode.next = top;
        }

        top = newNode;
        size++;

        return true;
    }

    /**
     * Appends all elements from bottom to top to a string
     * @return the string with all of the elements in the stack from bottom to top
     */
    @Override
    public String toString(){
        return toString("");
    }

    /**
     * Appends all elements from bottom to top to a string with a delimiter between each element
     * @param delimiter a string to be included between each element
     * @return a string with all of the elements in the stack with a delimiter between each element
     */
    @Override
    public String toString(String delimiter) {
        if(isEmpty()){
            return "";
        }

        String total = bottom.data.toString();
        LinkedNode node = bottom.before;

        while(node != null){
            total = total.concat(delimiter + node.data.toString());
            node = node.before;
        }

        return total;
    }

    /**
     * Pushes all elements in an ArrayList to the stack
     * @param list elements to be added to the Stack from bottom to top
     */
    @Override
    public void fill(ArrayList<T> list) {
        for(T data : list){
            push(data);
        }
    }
}
