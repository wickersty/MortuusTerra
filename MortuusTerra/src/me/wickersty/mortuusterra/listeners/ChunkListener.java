package me.wickersty.mortuusterra.listeners;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import me.wickersty.mortuusterra.MortuusTerra;

public class ChunkListener implements Listener {

	private final MortuusTerra instance;
	
	public ChunkListener(MortuusTerra instance) {
		
		this.instance = instance;
				
	}

	@EventHandler(priority=EventPriority.LOW)
	public void chunkLoad(ChunkLoadEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.isNewChunk() == false) {
			
			return;
			
		}
		
		Chunk chunk = event.getChunk();
		int x = chunk.getX() * 16;
		int z = chunk.getZ() * 16;
		int y = chunk.getWorld().getHighestBlockYAt(x + 8, z + 2);

		Location eventLocation = new Location(event.getWorld(), x + 8, y, z + 8);

		if (instance.getConfigManager().generateFalloutSheltersEnabled == true) {

			if (Math.random() < (instance.getConfigManager().generateFalloutSheltersChance / 10)) {
				
				placeFalloutShelter(eventLocation);
				
			}
			
		}
		
		if (instance.getConfigManager().generateCratersEnabled == true) {

			if (Math.random() < (instance.getConfigManager().generateCratersChance / 10)) {
				
				placeCrater(eventLocation);
				
			}

		}

	}

	public void placeFalloutShelter(Location falloutShelterLocation) {
		
		int falloutShelterLocationY = falloutShelterLocation.getWorld().getHighestBlockYAt(falloutShelterLocation);
		
		falloutShelterLocation.setY(falloutShelterLocationY);
	
		instance.getFalloutShelterManager().addFalloutShelter(falloutShelterLocation);
		
	}
	
	public void placeCrater(Location craterLocation) {
		
		int craterLocationY = craterLocation.getWorld().getHighestBlockYAt(craterLocation);
		
		craterLocation.setY(craterLocationY);
		
		// craterLocation.getWorld().createExplosion(craterLocation, 1);
		
		instance.getCraterManager().addCrater(craterLocation);
		
	}

}
