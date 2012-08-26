package me.wickersty.mortuusterra.objects;

import org.bukkit.Location;
import org.bukkit.World;

public class CraterObject {

	private Location craterLocation;
	private Long craterTime;
	
	public CraterObject(Location craterLocation, Long craterTime) {
		
		this.craterLocation = craterLocation;
		this.craterTime = craterTime;
		
	}
	
	public String getSerializedCraterObject() {
		
		return craterTime + "~" + craterLocation.getWorld().getName() + "~" + craterLocation.getX() + "~" + craterLocation.getY() + "~" + craterLocation.getZ();
		
	}
	
	public Location getCraterLocation() {

		return craterLocation;
	
	}
	
	public void setCraterLocation(Location craterLocation) {
		
		this.craterLocation = craterLocation;
		
	}
	
	public World getWorld() {
		
		return craterLocation.getWorld();
		
	}
	
	public Long getTime() {
		
		return craterTime;
		
	}

}
