package edu.du.ict4315.parking;

import edu.du.ict4315.currency.Money;
import java.time.LocalDateTime;

public class ParkingLot {
    private String id;
    private String name;
    private Address address;
    private Money baseRate = Money.of(5.00);

    // New data member to hold the ParkingChargeStrategy for this ParkingLot
    private ParkingChargeStrategy parkingChargeStrategy;

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

    // Method for permit-required-on-enter lot
    public void enterLot(LocalDateTime in, String permitId) {
        // Implementation for entering the lot
    }

    // Method for permit-required-on-exit lot
    public void exitLot(LocalDateTime in, LocalDateTime out, String permitId) {
        // Implementation for exiting the lot
    }
}
