package tower;

import java.util.ArrayList;
import java.util.List;

import aircraft.Flyable;

public abstract class Tower{
	private List<Flyable>	observers = new ArrayList<Flyable>();

	public void	register(Flyable flyable){
		if (flyable != null && !observers.contains(flyable)){
			observers.add(flyable);
		}
	}

	public void	unregister(Flyable flyable){
		observers.remove(flyable);
	}

	protected void	conditionChanged(){
		for (int i = 0; i < observers.size(); i++){
			Flyable flyable = observers.get(i);
			flyable.updateConditions();
		}
	}
}
