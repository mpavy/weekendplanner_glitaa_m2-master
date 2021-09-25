package service;

import domain.WeatherManager;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Weather {
	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	String ville;
	String expectedWeather;
	int expectedWindspeed;
	int expectedSeaLevel;
	
	public Weather(String ville) throws Exception {
		this.ville = ville;
		getWeather(ville);
	}
	private void getWeather(String ville) throws Exception {

        HttpGet request = new HttpGet("http://api.openweathermap.org/data/2.5/forecast?q="+ville+"&units=metric&appid=c7955ed0ac8725dff8009499bdeb512b");

        
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                
                JSONObject obj = new JSONObject(result);
                
                JSONArray list = (JSONArray) obj.get("list");
                
                JSONObject last = (JSONObject) list.get(list.length()-1);

                JSONObject weather = (JSONObject)last.getJSONArray("weather").get(0);
                String detailedWeather  = weather.getString("main");

                switch (detailedWeather){
                    case "Clear":
                        expectedWeather = WeatherManager.CLEAR;
                        break;
                    case "Clouds":
                    case "Fog":
                        expectedWeather = WeatherManager.NO_RAIN;
                        break;
                    default:
                        break;
                }

                JSONObject wind = last.getJSONObject("wind");
                expectedWindspeed = wind.getInt("speed");

                JSONObject main = last.getJSONObject("main");
                expectedSeaLevel = main.getInt("sea_level");
            }

        }

    }
}
