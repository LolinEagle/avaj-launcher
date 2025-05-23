package weather;

public class WeatherProvider{
	private static WeatherProvider weatherProvider = null;
	private static final String[] weather ={"SUN", "RAIN", "FOG", "SNOW"};

	private WeatherProvider(){}

	public static WeatherProvider getProvider(){
		if (weatherProvider == null){
			weatherProvider = new WeatherProvider();
		}
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coord){
		// Simple weather generation based on coordinates
		int	i = coord.getLongitude() + coord.getLatitude() + coord.getHeight();
		return weather[i % 4];
	}
}
