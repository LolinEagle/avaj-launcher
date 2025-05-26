package aircraft;

import weather.Coordinates;

public abstract class Aircraft extends Flyable{
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	private static long		idCounter = 0;

	protected	Aircraft(long id, String name, Coordinates coordinates){
		this.id = nextId(id);
		this.name = name;
		this.coordinates = coordinates;
	}

	private long	nextId(long id){
		if (id > idCounter){
			idCounter = id;
			return id;
		} else {
			return ++idCounter;
		}
	}

	protected String	getLogInfo(){
		return String.format("%s#%s(%d)", this.getClass().getSimpleName(),
		this.name, this.id);
	}
}
