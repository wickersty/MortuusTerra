package me.wickersty.mortuusterra.objects;

import org.bukkit.Location;

public class GECKObject {

	private Location geckLocation;
	boolean isPowered;
	
	public GECKObject(Location geckLocation) {
		
		this.geckLocation = geckLocation;
		
		this.isPowered = false;
			
	}
	
	public void turnOff() {
		
		
	}
	
	public void turnOn() {
		
		
	}

	public Location getGECKLocation() {

		return geckLocation;
	
	}

	public String getSerializedGECKObject() {

		return geckLocation.getWorld().getName() + "~" + geckLocation.getX() + "~" + geckLocation.getY() + "~" + geckLocation.getZ(); 
	
	}
	
	public boolean isPowered() {
		
		if (geckLocation.getBlock().isBlockIndirectlyPowered() == true) {
			
			setIsPowered(true);
			
		} else {
			
			setIsPowered(false);
			
		}
		
		return isPowered;
		
	}
	
	public void setIsPowered(boolean isPowered) {
		
		this.isPowered = isPowered;
		
	}

}
