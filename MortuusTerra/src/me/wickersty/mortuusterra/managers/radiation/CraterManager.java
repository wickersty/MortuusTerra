package me.wickersty.mortuusterra.managers.radiation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.bukkit.Location;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.CraterObject;

public class CraterManager {

	private final MortuusTerra instance;
	
	private List<CraterObject> craters;
	
	public CraterManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		craters = new ArrayList<CraterObject>();
		
	}
	
	public List<CraterObject> getCraters() {
		
		return craters;
		
	}
	
	public CraterObject getCraterObjectByLocation(Location location) {
		
		CraterObject foundCrater = null;
		
		for (CraterObject crater : craters) {
			
			if (crater.getCraterLocation().equals(location)) {
				
				foundCrater = crater;
				
			}
			
		}
		
		return foundCrater;
		
	}

	public void addCrater(Location craterLocation) {
		
		Date now = new Date();
		Long craterTime = now.getTime();

		craters.add(new CraterObject(craterLocation, craterTime));
		
	}

	public void removeCrater(Location craterLocation) {
		
		CraterObject craterObject = getCraterObjectByLocation(craterLocation);

		craters.remove(craterObject);
		
	}
	
	public void saveCratersToDisk() {
		
		try {

			File cratersFile = new File(instance.getDataFolder() + File.separator + "craters.txt");
			cratersFile.delete();
	
			cratersFile = new File(instance.getDataFolder() + File.separator + "craters.txt");
			cratersFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving Craters To Disk");

		try {
			
			PrintWriter cratersFile = new PrintWriter(instance.getDataFolder() + File.separator + "craters.txt");
				
			if (craters.size() > 0) {

				for (CraterObject crater : craters) {
					
					String craterString = crater.getSerializedCraterObject();
	
					cratersFile.println(craterString);
					
				}

			}
			
			cratersFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadCratersFromDisk() {
		
		ensureCratersFileExists();
		
		instance.getLogger().info("Loading Craters From Disk");

		try {

			File cratersFile = new File(instance.getDataFolder() + File.separator + "craters.txt");
			Scanner inputFile = new Scanner(cratersFile);
			
			while (inputFile.hasNextLine()) {
	
				String craterString = inputFile.nextLine();
				String[] craterArray = craterString.split("~");

				Location craterLocation = new Location(instance.getServer().getWorld(craterArray[1]), Double.valueOf(craterArray[2]), Double.valueOf(craterArray[3]), Double.valueOf(craterArray[4]));
	
				addCrater(craterLocation);
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void ensureCratersFileExists() {
		
		instance.getLogger().info("Creating Craters File");

		// create file if not exists
		try {
			
			File cratersFile = new File(instance.getDataFolder() + File.separator + "craters.txt");
	
			if (cratersFile.exists() == false) {
				
				cratersFile = new File(instance.getDataFolder() + File.separator + "craters.txt");
				cratersFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
}
