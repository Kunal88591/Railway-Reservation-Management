package models;

import java.util.Objects;

/**
 * Represents a Passenger in the railway system
 */
public class Passenger {
    private String passengerId;
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phoneNumber;
    private String idProofType; // Aadhaar, PAN, Passport, etc.
    private String idProofNumber;

    public Passenger(String passengerId, String name, int age, String gender,
                    String email, String phoneNumber, String idProofType, String idProofNumber) {
        this.passengerId = passengerId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idProofType = idProofType;
        this.idProofNumber = idProofNumber;
    }

    // Getters and Setters
    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdProofType() {
        return idProofType;
    }

    public void setIdProofType(String idProofType) {
        this.idProofType = idProofType;
    }

    public String getIdProofNumber() {
        return idProofNumber;
    }

    public void setIdProofNumber(String idProofNumber) {
        this.idProofNumber = idProofNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(passengerId, passenger.passengerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId);
    }

    @Override
    public String toString() {
        return String.format("%s - %s, Age: %d, Gender: %s, Contact: %s",
                passengerId, name, age, gender, phoneNumber);
    }
}
