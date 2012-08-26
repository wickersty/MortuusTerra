package me.wickersty.mortuusterra.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.SupplyDropObject;

public class SupplyDropManager {
	
	private final MortuusTerra instance;
	
	private List<SupplyDropObject> supplyDrops;
	
	public SupplyDropManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		supplyDrops = new ArrayList<SupplyDropObject>();
		
		initializeData();
		
	}
	
	public void initializeData() {
		
		try {
			
			File myFile = new File(instance.getDataFolder() + File.separator + "supply-drops.txt");
	
			if (myFile.exists() == false) {
				
				myFile = new File(instance.getDataFolder() + File.separator + "supply-drops.txt");
				myFile.createNewFile();
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void loadData() {

		instance.getLogger().info("[Mortuus Terra] Loading Supply Drops");

		try {

			File dataFile = new File(instance.getDataFolder() + File.separator + "supply-drops.txt");
			Scanner inputFile = new Scanner(dataFile);
			
			while (inputFile.hasNextLine()) {
	
				String dataString = inputFile.nextLine();
				String[] dataArray = dataString.split("~");

				Location dataLocation = new Location(instance.getServer().getWorld(dataArray[0]), Double.valueOf(dataArray[1]), Double.valueOf(dataArray[2]), Double.valueOf(dataArray[3]));
	
				Chest chestBlock = (Chest) dataLocation.getBlock().getState();
				
				addSupplyDrop(new SupplyDropObject(instance, chestBlock, dataLocation, chestBlock.getBlockInventory()));
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void saveData() {
		
		instance.getLogger().info("[Mortuus Terra] Saving Supply Drops");
		
		try {

			File dataFile = new File(instance.getDataFolder() + File.separator + "supply-drops.txt");
			dataFile.delete();
	
			dataFile = new File(instance.getDataFolder() + File.separator + "supply-drops.txt");
			dataFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		try {
			
			PrintWriter dataFile = new PrintWriter(instance.getDataFolder() + File.separator + "supply-drops.txt");
				
			if (supplyDrops.size() > 0) {

				for (SupplyDropObject supplyDrop : supplyDrops) {
					
					String dataString = supplyDrop.getDropLocation().getWorld().getName() + "~" + supplyDrop.getDropLocation().getX() + "~" + supplyDrop.getDropLocation().getY() + "~" + supplyDrop.getDropLocation().getZ();
	
					dataFile.println(dataString);
					
				}

			}
			
			dataFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}	
		
	public SupplyDropObject getSupplyDropByLocation(Location location) {
		
		SupplyDropObject foundSupplyDrop = null;
		
		for (SupplyDropObject supplyDrop : supplyDrops) {
			
			if (supplyDrop.getDropLocation().equals(location)) {
				
				foundSupplyDrop = supplyDrop;
				
				break;
				
			}
			
		}
		
		return foundSupplyDrop;
		
	}
	
	public SupplyDropObject getNearestSupplyDrop(Location location) {

		Double shortestDistance = -1.0;
		SupplyDropObject closestSupplyDrop = null;
		
		for (SupplyDropObject supplyDrop : supplyDrops) {
			
			if ((supplyDrop.getDropLocation().distance(location) < shortestDistance || shortestDistance == -1.0) && supplyDrop.isEmpty() == false) {
				
				closestSupplyDrop = supplyDrop;
				
			}
			
		}
		
		return closestSupplyDrop;
		
	}
	
	public void checkSupplyDropContents(Location supplyDropLocation) {
		
		boolean supplyDropIsEmpty = true;

		SupplyDropObject supplyDrop = getSupplyDropByLocation(supplyDropLocation);
		
		if (supplyDrop == null) {
			
			return;
			
		}
		
		for (int i = 0; i < supplyDrop.getDropInventory().getSize(); i++) {
			
			if (supplyDrop.getDropInventory().getItem(i) == null) { } else { supplyDropIsEmpty = false; }

		}
		
		if (supplyDropIsEmpty == true) {
			
			supplyDrops.remove(supplyDrop);
			
		}
		
	}
	
	public void addSupplyDrop(SupplyDropObject supplyDrop) {
		
		supplyDrops.add(supplyDrop);
		
	}
	
	public void removeSupplyDrop(Location location) {
		
		SupplyDropObject supplyDrop = getSupplyDropByLocation(location);
		
		supplyDrops.remove(supplyDrop);
		
	}
	

}
