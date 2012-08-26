package me.wickersty.mortuusterra.managers;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.Drought;

public class WorldManager {

	private final MortuusTerra instance;
	
	public Drought drought;

	public WorldManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		drought = new Drought(instance, false);
		
	}
	
	public void createDrought() {
		
		List<World> worlds = instance.getServer().getWorlds();
		
		for (World world : worlds) {
			
			if (instance.getConfigManager().isWorldEnabled(world.getName()) == true) {

				world.setStorm(false);
				world.setThundering(false);
				
			}
			
		}
		
		drought = new Drought(instance, true);
		
	}

	public void endDrought() {
		
		drought = new Drought(instance, false);
		
	}
	
	public void announceBeginningOfDrought() {
		
		String announceMessage = ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "A drought has begun. It will last for " + drought.droughtLength + " days.";
		
		Player[] players = instance.getServer().getOnlinePlayers();
		
		for (Player player : players) {
			
			if (instance.getConfigManager().isWorldEnabled(player.getWorld().getName()) == true) {
				
				player.sendMessage(announceMessage);
				
			}
			
		}
		
	}
	
	public void announceEndOfDrought() {
		
		String announceMessage = ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "The drought has ended.";
		
		Player[] players = instance.getServer().getOnlinePlayers();
		
		for (Player player : players) {
			
			if (instance.getConfigManager().isWorldEnabled(player.getWorld().getName()) == true) {
				
				player.sendMessage(announceMessage);
				
			}
			
		}
		
	}
	
}
