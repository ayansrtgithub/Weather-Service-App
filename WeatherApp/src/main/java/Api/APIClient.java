package Api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

public class APIClient {

    private static final String API_KEY = System.getenv("WEATHER_API_KEY") != null ? System.getenv("WEATHER_API_KEY") : "9f321c0dbe3b4187907145753262205"; // Fallback for development only

    private static final String BASE_URL = "https://api.weatherapi.com/v1/current.json";

    public String fetchData(String city) throws Exception {

        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = BASE_URL + "?key=" + API_KEY + "&q=" + encodedCity;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 400 || response.statusCode() == 404) {
            throw new IllegalArgumentException("\"" + city + "\" was not found. Check the spelling and try again.");
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("API returned status code: " + response.statusCode());
        }

        return response.body();
    }
}