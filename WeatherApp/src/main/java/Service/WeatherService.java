package Service;

import Api.APIClient;
import Model.Weather;
import org.json.JSONObject;

public class WeatherService {

    private final APIClient api = new APIClient();

    public Weather getWeather(String city) throws Exception {

        String data = api.fetchData(city);

        JSONObject root    = new JSONObject(data);
        JSONObject current = root.getJSONObject("current");

        // Resolved city name from API (handles aliases like "NYC" -> "New York")
        String resolvedCity = root.getJSONObject("location").getString("name")
                + ", " + root.getJSONObject("location").getString("country");

        double temp      = current.getDouble("temp_c");
        double feelsLike = current.getDouble("feelslike_c");
        int    humidity  = current.getInt("humidity");
        double windKph   = current.getDouble("wind_kph");
        String condition = current.getJSONObject("condition").getString("text");

        return new Weather(resolvedCity, temp, feelsLike, humidity, windKph, condition);
    }
}