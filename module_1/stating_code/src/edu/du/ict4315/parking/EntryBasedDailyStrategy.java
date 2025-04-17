package edu.du.ict4315.parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import edu.du.ict4315.currency.Money;

public class EntryBasedDailyStrategy implements ParkingChargeStrategy {

    @Override
    public Money calculateCharge(ParkingPermit permit, LocalDateTime entryTime, LocalDateTime exitTime, Money baseRate) {
        int nightsCharged = 0;
        LocalDateTime current = entryTime.truncatedTo(ChronoUnit.DAYS).plusHours(0); // Start at midnight

        while (!current.isAfter(exitTime)) {
            LocalDateTime windowStart = current;
            LocalDateTime windowEnd = current.plusHours(6);

            if (!exitTime.isBefore(windowStart) && !entryTime.isAfter(windowEnd)) {
                nightsCharged++;
            }

            current = current.plusDays(1);
        }

        // Calculate the base total for nights charged
        Money total = Money.times(baseRate, nightsCharged);

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

