/**
 * Thrown when a postfix or infix expression is not valid
 * @author Randall Kim
 */
public class InvalidNotationFormatException extends RuntimeException{
    /**
     * Constructs an InvalidNotationFormatException with default message
     */
    public InvalidNotationFormatException(){
        super("Expression is not a valid format");
    }
}
