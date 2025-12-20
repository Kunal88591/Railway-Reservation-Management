package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Represents a Train in the railway system
 */
public class Train {
    private String trainNumber;
    private String trainName;
    private String source;
    private String destination;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int totalSeats;
    private int availableSeats;
    private double fare;
    private String trainType; // Express, Superfast, Passenger, etc.
    private LocalDate operationDate;

    public Train(String trainNumber, String trainName, String source, String destination,
                 LocalTime departureTime, LocalTime arrivalTime, int totalSeats,
                 double fare, String trainType, LocalDate operationDate) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.fare = fare;
        this.trainType = trainType;
        this.operationDate = operationDate;
    }

    // Getters and Setters
    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    /**
     * Book seats on this train
     * @param numberOfSeats number of seats to book
     * @return true if booking successful, false otherwise
     */
    public boolean bookSeats(int numberOfSeats) {
        if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
            return true;
        }
        return false;
    }

    /**
     * Cancel seats on this train
     * @param numberOfSeats number of seats to cancel
     */
    public void cancelSeats(int numberOfSeats) {
        availableSeats = Math.min(totalSeats, availableSeats + numberOfSeats);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(trainNumber, train.trainNumber) &&
               Objects.equals(operationDate, train.operationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainNumber, operationDate);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s to %s) | Departure: %s | Arrival: %s | Available: %d/%d | Fare: ₹%.2f",
                trainNumber, trainName, source, destination, 
                departureTime, arrivalTime, availableSeats, totalSeats, fare);
    }
}
