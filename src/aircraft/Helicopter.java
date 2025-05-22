package aircraft;

import simulator.Simulator;
import tower.WeatherTower;
import weather.Coordinates;

public class Helicopter extends Aircraft implements Flyable{
	private WeatherTower weatherTower;

	public Helicopter(String name, Coordinates coordinates){
		super(name, coordinates);
	}

	public void updateConditions(){
		String weather = weatherTower.getWeather(this.coordinates);
		String message = getLogInfo() + ": ";

		switch (weather){
			case "SUN":
				coordinates.update(10, 0, 2);
				message += "This sun is shining bright, perfect for a helicopter ride!";
				break;
			case "RAIN":
				coordinates.update(5, 0, 0);
				message += "Rain makes the rotor blades shine!";
				break;
			case "FOG":
				coordinates.update(1, 0, 0);
				message += "Foggy conditions require careful navigation.";
				break;
			case "SNOW":
				coordinates.update(0, 0, -12);
				message += "My rotor is going to freeze!";
				break;
		}
		Simulator.log(message);

		if (coordinates.getHeight() <= 0){
			Simulator.log(getLogInfo() + " landing.");
			weatherTower.unregister(this);
			Simulator.log("Tower says: " + getLogInfo() + " unregistered from weather tower.");
		}
	}

	public void registerTower(WeatherTower weatherTower){
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.log("Tower says: " + getLogInfo() + " registered to weather tower.");
	}
}
