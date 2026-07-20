package Storage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchHistory {

    // LinkedHashMap preserves insertion order and avoids duplicate keys
    private final LinkedHashMap<String, String> history = new LinkedHashMap<>();

    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    public void addCity(String city) {
        String key = city.toLowerCase();
        String time = LocalTime.now().format(TIME_FMT);
        // Overwrite so latest search time is always stored
        history.put(key, city + " (searched at " + time + ")");
    }

    public void showHistory() {
        if (history.isEmpty()) {
            System.out.println("No search history yet.\n");
            return;
        }
        System.out.println("\n--- Search History (" + history.size() + " unique cities) ---");
        int i = 1;
        for (Map.Entry<String, String> entry : history.entrySet()) {
            System.out.println("  " + i++ + ". " + entry.getValue());
        }
        System.out.println();
    }
}