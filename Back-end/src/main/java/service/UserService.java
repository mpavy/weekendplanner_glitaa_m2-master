package service;

import domain.Location;
import domain.Sport;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repository.LocationRepository;
import repository.SportRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private EntitiesToDTOMapper mapper = EntitiesToDTOMapper.mapper;

    @Autowired private UserRepository userRepository;
    @Autowired private SportRepository sportRepository;
    @Autowired private LocationRepository locationRepository;
    @Autowired private SportService sportService;

    public UserDTO register(UserDTO userDTO){
        User u = mapper.UserDTOtoUser(userDTO);
        return mapper.UsertoUserDTO(userRepository.save(u));
    }

    @Transactional
    public List<SportDTO> getFavouritesSports(Long id){
        List<Sport> sports = getUserbyId(id).getFavouriteSports();
        List<SportDTO> sportsDTO = new ArrayList<>();
        for (Sport s: sports){
            sportsDTO.add(mapper.SporttoSportDTO(s));
        }
        return sportsDTO;
    }

    @Transactional
    public List<LocationDTO> getFavouritesLocations(Long id){
        List<Location> sports = getUserbyId(id).getFavouriteLocations();
        List<LocationDTO> locationsDTO = new ArrayList<>();
        for (Location l: sports){
            locationsDTO.add(mapper.LocationToLocationDTO(l));
        }
        return locationsDTO;
    }


    public User getUserbyId(Long id){
        Optional<User> u = userRepository.findById(id);
        return u.orElseThrow(() -> new WEPlannerEntityNotFoundException("The server couldn't find a user with the id : " +id));
    }

    @Transactional
    public UserDTO getUserbyEmail(String email){
        Optional<User> u = userRepository.findByEmail(email);
        User user = u.orElseThrow(() -> new WEPlannerEntityNotFoundException("The server couldn't find a user with the email : " +email));
        return mapper.UsertoUserDTO(user);
    }

    @Transactional
    public SportDTO addFavouriteSport(SportDTO sport, Long userId){
        Sport s = mapper.SportDTOtoSport(sport);
        Sport savedSport = sportRepository.save(s);
        User u = getUserbyId(userId);
        u.getFavouriteSports().add(s);
        userRepository.save(u);
        userRepository.findAll();
        return mapper.SporttoSportDTO(savedSport);
    }

    public void removeFavouriteSport(Long sportId, Long userId){
        Sport s = sportService.getSportbyId(sportId);
        User u = getUserbyId(userId);
        u.getFavouriteSports().remove(s);
        userRepository.save(u);
    }

    @Transactional
    public LocationDTO addFavouriteLocation(LocationDTO locationDTO, Long userId){
        Location l = mapper.LocationDTOtoLocation(locationDTO);
        Location savedLocation = locationRepository.save(l);
        User user = getUserbyId(userId);
        user.getFavouriteLocations().add(l);
        userRepository.save(user);
        return mapper.LocationToLocationDTO(savedLocation);
    }

    public void removeFavouriteLocation(Long sportId, Long userId){
        Sport s = sportService.getSportbyId(sportId);
        User u = getUserbyId(userId);
        u.getFavouriteSports().remove(s);
        userRepository.save(u);
    }

    

    @Transactional
    public List<AssociationDTO> getUserAssociations(Long id){
        List<AssociationDTO> associations = new ArrayList<>();
        User u = getUserbyId(id);
        List<Sport> sports = u.getFavouriteSports();
        for (Sport s: sports){
            for (Location l : s.getAvailableLocations()){
                AssociationDTO association = new AssociationDTO(s.getName(), l.getName());
                associations.add(association);
            }
        }
        return associations;
    }
}