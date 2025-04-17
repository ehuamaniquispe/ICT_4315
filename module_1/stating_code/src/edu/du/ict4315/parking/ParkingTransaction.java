//////////////////////////
// This class represents a parking transaction event.
// This class is immutable.
// File: ParkingTransaction.java
// Author: M. I. Schwartz
//////////////////////////
package edu.du.ict4315.parking;

import java.time.Instant;
import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;

public class ParkingTransaction {
	private Instant transactionDate;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private ParkingPermit permit;
	private ParkingLot parkingLot;
	private Money chargedAmount;

	public ParkingTransaction(LocalDateTime entryTime, LocalDateTime exitTime, ParkingPermit permit, ParkingLot parkingLot, Money chargedAmount) {
		this.transactionDate = Instant.now();
		this.entryTime = entryTime;
		this.exitTime = exitTime;
		this.permit = permit;
		this.parkingLot = parkingLot;
		this.chargedAmount = chargedAmount;
	}

	// Getters
	public Money getChargedAmount() {
		return chargedAmount;
	}

	public ParkingPermit getPermit() {
		return permit;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public Instant getTransactionDate() {
		return transactionDate;
	}
}
