package me.wickersty.mortuusterra.listeners;

import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.GECKObject;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GECKListener implements Listener {

	private final MortuusTerra instance;
	
	public GECKListener(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerChangesGECKPowerStatus(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (event.hasBlock() && event.getClickedBlock().getType().equals(Material.LEVER) && event.getClickedBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SPONGE)) {
			
			Block spongeBlock = event.getClickedBlock().getRelative(BlockFace.DOWN);
			
			if (instance.getGECKManager().getGECKObjectByLocation(spongeBlock.getLocation()) != null) {
				
				// this is a geck.
				GECKObject geck = instance.getGECKManager().getGECKObjectByLocation(spongeBlock.getLocation());
				
				if (geck.isPowered() == true) {
					
					// turning it off
					geck.turnOff();
					
				} else {
					
					// turning it on
					geck.turnOn();
					
				}
				
			}
			
		}
		
	}
	
}
