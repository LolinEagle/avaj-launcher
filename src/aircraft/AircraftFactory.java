package aircraft;

import weather.Coordinates;

public class AircraftFactory{
	private static AircraftFactory	instance = null;
	
	private	AircraftFactory(){}
	
	public static AircraftFactory	getInstance(){
		if (instance == null){
			instance = new AircraftFactory();
		}
		return instance;
	}
	
	public Flyable	newAircraft(String type, String name,
	Coordinates coordinates){
		switch (type.toLowerCase()){
			case "helicopter":
				return new Helicopter(name, coordinates);
			case "jetplane":
				return new JetPlane(name, coordinates);
			case "baloon":
				return new Baloon(name, coordinates);
			default:
				throw new IllegalArgumentException("Unknown type : " + type);
		}
	}
}
