package exceptions;

/**
 * Exception thrown when a booking operation fails
 */
public class BookingException extends RailwayReservationException {
    
    public BookingException(String message) {
        super(message);
    }
    
    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
