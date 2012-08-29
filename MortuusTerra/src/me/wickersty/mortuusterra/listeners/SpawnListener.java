package me.wickersty.mortuusterra.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import me.wickersty.mortuusterra.MortuusTerra;

public class SpawnListener implements Listener {

	private final MortuusTerra instance;
	
	public SpawnListener(MortuusTerra instance) { 
	
		this.instance = instance;
	
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void playerDamagesSpawnBlock(BlockDamageEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getBlock().getLocation().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getBlock().getLocation().distance(event.getBlock().getLocation().getWorld().getSpawnLocation()) <= instance.getConfigManager().spawnProtectionRange) {
			
			event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Spawn is protected.");
			
			if (event.getPlayer().isOp() == false) {
				
				event.setCancelled(true);
				
			}
			
		}
		
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void playerDamagesSpawnBlock(BlockPlaceEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getBlock().getLocation().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getBlock().getLocation().distance(event.getBlock().getLocation().getWorld().getSpawnLocation()) <= instance.getConfigManager().spawnProtectionRange) {
			
			event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Spawn is protected.");
			
			if (event.getPlayer().isOp() == false) {
				
				event.setCancelled(true);
				
			}
						
		}
		
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void mobSpawnsInSpawn(CreatureSpawnEvent event) {
		
		Entity entity = event.getEntity();

		if (instance.getConfigManager().isWorldEnabled(entity.getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (entity.getLocation().distance(entity.getWorld().getSpawnLocation()) <= instance.getConfigManager().spawnProtectionRange) {

			// instance.getLogger().info(entity.getClass().toString());
			
			if (
					entity instanceof Blaze || 
					entity instanceof CaveSpider || 
					entity instanceof Chicken || 
					entity instanceof Cow || 
					entity instanceof Creeper || 
					entity instanceof EnderDragon || 
					entity instanceof Enderman || 
					entity instanceof Ghast || 
					entity instanceof Giant || 
					entity instanceof IronGolem || 
					entity instanceof MagmaCube || 
					entity instanceof MushroomCow || 
					entity instanceof Ocelot || 
					entity instanceof Pig || 
					entity instanceof Sheep || 
					entity instanceof Silverfish || 
					entity instanceof Skeleton || 
					entity instanceof Slime || 
					entity instanceof Spider || 
					entity instanceof Squid || 
					entity instanceof Wolf || 
					entity instanceof Zombie 
				) {
				
				// instance.getLogger().info("Cancelling Spawn of: " + entity.toString());
			
				entity.remove();
				
			}
				
		}
		
		
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void mobWalksInSpawn(PlayerMoveEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getPlayer().getLocation().distance(event.getPlayer().getWorld().getSpawnLocation()) <= instance.getConfigManager().spawnProtectionRange) {
			
			for (Entity entity : event.getPlayer().getNearbyEntities(instance.getConfigManager().spawnProtectionRange / 2, instance.getConfigManager().spawnProtectionRange / 2, instance.getConfigManager().spawnProtectionRange / 2)) {
				
				if (
						entity instanceof Blaze || 
						entity instanceof CaveSpider || 
						entity instanceof Chicken || 
						entity instanceof Cow || 
						entity instanceof Creeper || 
						entity instanceof EnderDragon || 
						entity instanceof Enderman || 
						entity instanceof Ghast || 
						entity instanceof Giant || 
						entity instanceof IronGolem || 
						entity instanceof MagmaCube || 
						entity instanceof MushroomCow || 
						entity instanceof Ocelot || 
						entity instanceof Pig || 
						entity instanceof Sheep || 
						entity instanceof Silverfish || 
						entity instanceof Skeleton || 
						entity instanceof Slime || 
						entity instanceof Spider || 
						entity instanceof Squid || 
						entity instanceof Wolf || 
						entity instanceof Zombie 
					) {
				
					entity.remove();
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void playerDamagesSpawnBlock(ExplosionPrimeEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getEntity().getLocation().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getEntity().getLocation().distance(event.getEntity().getLocation().getWorld().getSpawnLocation()) <= instance.getConfigManager().spawnProtectionRange) {
			
			event.setCancelled(true);
				
		}
		
	}

}
