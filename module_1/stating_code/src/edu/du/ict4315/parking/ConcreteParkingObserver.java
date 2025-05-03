package edu.du.ict4315.parking;

public class ConcreteParkingObserver implements ParkingObserver {

    private TransactionManager transactionManager;

    public ConcreteParkingObserver(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void update(ParkingEvent event) { // this will be called when changes are made
        transactionManager.park(event.getEntryTime(), event.getExitTime(), event.getPermit(), event.getLot());
    }
}