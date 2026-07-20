package Model;

public class Weather {

    private final String city;
    private final double temperature;
    private final double feelsLike;
    private final int humidity;
    private final double windKph;
    private final String condition;

    public Weather(String city, double temperature, double feelsLike,
                   int humidity, double windKph, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windKph = windKph;
        this.condition = condition;
    }

    public String getCity()
    {
        return city;
    }
    public double getTemperature()
    {
        return temperature;
    }
    public double getFeelsLike()
    {
        return feelsLike;
    }
    public int    getHumidity()
    { return humidity;
    }
    public double getWindKph()     { return windKph; }
    public String getCondition()   { return condition; }
}