package weather;

public enum WeatherType{
	SUN, RAIN, FOG, SNOW;

	@Override
	public String	toString(){
		return this.name();
	}
}
