package tower;

import weather.WeatherProvider;
import weather.Coordinates;

public class WeatherTower extends Tower{
	public String	getWeather(Coordinates coordinates){
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	public void	changeWeather(){
		conditionsChanged();
	}
}
