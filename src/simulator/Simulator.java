package simulator;

import java.io.*;
import java.util.*;

import aircraft.*;
import exception.*;
import tower.*;
import weather.*;

public class Simulator{
	public static final String	ERROR1 = "Simulation cycles must be positive";
	public static final String	ERROR2 = "Invalid aircraft format : ";
	public static final String	ERROR3 = "Invalid coordinates for aircraft : ";
	public static final String	ERROR4 = "Invalid number format in aircraft : ";
	public static final String	ERROR5 = "Unknown aircraft type : ";

	private static PrintWriter	writer = null;

	public static void	log(String message){
		if (writer != null){
			writer.println(message);
		}
	}

	public static void	main(String[] args){
		if (args.length != 1){
			System.out.println("Usage : java Simulator scenario.txt");
			return;
		}

		try{
			writer = new PrintWriter(new FileWriter("simulation.txt"));
			simulate(args[0]);
		} catch (InvalidScenarioException e){
			System.out.println("Error : " + e.getMessage());
		} catch (IOException e){
			System.out.println("Error reading file : " + e.getMessage());
		} finally {
			if (writer != null){
				writer.close();
			}
		}
	}

	private static void	simulate(String fileName)
	throws IOException, InvalidScenarioException{
		BufferedReader	reader = new BufferedReader(new FileReader(fileName));
		String			line = reader.readLine();// Read simulation cycles
		if (line == null){
			throw new InvalidScenarioException("Empty scenario file");
		}

		int	cycles;
		try{
			cycles = Integer.parseInt(line.trim());
			if (cycles < 0){
				throw new InvalidScenarioException(ERROR1);
			}
		} catch (NumberFormatException e){
			throw new InvalidScenarioException("Invalid cycle count");
		}

		// Read aircrafts
		WeatherTower	weatherTower = new WeatherTower();
		List<Flyable>	aircrafts = new ArrayList<>();

		while ((line = reader.readLine()) != null){
			line = line.trim();
			if (line.isEmpty()) continue;

			String[]	parts = line.split("\\s+");
			if (parts.length != 5){
				throw new InvalidScenarioException(ERROR2 + line);
			}

			try{
				String	type = parts[0];
				String	name = parts[1];
				int		longitude = Integer.parseInt(parts[2]);
				int		latitude = Integer.parseInt(parts[3]);
				int		height = Integer.parseInt(parts[4]);

				if (longitude < 0 || latitude < 0 ||
				height < 0 || height > 100){
					throw new InvalidScenarioException(ERROR3 + line);
				}

				Coordinates	co = new Coordinates(longitude, latitude, height);
				Flyable	aircraft = AircraftFactory.getInstance().newAircraft(
				type, name, co);
				aircraft.registerTower(weatherTower);
				aircrafts.add(aircraft);
			} catch (NumberFormatException e){
				throw new InvalidScenarioException(ERROR4 + line);
			} catch (IllegalArgumentException e){
				throw new InvalidScenarioException(ERROR5 + parts[0]);
			}
		}

		// Run simulation
		for (int i = 0; i < cycles; i++){
			weatherTower.changeWeather();
		}

		reader.close();
	}
}
