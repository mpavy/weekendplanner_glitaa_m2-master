package service;

public class AssociationDTO {

    private String sportName;
    private String locationName;

    public AssociationDTO(String sportName, String locationName) {
        this.sportName = sportName;
        this.locationName = locationName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
