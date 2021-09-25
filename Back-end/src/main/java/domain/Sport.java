package domain;

import io.swagger.models.auth.In;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sport {
    private String name;
    
    private String idealWeather;

    private Integer windMaxSpeed;

    private Integer windMinSpeed;

    private Integer seaLevelMax;

    private Integer seaLevelMin;
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "sport_location",
            joinColumns = @JoinColumn(name = "sportId"),
            inverseJoinColumns = @JoinColumn(name = "locationId"))
    private List<Location> availableLocations = new ArrayList<>();

    public Sport(String name, String idealWeather) {
        this.name = name;
        this.idealWeather = idealWeather;
    }

    public Sport(){}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWindMaxSpeed() {
        return windMaxSpeed;
    }

    public void setWindMaxSpeed(int windMaxSpeed) {
        this.windMaxSpeed = windMaxSpeed;
    }

    public Integer getWindMinSpeed() {
        return windMinSpeed;
    }

    public void setWindMinSpeed(int windMinSpeed) {
        this.windMinSpeed = windMinSpeed;
    }

    public Integer getSeaLevelMax() {
        return seaLevelMax;
    }

    public void setSeaLevelMax(int seaLevelMax) {
        this.seaLevelMax = seaLevelMax;
    }

    public Integer getSeaLevelMin() {
        return seaLevelMin;
    }

    public void setSeaLevelMin(int seaLevelMin) {
        this.seaLevelMin = seaLevelMin;
    }

    public List<Location> getAvailableLocations() {
        return availableLocations;
    }

    public void setAvailableLocations(List<Location> availableLocations) {
        this.availableLocations = availableLocations;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", idealWeather='" + idealWeather + '\'' +
                ", windMaxSpeed=" + windMaxSpeed +
                ", windMinSpeed=" + windMinSpeed +
                ", seaLevelMax=" + seaLevelMax +
                ", seaLevelMin=" + seaLevelMin +
                ", id=" + id +

                '}';
    }
}
