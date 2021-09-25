package web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import service.LocationService;
import service.SportService;
import service.UserService;

@RestController
@CrossOrigin
public class SportController {

	@Autowired
	UserService userService;

	@Autowired
	SportService sportService;
	
	@Autowired
	LocationService locationService;
	
	@ApiOperation(value = "Associate a sport with a location")
	@PutMapping("/sport/{sportId}/location/{locationId}")
	public ResponseEntity<?> registerLocationToSport(@PathVariable Long sportId, @PathVariable Long locationId) {
		sportService.registerLocationToSport(sportId, locationId);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
}
