package me.wickersty.mortuusterra.managers;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.permission.Permission;
import me.wickersty.mortuusterra.MortuusTerra;

public class PermissionsManager {
	
	private final MortuusTerra instance;

	public static Permission permissions;
	
	public PermissionsManager(MortuusTerra instance) {
		
		this.instance = instance;

		permissions = null;

		initializePermissions();
		
	}

	private void initializePermissions() {
		
		RegisteredServiceProvider<Permission> permissionProvider = instance.getServer().getServicesManager().getRegistration(Permission.class);
		
		if (permissionProvider != null) {

			permissions = (Permission)permissionProvider.getProvider();
			
		}
		
		if (permissions != null) { 
			
			instance.getLogger().info("Permissions Initialized");
			
		} else {
			
			instance.getLogger().warning("Could Not Initialize Permissions");
			
		}
		
	}
	
	public boolean playerHasPermissions(Player player, String permission) {
		
		boolean hasPermissions = false;
		
		if (permissions.has(player, permission)) {
			
			hasPermissions = true;
			
		}
		
		return hasPermissions;		
		
	}
	

}
