package weather;

public class Coordinates{
	private int	longitude;
	private int	latitude;
	private int	height;

	public	Coordinates(int longitude, int latitude, int height){
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = Math.min(height, 100);
		if (this.height < 0)
			this.height = 0;
	}

	public int	getLongitude(){return longitude;}
	public int	getLatitude(){return latitude;}
	public int	getHeight(){return height;}

	public void	update(int longitudeDelta, int latitudeDelta, int heightDelta){
		longitude += longitudeDelta;
		latitude += latitudeDelta;
		height += heightDelta;
		height = Math.min(height, 100);
		if (height < 0)
			height = 0;
	}
}
