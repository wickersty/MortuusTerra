package me.wickersty.mortuusterra.managers.radiation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bukkit.Location;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.FalloutShelterObject;

public class FalloutShelterManager {

	private final MortuusTerra instance;
	
	private List<FalloutShelterObject> falloutShelters;
	
	public FalloutShelterManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		falloutShelters = new ArrayList<FalloutShelterObject>();
		
	}
	
	public FalloutShelterObject getFalloutShelterObjectByLocation(Location location) {
		
		FalloutShelterObject foundFalloutShelter = null;
		
		for (FalloutShelterObject falloutShelter : falloutShelters) {
			
			if (falloutShelter.getFalloutShelterLocation().equals(location)) {
				
				foundFalloutShelter = falloutShelter;
				
			}
			
		}
		
		return foundFalloutShelter;
		
	}

	public void addFalloutShelter(Location falloutShelterLocation) {
		
		falloutShelters.add(new FalloutShelterObject(falloutShelterLocation, true));

	}

	public void removeFalloutShelter(Location falloutShelterLocation) {
		
		FalloutShelterObject falloutShelterObject = getFalloutShelterObjectByLocation(falloutShelterLocation);

		falloutShelters.remove(falloutShelterObject);
		
	}
	
	public void saveFalloutSheltersToDisk() {
		
		try {

			File falloutSheltersFile = new File(instance.getDataFolder() + File.separator + "fallout-shelters.txt");
			falloutSheltersFile.delete();
	
			falloutSheltersFile = new File(instance.getDataFolder() + File.separator + "fallout-shelters.txt");
			falloutSheltersFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving Fallout Shelters To Disk");

		try {
			
			PrintWriter falloutSheltersFile = new PrintWriter(instance.getDataFolder() + File.separator + "fallout-shelters.txt");
				
			if (falloutShelters.size() > 0) {

				for (FalloutShelterObject falloutShelter : falloutShelters) {
					
					String falloutShelterString = falloutShelter.getSerializedFalloutShelterObject();
	
					falloutSheltersFile.println(falloutShelterString);
					
				}

			}
			
			falloutSheltersFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadFalloutSheltersFromDisk() {
		
		ensureFalloutSheltersFileExists();
		
		instance.getLogger().info("Loading Fallout Shelters From Disk");

		try {

			File falloutSheltersFile = new File(instance.getDataFolder() + File.separator + "fallout-shelters.txt");
			Scanner inputFile = new Scanner(falloutSheltersFile);
			
			while (inputFile.hasNextLine()) {
	
				String falloutShelterString = inputFile.nextLine();
				String[] falloutShelterArray = falloutShelterString.split("~");

				Location falloutShelterLocation = new Location(instance.getServer().getWorld(falloutShelterArray[0]), Double.valueOf(falloutShelterArray[1]), Double.valueOf(falloutShelterArray[2]), Double.valueOf(falloutShelterArray[3]));
	
				falloutShelters.add(new FalloutShelterObject(falloutShelterLocation, false));
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void ensureFalloutSheltersFileExists() {
		
		instance.getLogger().info("Creating Fallout Shelters File");

		// create file if not exists
		try {
			
			File falloutSheltersFile = new File(instance.getDataFolder() + File.separator + "fallout-shelters.txt");
	
			if (falloutSheltersFile.exists() == false) {
				
				falloutSheltersFile = new File(instance.getDataFolder() + File.separator + "fallout-shelters.txt");
				falloutSheltersFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
}
