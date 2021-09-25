package service;

import domain.Location;
import domain.Sport;
import domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntitiesToDTOMapper {

    EntitiesToDTOMapper mapper = Mappers.getMapper( EntitiesToDTOMapper.class );

    User UserDTOtoUser(UserDTO u);
    UserDTO UsertoUserDTO(User u);

    Sport SportDTOtoSport(SportDTO s);
    SportDTO SporttoSportDTO(Sport s);

    Location LocationDTOtoLocation(LocationDTO l);
    LocationDTO LocationToLocationDTO(Location l);
}
