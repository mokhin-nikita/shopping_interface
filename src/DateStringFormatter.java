import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateStringFormatter {
    private DateStringFormatter() {

    }
    public static String Format(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
}