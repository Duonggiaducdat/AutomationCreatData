package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getTimeForFileName() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        return LocalDateTime.now().format(formatter);
    }
    public static String getToday() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.now().format(formatter);
    }
    public static String getPlusMonth() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.now()
                .plusMonths(1)   // 👈 thêm dòng này
                .format(formatter);
    }
}
