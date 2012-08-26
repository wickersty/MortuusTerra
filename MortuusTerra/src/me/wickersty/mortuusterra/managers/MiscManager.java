package me.wickersty.mortuusterra.managers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.MortuusTerra;

public class MiscManager {
	
	private final MortuusTerra instance;
	
	public MiscManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	public void fixStreetlights(Player player, Location location) {

		int lightsFixed = 0;

		instance.getLogger().info("Fixing Street Lamps");
		
		for (double x = location.getX() - 50.0; x <= location.getX() + 50.0; x += 1.0) {
			
			for (double y = location.getY() - 50.0; y <= location.getY() + 50.0; y += 1.0) {
				
				for (double z = location.getZ() - 50.0; z <= location.getZ() + 50.0; z += 1.0) {
					
					Location currentLocation = new Location(location.getWorld(), x, y, z);
					
					if (currentLocation.getBlock().getType().equals(Material.GLOWSTONE)) {
						
						Block redstoneLamp = currentLocation.getBlock();
						Block fence1 = redstoneLamp.getRelative(BlockFace.DOWN);
						Block fence2 = fence1.getRelative(BlockFace.DOWN);
						
						if (fence1.getType().equals(Material.FENCE) && fence2.getType().equals(Material.FENCE)) {
							
							redstoneLamp.setType(Material.REDSTONE_LAMP_OFF);
							redstoneLamp.getRelative(BlockFace.UP).setType(Material.LEVER);
							redstoneLamp.getRelative(BlockFace.UP).setData((byte) 5);
							
							lightsFixed++;
							
						}
						
					}
					
				}
				
			}
			
		}
		
		instance.getLogger().info("Total Streetlights Fixed: " + lightsFixed);
		
		player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Total Street Lamps Fixed: " + lightsFixed);
		
	}

	public void clearDrops(Player player, World world) {

		for (Entity e : world.getEntities()) {
			
			if (e instanceof Item) {
				
				e.remove();
				
			}
			
		}
	
		player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Drops Removed");
		
	}
	
}
