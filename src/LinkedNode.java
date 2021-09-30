/**
 * Node for a doubly linked list
 * @author Randall Kim
 * @param <T> data type that is in each node
 */
public class LinkedNode<T> {
    T data;
    LinkedNode next;
    LinkedNode before;

    /**
     * Constructor to create a node with data
     * @param data information that the node holds
     */
    LinkedNode(T data){
        this.data = data;
    }
}
