package exceptions;

/**
 * Exception thrown when a train is not found
 */
public class TrainNotFoundException extends RailwayReservationException {
    
    private String trainNumber;
    
    public TrainNotFoundException(String trainNumber) {
        super("Train not found: " + trainNumber);
        this.trainNumber = trainNumber;
    }
    
    public String getTrainNumber() {
        return trainNumber;
    }
}
