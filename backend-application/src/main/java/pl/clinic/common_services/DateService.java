package pl.clinic.common_services;

import java.time.LocalDateTime;

public class DateService {
    public static boolean isDayEqual(LocalDateTime date, LocalDateTime date2) {
        return date.getDayOfYear() == date.getDayOfYear() && date.getYear() == date2.getYear();
    }
}
