package service;

import domain.Location;
import domain.Sport;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repository.LocationRepository;
import repository.SportRepository;

import java.util.Optional;

@Component
public class SportService {

    private EntitiesToDTOMapper mapper = Mappers.getMapper(EntitiesToDTOMapper.class);

    @Autowired SportRepository sportRepository;
    @Autowired private LocationRepository locationRepository;
    @Autowired private SportService sportService;
    @Autowired private LocationService locationService;

    public void add(SportDTO sportDTO){
        Sport s = mapper.SportDTOtoSport(sportDTO);
        sportRepository.save(s);
    }

    public void removeSport(Long id){
        sportRepository.delete(getSportbyId(id));
    }

    public void changeIdealWeather(Long id, String weather){
        Sport s = getSportbyId(id);
        s.setIdealWeather(weather);
        sportRepository.save(s);
    }

    public void addWindMax(Long id, Integer maxSpeed){
        Sport s = getSportbyId(id);
        s.setWindMaxSpeed(maxSpeed);
        sportRepository.save(s);
    }

    public void addWindMin(Long id, Integer minSpeed){
        Sport s = getSportbyId(id);
        s.setWindMinSpeed(minSpeed);
        sportRepository.save(s);
    }

    public  void addSeaLevelMax(Long id, Integer maxLevel){
        Sport s = getSportbyId(id);
        s.setSeaLevelMax(maxLevel);
        sportRepository.save(s);
    }

    public  void addSeaLevelMin(Long id, Integer minLevel){
        Sport s = getSportbyId(id);
        s.setSeaLevelMax(minLevel);
        sportRepository.save(s);
    }

    @Transactional
    public void registerLocationToSport(Long sportId, Long locationId){
        Sport s = sportService.getSportbyId(sportId);
        Location l = locationService.getLocationById(locationId);
        s.getAvailableLocations().add(l);
        l.getAvailableSports().add(s);
        sportRepository.save(s);
        locationRepository.save(l);
    }
    
    public Sport getSportbyId(Long id){
        Optional<Sport> u = sportRepository.findById(id);
        return u.orElseThrow(() -> new WEPlannerEntityNotFoundException("Couldn't find a Sport with the id:" +id));
    }
}
