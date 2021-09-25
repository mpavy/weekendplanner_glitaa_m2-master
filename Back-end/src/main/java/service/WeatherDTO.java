package service;

public class WeatherDTO {

    private String weather;

    public WeatherDTO(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
