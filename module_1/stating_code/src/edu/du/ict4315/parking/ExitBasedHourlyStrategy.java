package edu.du.ict4315.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import edu.du.ict4315.currency.Money;

public class ExitBasedHourlyStrategy implements ParkingChargeStrategy {

    @Override
    public Money calculateCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime, Money baseRate) {
        // Calculate the duration between entry and exit
        Duration duration = Duration.between(entryTime, exitTime);
        long minutes = duration.toMinutes();
        long hours = (minutes + 59) / 60; // Round up to the nearest hour

        // Calculate the base charge
        Money total = Money.times(baseRate, (int) hours);

        // Apply discount for compact cars
        if (isCompact(permit)) {
            total = Money.times(total, 0.8); // Apply 20% discount for compact cars
        }

        // Apply weekday vs. weekend surcharge
        total = applyDayOfWeekSurcharge(entryTime, total);

        return total;
    }

    private boolean isCompact(ParkingPermit permit) {
        return permit.getCar().getType() == CarType.COMPACT;
    }

    private Money applyDayOfWeekSurcharge(LocalDateTime entryTime, Money total) {
        DayOfWeek dayOfWeek = entryTime.getDayOfWeek();

        // Weekend surcharge (Saturday and Sunday)
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return Money.times(total, 1.2); // 20% surcharge for weekends
        }

        // Weekday (Monday to Friday)
        return total;
    }
}