package me.wickersty.mortuusterra.listeners;

import me.wickersty.mortuusterra.MortuusTerra;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ProtectionListener implements Listener {

	private final MortuusTerra instance;
	
	public ProtectionListener(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerDestroysBlock(BlockBreakEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (instance.getProtectionManager().locationIsProtected(event.getBlock().getLocation()) == true) {
			
			instance.getProtectionManager().informPlayer(event.getPlayer());
			
			if (event.getPlayer().isOp() == false && !(instance.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.admin")))  {

				event.setCancelled(true);
				
			}
			
		}
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void playerPlacesBlock(BlockPlaceEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (instance.getProtectionManager().locationIsProtected(event.getBlock().getLocation()) == true) {
			
			instance.getProtectionManager().informPlayer(event.getPlayer());
			
			if (event.getPlayer().isOp() == false && !(instance.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.admin")))  {

				event.setCancelled(true);
				
			}
			
		}
		
	}
	
}
