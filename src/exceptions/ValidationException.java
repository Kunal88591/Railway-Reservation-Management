package exceptions;

/**
 * Exception thrown when data validation fails
 */
public class ValidationException extends RailwayReservationException {
    
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
