package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import application.WeekendPlannerApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;

import domain.Location;
import domain.Sport;
import domain.User;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

@Component
public class ScheduledTasks {

	@Autowired private UserRepository userRepository;

    @Scheduled(cron = "0 0 20 * * TUE")
	@Transactional
    public void weekendPlaningEmail() throws Exception {
        // get all users
    	List<User> users = userRepository.findAll();
    	// get their name and mail.
    	
    	for (User user : users) {
    	String toEmail = user.getEmail();
    	String toName = user.getFirstName();
    	
    	HashMap<String,List<String>> sportlocations = new HashMap<>();
    	
        // see where they want to spend the Weekend
    	for(Location location : user.getFavouriteLocations()) {
    		
    		String ville = location.getVille();
    		// request to openWeatherAPI
    		Weather weather = new Weather(ville);
    		// extract the following data from the API response:
    		String expectedWeather = weather.expectedWeather;
    		int expectedWindspeed = weather.expectedWindspeed;
    		int expectedSeaLevel = weather.expectedSeaLevel;
    		
    		List<String> sportMatches = new ArrayList<>();
    		
    		 // see if they planned to do a sport there
    		for(Sport sport : location.getAvailableSports()) {
    			boolean isMatch = true;
    			// check conditions
    			if(sport.getIdealWeather()!=null && !sport.getIdealWeather().equals(expectedWeather)) {
    				isMatch = false;
    			}

   				if(sport.getSeaLevelMax()!=null && sport.getSeaLevelMin()!=null) {
   					if(expectedSeaLevel>sport.getSeaLevelMax() || expectedSeaLevel<sport.getSeaLevelMin()) {
   						isMatch = false;
   					}
   				}

   				if(sport.getWindMaxSpeed()!=null && sport.getWindMinSpeed()!=null) {
   					if(expectedWindspeed>sport.getWindMaxSpeed() || expectedWindspeed<sport.getWindMinSpeed()) {
   						isMatch = false;
   					}
   				}

   				//conclude
   				if(isMatch) {
   					sportMatches.add(sport.getName());
   				}
    		}
    		
    		if (sportMatches.size()>0) {
    			sportlocations.put(location.getName(), sportMatches);
    		}
    	}
        // prepare the results
    	MailSender mailsender = new MailSender(toEmail, toName,sportlocations);
    	
        // send the email
	    	try {
				mailsender.sendMail();
			} catch (MailjetException | MailjetSocketTimeoutException e) {
				e.printStackTrace();
			}
			;
    	}
    }
}
