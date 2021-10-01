package exception;

/**
 * Exception when robot moves out of the grid
 */
public class OutOfBoundaryException extends Exception{

    public OutOfBoundaryException(String message) {
        super(message);
    }
}
