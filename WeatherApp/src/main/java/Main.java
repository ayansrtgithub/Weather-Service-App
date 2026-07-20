import java.util.Scanner;

import Service.WeatherService;
import Model.Weather;
import Storage.SearchHistory;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        WeatherService service = new WeatherService();
        SearchHistory history = new SearchHistory();

        System.out.println("=== Weather Forecast App ===");
        System.out.println("Type 'history' to view search history, 'exit' to quit.\n");

        while (true) {
            System.out.print("Enter city: ");
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("City name cannot be empty. Please try again.\n");
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.equalsIgnoreCase("history")) {
                history.showHistory();
                continue;
            }

            try {
                Weather weather = service.getWeather(input);
                history.addCity(input);
                printWeather(weather);
            } catch (IllegalArgumentException e) {
                System.out.println("City not found: " + e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("Error fetching weather: " + e.getMessage() + "\n");
            }
        }

        sc.close();
    }

    private static void printWeather(Weather w) {
        System.out.println("\n--- Weather in " + w.getCity() + " ---");
        System.out.println("Condition   : " + w.getCondition());
        System.out.printf( "Temperature : %.1f°C (Feels like %.1f°C)%n", w.getTemperature(), w.getFeelsLike());
        System.out.println("Humidity    : " + w.getHumidity() + "%");
        System.out.printf( "Wind Speed  : %.1f km/h%n", w.getWindKph());
        System.out.println();
    }
}