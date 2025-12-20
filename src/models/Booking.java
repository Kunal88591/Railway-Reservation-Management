package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Booking/Reservation in the railway system
 */
public class Booking {
    private String pnr; // Passenger Name Record
    private String userId;
    private Train train;
    private List<Passenger> passengers;
    private LocalDateTime bookingDateTime;
    private BookingStatus status;
    private double totalFare;
    private String paymentMethod;
    private String seatNumbers;

    public enum BookingStatus {
        CONFIRMED,
        WAITING,
        CANCELLED,
        COMPLETED
    }

    public Booking(String pnr, String userId, Train train, List<Passenger> passengers,
                   double totalFare, String paymentMethod) {
        this.pnr = pnr;
        this.userId = userId;
        this.train = train;
        this.passengers = new ArrayList<>(passengers);
        this.bookingDateTime = LocalDateTime.now();
        this.status = BookingStatus.CONFIRMED;
        this.totalFare = totalFare;
        this.paymentMethod = paymentMethod;
        this.seatNumbers = generateSeatNumbers(passengers.size());
    }

    private String generateSeatNumbers(int count) {
        StringBuilder seats = new StringBuilder();
        int baseNumber = (int) (Math.random() * 50) + 1;
        for (int i = 0; i < count; i++) {
            if (i > 0) seats.append(", ");
            seats.append((baseNumber + i));
        }
        return seats.toString();
    }

    // Getters and Setters
    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public List<Passenger> getPassengers() {
        return new ArrayList<>(passengers);
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = new ArrayList<>(passengers);
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(String seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    /**
     * Cancel this booking
     * @return true if cancellation successful
     */
    public boolean cancelBooking() {
        if (status == BookingStatus.CONFIRMED || status == BookingStatus.WAITING) {
            status = BookingStatus.CANCELLED;
            train.cancelSeats(passengers.size());
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(pnr, booking.pnr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pnr);
    }

    @Override
    public String toString() {
        return String.format("PNR: %s | Train: %s | Passengers: %d | Status: %s | Fare: ₹%.2f",
                pnr, train.getTrainNumber(), passengers.size(), status, totalFare);
    }
}
