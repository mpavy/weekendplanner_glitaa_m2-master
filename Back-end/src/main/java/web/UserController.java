package web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.*;

import java.util.List;


// controller that will use the service layers
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SportService sportService;

    @ApiOperation(value = "Create a new user")
    @PostMapping(value = "/user")
    public UserDTO createUser(@RequestBody UserDTO user){
        return userService.register(user);
    }

    @ApiOperation(value = "Login")
    @GetMapping(value = "/login")
    public ResponseEntity<UserDTO> login(String email, String password){
        UserDTO u = userService.getUserbyEmail(email);
        if (password.equals(u.getPassword())) {
            return ResponseEntity.ok().body(u);
            }
        UserDTO user = new UserDTO();
        user.setId(-1L);
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "Returns the location list corresponding to a user")
    @GetMapping(value = "/user/{id}/locations")
    public List<LocationDTO> getUserLocations(@PathVariable Long id){
        return userService.getFavouritesLocations(id);
    }

    @ApiOperation(value = "Returns the sport list corresponding to a user")
    @GetMapping(value = "/user/{id}/sports")
    public List<SportDTO> getUserSports(@PathVariable Long id){
        return userService.getFavouritesSports(id);
    }

    @ApiOperation(value = "Adds a sport to the favourite sports of a user")
    @PostMapping("/user/{id}/sport")
    public SportDTO registerSport(@PathVariable Long id, @RequestBody SportDTO sportDto){
        return userService.addFavouriteSport(sportDto, id);
    }

    @ApiOperation(value = "Adds a location to the favourite locations of a user")
    @PostMapping("/user/{id}/location")
    public LocationDTO registerLocation(@PathVariable Long id, @RequestBody LocationDTO locationDTO) {
        return userService.addFavouriteLocation(locationDTO, id);
    }

    @ApiOperation(value = "Returns the associations (sport - location) linked to one user")
    @GetMapping("/user/{id}/associations")
    public List<AssociationDTO> getUserAssociations(@PathVariable Long id){
        return userService.getUserAssociations(id);
    }

}
