package edu.du.ict4315.parking;

import edu.du.ict4315.currency.Money;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// this will act as observable
public class ParkingLot {
    private String id;
    private String name;
    private Address address;
    private Money baseRate = Money.of(5.00);

    // New data member to hold the ParkingChargeStrategy for this ParkingLot
    private ParkingChargeStrategy parkingChargeStrategy;

    private List<ParkingObserver> observers = new ArrayList<>();

    // Add/Remove observers
    public void addObserver(ParkingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ParkingObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(ParkingEvent event) {
        for (ParkingObserver observer : observers) {
            observer.update(event);
        }
    }

    // Constructor with base rate and parking charge strategy
    public ParkingLot(String id, String name, Address address, Money baseRate, ParkingChargeStrategyFactory factory) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.baseRate = baseRate;
        this.parkingChargeStrategy = factory.createStrategy(this);; // Assign the factory
    }


    // Getter for base rate
    public Money getBaseRate() {
        return baseRate;
    }

    // Setter for parking charge strategy
    public void setParkingChargeStrategy(ParkingChargeStrategy parkingChargeStrategy) {
        this.parkingChargeStrategy = parkingChargeStrategy;
    }

    // Getter for parking charge strategy
    public ParkingChargeStrategy getParkingChargeStrategy() {
        return parkingChargeStrategy;
    }

    // Method to calculate parking charges using the strategy
    public Money getParkingCharges(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime) {
        if (parkingChargeStrategy == null) {
            throw new IllegalStateException("Parking charge strategy is not set.");
        }
        return parkingChargeStrategy.calculateCharge(permit, entryTime, exitTime, baseRate);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        sb.append("\n");
        sb.append(name);
        sb.append("\n");
        sb.append(address);
        sb.append("\n");
        sb.append("Base Rate: ").append(baseRate);
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    // Method for permit-required-on-enter lot, updated for the observer method
    public void enter(ParkingPermit permit, LocalDateTime entryTime) {
        // For entry-only tracking, we just log the time
        ParkingEvent event = new ParkingEvent(permit, this, entryTime, null);
        notifyObservers(event);
    }

    // Method for permit-required-on-exit lot, updated for the observer method
    public void exit(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime) {
        ParkingEvent event = new ParkingEvent(permit, this, entryTime, exitTime);
        notifyObservers(event);
    }
}
