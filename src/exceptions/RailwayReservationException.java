package exceptions;

/**
 * Base exception class for Railway Reservation System
 */
public class RailwayReservationException extends Exception {
    
    public RailwayReservationException(String message) {
        super(message);
    }
    
    public RailwayReservationException(String message, Throwable cause) {
        super(message, cause);
    }
}
