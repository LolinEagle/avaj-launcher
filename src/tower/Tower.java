package tower;

import java.util.ArrayList;
import java.util.List;

import aircraft.Flyable;

public abstract class Tower{
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable){
		if (!observers.contains(flyable)){
			observers.add(flyable);
		}
	}

	public void unregister(Flyable flyable){
		observers.remove(flyable);
	}

	protected void conditionsChanged(){
		for (int i = 0; i < observers.size(); i++){
			Flyable flyable = observers.get(i);
			flyable.updateConditions();
		}
	}
}
