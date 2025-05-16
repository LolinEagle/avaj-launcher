package aircraft;

import weather.Coordinates;
import tower.WeatherTower;

public class AircraftFactory{
	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height){
		Coordinates coordinates = new Coordinates(longitude, latitude, height);
		
		switch (type.toLowerCase()){
			case "helicopter":
				return new Helicopter(name, coordinates);
			case "jetplane":
				return new JetPlane(name, coordinates);
			case "baloon":
				return new Baloon(name, coordinates);
			default:
				throw new IllegalArgumentException("Unknown aircraft type: " + type);
		}
	}
}
