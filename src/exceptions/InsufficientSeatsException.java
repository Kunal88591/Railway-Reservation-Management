package exceptions;

/**
 * Exception thrown when insufficient seats are available
 */
public class InsufficientSeatsException extends BookingException {
    
    private int requestedSeats;
    private int availableSeats;
    
    public InsufficientSeatsException(int requestedSeats, int availableSeats) {
        super(String.format("Insufficient seats available. Requested: %d, Available: %d",
                requestedSeats, availableSeats));
        this.requestedSeats = requestedSeats;
        this.availableSeats = availableSeats;
    }
    
    public int getRequestedSeats() {
        return requestedSeats;
    }
    
    public int getAvailableSeats() {
        return availableSeats;
    }
}
