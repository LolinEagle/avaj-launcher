package aircraft;

import simulator.Simulator;
import tower.WeatherTower;
import weather.Coordinates;

public class Baloon extends Aircraft implements Flyable{
	private WeatherTower weatherTower;

	public Baloon(String name, Coordinates coordinates){
		super(name, coordinates);
	}

	public void updateConditions(){
		String weather = weatherTower.getWeather(this.coordinates);
		String message = getLogInfo() + ": ";

		switch (weather){
			case "SUN":
				coordinates.update(2, 0, 4);
				message += "Let's enjoy this sunny day and see the world!";
				break;
			case "RAIN":
				coordinates.update(0, 0, -5);
				message += "Damn you rain! You messed up my baloon.";
				break;
			case "FOG":
				coordinates.update(0, 0, -3);
				message += "I can't see through this fog! Scary!";
				break;
			case "SNOW":
				coordinates.update(0, 0, -15);
				message += "It's snowing! We might crash!";
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
