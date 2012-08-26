package me.wickersty.mortuusterra.objects;

import me.wickersty.mortuusterra.MortuusTerra;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;

public class SupplyDropObject {

	private final MortuusTerra instance;

	private Chest dropChest;
	private Location dropLocation;
	private Inventory dropInventory;
	private boolean isEmpty;
	
	public SupplyDropObject(MortuusTerra instance, Chest dropChest, Location dropLocation, Inventory dropInventory) {
		
		this.instance = instance;
		
		this.dropChest = dropChest;
		this.dropLocation = dropLocation;
		this.dropInventory = dropInventory;
		
		if (dropInventory != null) {

			fillDropChest();
			
			placeDropSign();
			
		}
		
	}
	
	public void fillDropChest() {
		
		dropInventory = instance.getConfigManager().getSupplyDropContents(dropInventory);
		this.isEmpty = false;
		
	}
	
	public void placeDropSign() {
		
		Location signLocation = dropLocation.clone();
		
		signLocation.setX(signLocation.getX() + 1);
		
		Block signBlock = signLocation.getBlock();
		signBlock.setTypeId(63);
		
		Sign sign = (Sign) signBlock.getState();
		sign.setLine(0, "");
		sign.setLine(1, "Supply Drop");
		sign.setLine(2, "-----------");
		sign.setLine(3, "");
		
		sign.update(true);
		
	}
	
	public Chest getDropChest() {
		
		return dropChest;
		
	}
	
	public void setDropChest(Chest dropChest) {
		
		this.dropChest = dropChest;
		
	}
	
	public Inventory getDropInventory() {
		
		return dropInventory;
		
	}
	
	public void setDropInventory(Inventory dropInventory) {
		
		this.dropInventory = dropInventory;
		
	}
	
	public Location getDropLocation() {
		
		return dropLocation;
		
	}
	
	public void setDropLocation(Location dropLocation) {
		
		this.dropLocation = dropLocation;
		
	}
	
	public boolean isEmpty() {
		
		return isEmpty;
		
	}
	
	public void setIsEmpty(boolean isEmpty) {
		
		this.isEmpty = isEmpty;
		
	}
	
}
