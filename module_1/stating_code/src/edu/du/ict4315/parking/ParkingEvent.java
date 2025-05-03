package edu.du.ict4315.parking;

import java.time.LocalDateTime;

public class ParkingEvent {
    private final ParkingPermit permit;
    private final ParkingLot lot;
    private final LocalDateTime entryTime;
    private final LocalDateTime exitTime;

    public ParkingEvent(ParkingPermit permit, ParkingLot lot, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.permit = permit;
        this.lot = lot;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public ParkingPermit getPermit() {
        return permit;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }
}
