package service;

public class SportDTO {

    private Long id;

    private String name;

    private String idealWeather;

    private Integer windMaxSpeed;

    private Integer windMinSpeed;

    private Integer seaLevelMax;

    private Integer seaLevelMin;

    public SportDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdealWeather() {
        return idealWeather;
    }

    public void setIdealWeather(String idealWeather) {
        this.idealWeather = idealWeather;
    }

    public Integer getWindMaxSpeed() {
        return windMaxSpeed;
    }

    public void setWindMaxSpeed(Integer windMaxSpeed) {
        this.windMaxSpeed = windMaxSpeed;
    }

    public Integer getWindMinSpeed() {
        return windMinSpeed;
    }

    public void setWindMinSpeed(Integer windMinSpeed) {
        this.windMinSpeed = windMinSpeed;
    }

    public Integer getSeaLevelMax() {
        return seaLevelMax;
    }

    public void setSeaLevelMax(Integer seaLevelMax) {
        this.seaLevelMax = seaLevelMax;
    }

    public Integer getSeaLevelMin() {
        return seaLevelMin;
    }

    public void setSeaLevelMin(Integer seaLevelMin) {
        this.seaLevelMin = seaLevelMin;
    }
}
