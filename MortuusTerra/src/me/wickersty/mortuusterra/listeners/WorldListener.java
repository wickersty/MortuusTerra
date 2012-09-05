package me.wickersty.mortuusterra.listeners;

// import info.hawksharbor.LoreTime.events.DateChangeEvent;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.events.MTTimerEvent;
import me.wickersty.mortuusterra.objects.SupplyDropObject;

public class WorldListener implements Listener {

	private final MortuusTerra instance;

	private Long lastTimestamp;
	public Integer minecraftQuarterDaysPassed;
	public Integer minecraftFullDaysPassed;
	
	public WorldListener(MortuusTerra instance) {
		
		this.instance = instance;
		this.lastTimestamp = Long.valueOf(0);
		this.minecraftQuarterDaysPassed = 0;
		this.minecraftFullDaysPassed = 0;
		
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void weatherChanges(WeatherChangeEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (instance.getConfigManager().droughtsEnabled == false) {
			
			return;
			
		}
		
		if (instance.getWorldManager().drought.getDroughtActive() == false) {
			
			return;
			
		}
		
		// cancel rain due to drought
		event.setCancelled(true);
		
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void cropGrows(BlockGrowEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getBlock().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (instance.getConfigManager().droughtsEnabled == false) {
			
			return;
			
		}
		
		if (instance.getWorldManager().drought.getDroughtActive() == false) {
			
			return;
			
		}
		
		// cancel crop growth due to drought
		event.setCancelled(true);
		
		// drop crop
		event.getBlock().breakNaturally();
		
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void treeGrows(StructureGrowEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getWorld().getName()) == false) {
			
			return;
			
		}

		if (instance.getConfigManager().droughtsEnabled == false) {
			
			return;
			
		}
		
		if (instance.getWorldManager().drought.getDroughtActive() == false) {
			
			return;
			
		}
		
		// cancel tree growth due to drought
		event.setCancelled(true);
		
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void checkTimestamp(PlayerMoveEvent event) {
		
		// 24000 ticks in a minecraft-day
		// 20 IRL minutes in a minecraft day
		// 6000 ticks in 5 IRL minutes
		
		// 72000 ticks IRL hour
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
				
		// if less than 5 IRL minutes have passed, abort and let at least 5 minutes acrue
		if (event.getPlayer().getWorld().getFullTime() - lastTimestamp < 6000) {
			
			return;
			
		}

		// Create the timer event here
		MTTimerEvent timerEvent = new MTTimerEvent(instance);
		timerEvent.setWorld(event.getPlayer().getWorld());
		
		// full day passed?
		if (event.getPlayer().getWorld().getFullTime() < lastTimestamp) {
			
			timerEvent.setFullDay(true);

			minecraftFullDaysPassed++;
						
		}		

		timerEvent.setQuarterDay(true);	

		minecraftQuarterDaysPassed++;
		
		
		if (minecraftQuarterDaysPassed == 4) {
			
			timerEvent.setFullDay(true);

			minecraftQuarterDaysPassed = 0;
			
		}

		
		
		// Call the event
	    instance.getServer().getPluginManager().callEvent(timerEvent);

		
		lastTimestamp = event.getPlayer().getWorld().getFullTime();
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void playerDestroysSupplyDrop(BlockBreakEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;
			
		}
		
		if (!(event.getBlock().getState().toString().contains("CraftChest"))) {
			
			return;
			
		}
		
		SupplyDropObject supplyDrop = instance.getSupplyDropManager().getSupplyDropByLocation(event.getBlock().getLocation());
		
		if (supplyDrop == null) {
			
			return;
			
		}
		
		supplyDrop.setIsEmpty(true);
		instance.getSupplyDropManager().removeSupplyDrop(supplyDrop.getDropLocation());
		instance.getLogger().info("Removing Supply Drop At: " + supplyDrop.getDropLocation().toString());		
		
	}

	
	@EventHandler(priority=EventPriority.LOWEST)
	public void timerEvent(MTTimerEvent event) {

		if (event.quarterDay == true) {
			
			instance.getLogger().info("MTTimerEvent: Quarter Minecraft Day Passes");
			
			if (instance.getConfigManager().supplyDropsEnabled == true && instance.getConfigManager().supplyDropsChance > 0) {
			
				for (World world : instance.getServer().getWorlds()) {
					
					if (instance.getConfigManager().isWorldEnabled(world.getName()) == true) {
						
						double ran = Math.random();
						
						if (ran < instance.getConfigManager().supplyDropsChance) {
		
							deliverSupplyDrop(world);
							
						}
						
					}
					
				}
				
			}
			
		}		
		
		if (event.fullDay == true) {
			
			instance.getLogger().info("MTTimerEvent: Full Minecraft Day Passes");
			
			// a full minecraft day has passed
			minecraftDayPasses();
			
			minecraftQuarterDaysPassed = 0;
			minecraftFullDaysPassed = 0;
			
		}
		
	}

	public void deliverSupplyDrop(World world) {
				
		Random ran = new Random();
		
		double x = ran.nextInt(500);
		double y = 0.0;
		double z = ran.nextInt(500);
		
		Location dropLocation = new Location(world, x, y, z);
		dropLocation.setY(world.getHighestBlockYAt(dropLocation));
		
		dropLocation.getBlock().setType(Material.CHEST);
				
		Chest dropChest = (Chest) dropLocation.getBlock().getState();

		SupplyDropObject supplyDrop = new SupplyDropObject(instance, dropChest, dropChest.getLocation(), dropChest.getBlockInventory());
		
		instance.getSupplyDropManager().addSupplyDrop(supplyDrop);
		instance.getLogger().info("Adding Supply Drop At: " + supplyDrop.getDropLocation().toString());

		for (Player player : world.getPlayers()) {
			
			player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]");
			player.sendMessage(ChatColor.WHITE + "[Emergency Alert] " + ChatColor.GRAY + "Supply Drop Made at " + dropLocation.getX() + "," + dropLocation.getY() + "," + dropLocation.getZ());
			player.sendMessage(ChatColor.GRAY + "Use your compass to find the supply drop. Right-click to target the signal.");
			
		}

	}

	public void minecraftDayPasses() {
		
		minecraftQuarterDaysPassed = 0;
			
		if (instance.getConfigManager().droughtsEnabled == true) {
			
			if (instance.getWorldManager().drought.getDroughtActive() == false) {
				
				instance.getLogger().info("Checking if Drought Begins");
				checkIfDroughtBegins();
				
			} else {
			
				instance.getLogger().info("Checking if Drought Ends");
				checkIfDroughtEnds();
				
			}

		}
				
	}
	
	public void checkIfDroughtBegins() {
		
		if (Math.random() < instance.getConfigManager().droughtsChance) {
			
			instance.getWorldManager().createDrought();
			
			minecraftQuarterDaysPassed = 0;
			minecraftFullDaysPassed = 0;
			
		}
		
	}

	public void checkIfDroughtEnds() {
		
		// another day since drought began
		instance.getWorldManager().drought.daysSinceDrought++;

		// if drought length is same as days passed, end drought
		if (instance.getWorldManager().drought.getDroughtLength() >= instance.getWorldManager().drought.daysSinceDrought) {
			
			instance.getWorldManager().drought.endDrought();
			
			minecraftQuarterDaysPassed = 0;
			minecraftFullDaysPassed = 0;
			
		}
		
	}

}
