package me.wickersty.mortuusterra.objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class GECKObject {

	private Location geckLocation;
	boolean isPowered;
	
	public GECKObject(Location geckLocation) {
		
		this.geckLocation = geckLocation;
		
		this.isPowered = false;
			
	}
	
	public void turnOff() {
		
		Block underneathSponge = geckLocation.getBlock().getRelative(BlockFace.DOWN);
		underneathSponge.getRelative(BlockFace.EAST).setType(Material.STONE);
		underneathSponge.getRelative(BlockFace.WEST).setType(Material.STONE);
		underneathSponge.getRelative(BlockFace.NORTH).setType(Material.STONE);
		underneathSponge.getRelative(BlockFace.SOUTH).setType(Material.STONE);
		
	}
	
	public void turnOn() {
		
		Block underneathSponge = geckLocation.getBlock().getRelative(BlockFace.DOWN);
		underneathSponge.getRelative(BlockFace.EAST).setType(Material.GLOWSTONE);
		underneathSponge.getRelative(BlockFace.WEST).setType(Material.GLOWSTONE);
		underneathSponge.getRelative(BlockFace.NORTH).setType(Material.GLOWSTONE);
		underneathSponge.getRelative(BlockFace.SOUTH).setType(Material.GLOWSTONE);
		
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
