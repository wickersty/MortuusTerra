package me.wickersty.mortuusterra.objects;

import org.bukkit.Location;

public class ProtectionObject {
	
	String protectionName;
	Location protectionMark1;
	Location protectionMark2;
	
	public ProtectionObject(String protectionName, Location protectionMark1, Location protectionMark2) {
		
		this.protectionName = protectionName;
		this.protectionMark1 = protectionMark1;
		this.protectionMark2 = protectionMark2;
		
	}
	
	public String getSerializedProtectionObject() {

		String serializedObject = protectionName + "~";
	
		serializedObject += protectionMark1.getWorld().getName() + "~" + protectionMark1.getX() + "~" + protectionMark1.getY() + "~" + protectionMark1.getZ() + "~";
		serializedObject += protectionMark2.getWorld().getName() + "~" + protectionMark2.getX() + "~" + protectionMark2.getY() + "~" + protectionMark2.getZ();
		
		return serializedObject;
		
	}

	public String getProtectionName() {
		
		return protectionName;
		
	}
	
	public Location getProtectionMark1() {
		
		return protectionMark1;
		
	}

	public Location getProtectionMark2() {
		
		return protectionMark2;
		
	}
	
	public boolean isLocationWithinProtection(Location location) {
		
		boolean withinProtection = false;
		
		boolean withinX;					
		if ((location.getX() >= protectionMark1.getX() && location.getX() <= protectionMark2.getX()) || (location.getX() <= protectionMark1.getX() && location.getX() >= protectionMark2.getX())) {

			withinX = true;
			
		} else {
			
			withinX = false;
			
		}
		
		boolean withinY;
		if ((location.getY() >= protectionMark1.getY() && location.getY() <= protectionMark2.getY()) || (location.getY() <= protectionMark1.getY() && location.getY() >= protectionMark2.getY())) {

			withinY = true;
			
		} else {
			
			withinY = false;
			
		}
		
		boolean withinZ;
		if ((location.getZ() >= protectionMark1.getZ() && location.getZ() <= protectionMark2.getZ()) || (location.getZ() <= protectionMark1.getZ() && location.getZ() >= protectionMark2.getZ())) {
			
			withinZ = true;
			
		} else {
			
			withinZ = false;
			
		}		
		
		if (withinX == true && withinY == true && withinZ == true) {
			
			withinProtection = true;
			
		}
		
		return withinProtection;
		
	}

}
