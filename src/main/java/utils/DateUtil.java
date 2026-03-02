package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static String getTimeForFileName() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

        return LocalDateTime.now().format(formatter);
    }
}
