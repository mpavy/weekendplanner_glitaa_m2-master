package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {
    private String name;
    
    private Integer latitude;
    
    private Integer longitude;

    private String ville;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany(mappedBy = "availableLocations")
    private List<Sport> availableSports = new ArrayList<>();

    public Location(){}

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

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public List<Sport> getAvailableSports() {
        return availableSports;
    }

    public void setAvailableSports(List<Sport> availableSports) {
        this.availableSports = availableSports;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", ville='" + ville + '\'' +
                ", id=" + id +
                ", availableSports=" + availableSports +
                '}';
    }
}
