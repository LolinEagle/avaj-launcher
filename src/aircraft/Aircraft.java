package aircraft;

import weather.Coordinates;
import weather.WeatherType;
import weather.WeatherProvider;

public abstract class Aircraft{
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	private static long idCounter = 0;

	protected Aircraft(String name, Coordinates coordinates){
		this.id = nextId();
		this.name = name;
		this.coordinates = coordinates;
	}

	private long nextId(){
		return ++idCounter;
	}

	protected String getLogInfo(){
		return String.format("%s#%s(%d)", 
			this.getClass().getSimpleName(), 
			this.name, 
			this.id);
	}
}
