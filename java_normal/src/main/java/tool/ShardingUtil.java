package tool;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class ShardingUtil {
    private static final String YYYYMMDD = "yyyyMMddHHmmss";

    public static LocalDateTime ofSerialNumber(String serialNumber) {
        int index = 0;

        for(int i = 0; i < serialNumber.length(); ++i) {
            char c = serialNumber.charAt(i);
            if (c >= '0' && c <= '9') {
                break;
            }

            ++index;
        }

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateStr = serialNumber.substring(index, index + "yyyyMMddHHmmss".length());
        LocalDateTime createdTime = LocalDateTime.parse(dateStr, FORMATTER);
        return createdTime;
    }

    private ShardingUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
