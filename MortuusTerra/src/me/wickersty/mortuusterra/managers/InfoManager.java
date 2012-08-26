package me.wickersty.mortuusterra.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.PlayerObject;

public class InfoManager {

	private final MortuusTerra instance;

	public InfoManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	public void displayPlayerInfo(Player player) {
		
		PlayerObject playerObject = instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName());
		
		Integer health = playerObject.health;
		Integer hunger = playerObject.hunger;
		Integer thirst = playerObject.thirst;
		
		Double x = player.getLocation().getX();
		Double y = player.getLocation().getY();
		Double z = player.getLocation().getZ();
		
		Integer xI = x.intValue();
		Integer yI = y.intValue();
		Integer zI = z.intValue();
		
		Double radiationLevel = instance.getRadiationManager().calculateRadiationDamage(player);
		String playerCoords = xI + "," + yI + "," + zI;

		String drought = "";
		if (instance.getWorldManager().drought.getDroughtActive() == true) {

			drought = (instance.getWorldManager().drought.droughtLength - instance.getWorldManager().drought.daysSinceDrought + 1) + " Days Remaining";
			
		} else {
			
			drought = "None";
			
		}

		
		// display player info
		player.sendMessage(ChatColor.DARK_RED + player.getName());
		player.sendMessage(ChatColor.WHITE + "Health: " + ChatColor.GRAY + health + ChatColor.WHITE + " | Hunger: " + ChatColor.GRAY + hunger + ChatColor.WHITE + " | Thirst: " + ChatColor.GRAY + thirst);
		player.sendMessage(ChatColor.WHITE + "Radiation: " + ChatColor.GRAY + radiationLevel + ChatColor.WHITE + " | Position: " + ChatColor.GRAY + playerCoords);
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_RED + "Effects:");

		for (PotionEffectType effect: playerObject.effects) {

			player.sendMessage(ChatColor.GRAY + effect.getName());
			
		}
		
		player.sendMessage("");
		player.sendMessage(ChatColor.DARK_RED + "Environment:");
		player.sendMessage(ChatColor.WHITE + "Drought: " + ChatColor.GRAY + drought);
		
	}
	
	
}
