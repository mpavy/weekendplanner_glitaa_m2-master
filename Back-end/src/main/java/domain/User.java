package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User{
    private String firstName;
    
    private String lastName;
    
    private String email;

    private String userName;

    private String password;

    @Id
    @GeneratedValue
    private Long id;
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "location_userId")
    private List<Location> favouriteLocations = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name="sport_userId")
    private List<Sport> favouriteSports = new ArrayList<>();

    public User(String firstName, String lastName, String email, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = password;
        }
    }

    public List<Sport> getFavouriteSports() {
        return favouriteSports;
    }

    public void setFavouriteSports(List<Sport> favouriteSports) {
        this.favouriteSports = favouriteSports;
    }

    public List<Location> getFavouriteLocations() {
        return favouriteLocations;
    }

    public void setFavouriteLocations(List<Location> favouriteLocations) {
        this.favouriteLocations = favouriteLocations;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
