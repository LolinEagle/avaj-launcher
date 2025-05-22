package simulator;

import java.io.*;
import java.util.*;

import aircraft.*;
import tower.*;
import exception.*;

public class Simulator{
	private static PrintWriter	writer = null;

	public static void log(String message){
		if (writer != null){
			writer.println(message);
		}
    }

	public static void main(String[] args){
		if (args.length != 1){
			System.out.println("Usage: java Simulator scenario.txt");
			return;
		}

		try{
			writer = new PrintWriter(new FileWriter("simulation.txt"));
			simulate(args[0]);
		} catch (InvalidScenarioException e){
			System.out.println("Error: " + e.getMessage());
		} catch (IOException e){
			System.out.println("Error reading file: " + e.getMessage());
		} finally {
            if (writer != null){
                writer.close();
            }
        }
	}

	private static void simulate(String fileName) throws IOException, InvalidScenarioException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		// Read simulation cycles
		String line = reader.readLine();
		if (line == null){
			throw new InvalidScenarioException("Empty scenario file");
		}

		int cycles;
		try{
			cycles = Integer.parseInt(line.trim());
			if (cycles < 0){
				throw new InvalidScenarioException("Simulation cycles must be positive");
			}
		} catch (NumberFormatException e){
			throw new InvalidScenarioException("Invalid simulation cycle count");
		}

		// Read aircrafts
		WeatherTower weatherTower = new WeatherTower();
		List<Flyable> aircrafts = new ArrayList<>();

		while ((line = reader.readLine()) != null){
			line = line.trim();
			if (line.isEmpty()) continue;

			String[] parts = line.split("\\s+");
			if (parts.length != 5){
				throw new InvalidScenarioException("Invalid aircraft format: " + line);
			}

			try{
				String type = parts[0];
				String name = parts[1];
				int longitude = Integer.parseInt(parts[2]);
				int latitude = Integer.parseInt(parts[3]);
				int height = Integer.parseInt(parts[4]);

				if (longitude < 0 || latitude < 0 || height < 0 || height > 100){
					throw new InvalidScenarioException("Invalid coordinates for aircraft: " + line);
				}

				Flyable aircraft = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
				aircraft.registerTower(weatherTower);
				aircrafts.add(aircraft);
			} catch (NumberFormatException e){
				throw new InvalidScenarioException("Invalid number format in aircraft data: " + line);
			} catch (IllegalArgumentException e){
				throw new InvalidScenarioException("Unknown aircraft type: " + parts[0]);
			}
		}

		// Run simulation
		for (int i = 0; i < cycles; i++){
			weatherTower.changeWeather();
		}

		reader.close();
	}
}
