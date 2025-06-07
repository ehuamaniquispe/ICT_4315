package edu.du.ict4315.parking;

public interface ITransactionManager {
    ParkingTransaction park(LocalDateTime entryTime, LocalDateTime exitTime, ParkingPermit permit, ParkingLot lot);
    Money getParkingCharges(Customer customer);
    Money getParkingCharges(ParkingPermit permit);
}
