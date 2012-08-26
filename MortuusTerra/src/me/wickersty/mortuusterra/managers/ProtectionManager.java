package me.wickersty.mortuusterra.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.ProtectionObject;

public class ProtectionManager {
	
	private final MortuusTerra instance;

	private List<ProtectionObject> protections;

	public Map<Player, Location> protectionMarksOne = new HashMap<Player, Location>();
	public Map<Player, Location> protectionMarksTwo = new HashMap<Player, Location>();

	public ProtectionManager(MortuusTerra instance) {
		
		this.instance = instance;

		protections = new ArrayList<ProtectionObject>();

	}

	public List<ProtectionObject> getProtections() {
		
		return protections;
		
	}

	public ProtectionObject getProtectionObjectByName(String protectionName) {

		ProtectionObject foundProtectionObject = null;
		
		for (ProtectionObject protection : protections) {
			
			if (protection.getProtectionName().equalsIgnoreCase(protectionName)) {
				
				foundProtectionObject = protection;
				
			}
			
		}

		return foundProtectionObject;
		
	}

	public void addProtection(String protectionName, Player player) {
		
		protections.add(new ProtectionObject(protectionName, protectionMarksOne.get(player), protectionMarksTwo.get(player)));
		
	}

	public void removeProtection(String protectionName) {
		
		ProtectionObject protectionObject = getProtectionObjectByName(protectionName);

		protections.remove(protectionObject);
		
	}
	
	public void saveProtectionsToDisk() {
		
		try {

			File protectionsFile = new File(instance.getDataFolder() + File.separator + "protections.txt");
			protectionsFile.delete();
	
			protectionsFile = new File(instance.getDataFolder() + File.separator + "protections.txt");
			protectionsFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving Protections To Disk");

		try {
			
			PrintWriter protectionsFile = new PrintWriter(instance.getDataFolder() + File.separator + "protections.txt");
				
			if (protections.size() > 0) {

				for (ProtectionObject protection : protections) {
					
					String protectionString = protection.getSerializedProtectionObject();
	
					protectionsFile.println(protectionString);
					
				}

			}
			
			protectionsFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadProtectionsFromDisk() {
		
		ensureProtectionsFileExists();
		
		instance.getLogger().info("Loading Protections From Disk");

		try {

			File protectionsFile = new File(instance.getDataFolder() + File.separator + "protections.txt");
			Scanner inputFile = new Scanner(protectionsFile);
			
			while (inputFile.hasNextLine()) {
	
				String protectionString = inputFile.nextLine();
				String[] protectionArray = protectionString.split("~");

				String protectionName = protectionArray[0];
				Location protectionMarkOne = new Location(instance.getServer().getWorld(protectionArray[1]), Double.valueOf(protectionArray[2]), Double.valueOf(protectionArray[3]), Double.valueOf(protectionArray[4]));
				Location protectionMarkTwo = new Location(instance.getServer().getWorld(protectionArray[5]), Double.valueOf(protectionArray[6]), Double.valueOf(protectionArray[7]), Double.valueOf(protectionArray[8]));
	
				protections.add(new ProtectionObject(protectionName, protectionMarkOne, protectionMarkTwo));
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void ensureProtectionsFileExists() {
		
		instance.getLogger().info("Creating Protections File");

		// create file if not exists
		try {
			
			File protectionsFile = new File(instance.getDataFolder() + File.separator + "protections.txt");
	
			if (protectionsFile.exists() == false) {
				
				protectionsFile = new File(instance.getDataFolder() + File.separator + "protections.txt");
				protectionsFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}
	
	public boolean protectionMarksPresent(Player player) {

		boolean marksPresent = false;
		
		Location mark1 = protectionMarksOne.get(player);
		Location mark2 = protectionMarksTwo.get(player);
		
		if (mark1 != null && mark2 != null) {
			
			marksPresent = true;
			
		}

		return marksPresent;
	
	}

	public boolean locationIsProtected(Location location) {

		boolean isProtected = false;
		
		for (ProtectionObject protection : protections) {

			if (protection.isLocationWithinProtection(location) == true) {
				
				isProtected = true;
				
			}
			
		}
		
		return isProtected;
	
	}

	public void informPlayer(Player player) {

		player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "This area is protected.");
		
	}

}
