package service;

import domain.Location;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Component
public class LocationService {

    private EntitiesToDTOMapper mapper = Mappers.getMapper(EntitiesToDTOMapper.class);

    @Autowired
    LocationRepository locationRepository;

    public void addLocation(LocationDTO locationDTO){
        Location l = mapper.LocationDTOtoLocation(locationDTO);
        locationRepository.save(l);
    }

    public void deleteLocation(Long id){
        locationRepository.delete(getLocationById(id));
    }

    // To add the Exception messages
    public Location getLocationById(Long id){
        Optional<Location> u = locationRepository.findById(id);
        return u.orElseThrow(() -> new WEPlannerEntityNotFoundException("Couldn't find a Location with the id:" +id));
    }
}
