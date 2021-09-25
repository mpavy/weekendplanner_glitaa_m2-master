package web;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.WeatherManager;
import service.WeatherDTO;

@RestController
@CrossOrigin
public class WeatherController {

	@ApiOperation(value = "Returns the list of weather conditions")
	 @GetMapping("/weathers")
	    public List<WeatherDTO> getWeather(){
	        ArrayList<WeatherDTO> weathers = new ArrayList<>();
	        weathers.add(new WeatherDTO(WeatherManager.CLEAR));
	        weathers.add(new WeatherDTO(WeatherManager.NO_RAIN));
	        return weathers;
	    }
}
