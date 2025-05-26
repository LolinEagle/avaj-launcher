package aircraft;

import simulator.Simulator;
import tower.WeatherTower;
import weather.Coordinates;

public class JetPlane extends Aircraft{
	private WeatherTower	weatherTower;

	public	JetPlane(long id, String name, Coordinates coordinates){
		super(id, name, coordinates);
	}

	public void	updateConditions(){
		String	weather = weatherTower.getWeather(this.coordinates);
		String	message = getLogInfo() + " : ";

		switch (weather){
			case "SUN":
				coordinates.update(0, 10, 2);
				message += "Perfect weather for high-speed cruising!";
				break;
			case "RAIN":
				coordinates.update(0, 5, 0);
				message += "It's raining. Better watch out for lightings.";
				break;
			case "FOG":
				coordinates.update(0, 1, 0);
				message += "Fog detected, switching to instrument flight.";
				break;
			case "SNOW":
				coordinates.update(0, 0, -7);
				message += "OMG! Winter is coming!";
				break;
		}
		Simulator.log(message);

		if (coordinates.getHeight() <= 0){
			Simulator.log(getLogInfo() + " landing.");
			weatherTower.unregister(this);
			Simulator.log("Tower says : " + getLogInfo() +
			" unregistered from weather tower.");
		}
	}

	public void	registerTower(WeatherTower weatherTower){
		this.weatherTower = weatherTower;
		weatherTower.register(this);
		Simulator.log("Tower says : " + getLogInfo() +
		" registered to weather tower.");
	}
}
