package me.wickersty.mortuusterra.managers.radiation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.GECKObject;

public class GECKManager {

	private final MortuusTerra instance;
	
	private List<GECKObject> gecks;
	
	public GECKManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		gecks = new ArrayList<GECKObject>();
		
	}
	
	public List<GECKObject> getGECKs() {
		
		return gecks;
		
	}
	
	public Integer getActiveGECKs() {
		
		Integer activeGECKs = 0;
		
		for (GECKObject geck : gecks) {
			
			if (geck.isPowered() == true) {
				
				activeGECKs++;
				
			}
			
		}
		
		return activeGECKs;
		
	}
	
	public GECKObject getGECKObjectByLocation(Location location) {
		
		GECKObject foundGECK = null;
		
		for (GECKObject geck : gecks) {
			
			if (geck.getGECKLocation().equals(location)) {
				
				foundGECK = geck;
				
			}
			
		}
		
		return foundGECK;
		
	}

	public void addGECK(Location geckLocation) {
		
		gecks.add(new GECKObject(geckLocation));
		
	}

	public void removeGECK(Location geckLocation) {
		
		GECKObject geckObject = getGECKObjectByLocation(geckLocation);

		gecks.remove(geckObject);
		
	}
	
	public void saveGECKsToDisk() {
		
		try {

			File gecksFile = new File(instance.getDataFolder() + File.separator + "gecks.txt");
			gecksFile.delete();
	
			gecksFile = new File(instance.getDataFolder() + File.separator + "gecks.txt");
			gecksFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving GECKs To Disk");

		try {
			
			PrintWriter gecksFile = new PrintWriter(instance.getDataFolder() + File.separator + "gecks.txt");
				
			if (gecks.size() > 0) {

				for (GECKObject geck : gecks) {
					
					String geckString = geck.getSerializedGECKObject();
	
					gecksFile.println(geckString);
					
				}

			}
			
			gecksFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadGECKsFromDisk() {
		
		ensureGECKsFileExists();
		
		instance.getLogger().info("Loading GECKs From Disk");

		try {

			File gecksFile = new File(instance.getDataFolder() + File.separator + "gecks.txt");
			Scanner inputFile = new Scanner(gecksFile);
			
			while (inputFile.hasNextLine()) {
	
				String geckString = inputFile.nextLine();
				String[] geckArray = geckString.split("~");

				Location geckLocation = new Location(instance.getServer().getWorld(geckArray[0]), Double.valueOf(geckArray[1]), Double.valueOf(geckArray[2]), Double.valueOf(geckArray[3]));
	
				gecks.add(new GECKObject(geckLocation));
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void ensureGECKsFileExists() {
		
		instance.getLogger().info("Creating GECKs File");

		// create file if not exists
		try {
			
			File gecksFile = new File(instance.getDataFolder() + File.separator + "gecks.txt");
	
			if (gecksFile.exists() == false) {
				
				gecksFile = new File(instance.getDataFolder() + File.separator + "gecks.txt");
				gecksFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}

	/*
	 * Validates the multi-block object at the location is a correctly built GECK
	 */
	public boolean isValidGECK(Location location) {

		boolean isValid = true;
		
		Block lever = location.getBlock();

		Block sponge = lever.getRelative(BlockFace.DOWN);
		Block piston1 = sponge.getRelative(BlockFace.EAST);
		Block piston2 = sponge.getRelative(BlockFace.WEST);
		Block piston3 = sponge.getRelative(BlockFace.NORTH);
		Block piston4 = sponge.getRelative(BlockFace.SOUTH);
				
		if (sponge.getType().equals(Material.SPONGE)) { } else {
			
			isValid = false;
			
		}
		
		if (piston1.getType().equals(Material.PISTON_BASE) && piston2.getType().equals(Material.PISTON_BASE) && piston3.getType().equals(Material.PISTON_BASE) && piston4.getType().equals(Material.PISTON_BASE)) { } else {
			
			isValid = false;
			
		}
		
		return isValid;
	
	}

	public boolean isLocationProtectedByGECK(Location playerLocation) {
		
		boolean isProtected = false;
		
		for (GECKObject geck : gecks) {
			
			if (geck.getGECKLocation().getWorld().equals(playerLocation.getWorld()) && geck.getGECKLocation().distance(playerLocation) <= 15 && geck.isPowered() == true) {
				
				isProtected = true;
				
			}
			
		}
		
		return isProtected;
		
	}
	
}
