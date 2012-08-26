package me.wickersty.mortuusterra.managers;

import java.util.Date;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.events.EntityRadiationDamageEvent;
import me.wickersty.mortuusterra.objects.CraterObject;

public class RadiationManager {

	private final MortuusTerra instance;
	
	public RadiationManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	public void radiationDamageReceived(Player player) {
		
		double radiationDamageReceived = calculateRadiationDamage(player);
		
	    // Create the event here
		EntityRadiationDamageEvent event = new EntityRadiationDamageEvent(player, radiationDamageReceived);

		// Call the event
	    instance.getServer().getPluginManager().callEvent(event);
		
	}
	
	public double calculateRadiationDamage(Player player) {

		Double radDamageReceived = 0.0;
		
		if (instance.getConfigManager().isWorldEnabled(player.getWorld().getName()) == false) {
			
			return 0.0;
			
		}
		
		if (instance.getConfigManager().radiationEnabled == true) {
			
			double radiationAtCore = 100000000;
									
			Date now = new Date();

			for (CraterObject crater : instance.getCraterManager().getCraters()) {
				
				if (crater.getWorld().getName().equals(player.getWorld().getName())) {
					
					Long history = Long.valueOf(now.getTime() - crater.getTime());
					double craterAgeInDays = history / 24000;
					
					// There are 72 Minecraft days in one RL Day
					// Therefore craterAgeInDays / 72 = RL Days since crater spawned
					// 30 RL Days = 2160 MC Days
					// So for easy math, let's say craters deteriorate after 2,000 MC Days
					//
					// So... 2,000 / 2 = 1,000. 1,000 / 10 = 100. 
					// Therefore: percentageOfRadiationStillInEffect = 100 - ((craterAgeInDays / 2) / 10)
					double percentageOfRadiationStillInEffect = ((craterAgeInDays / 2) / 10);
					
					Double distanceFromCrater = crater.getCraterLocation().distanceSquared(player.getLocation());
					distanceFromCrater = distanceFromCrater.doubleValue(); 

					// instance.getLogger().info(history + " " + craterAgeInDays + " " + percentageOfRadiationStillInEffect + "%");
					double totalRadiationAdjustment = distanceFromCrater * percentageOfRadiationStillInEffect;

					radDamageReceived = (radiationAtCore / totalRadiationAdjustment);
					radDamageReceived = Math.abs(radDamageReceived);
					
					radDamageReceived = Math.log(radDamageReceived);

					// instance.getLogger().info(Math.abs(radDamageReceived) + " Rads");
																				
				}
				
			}
						
			if (radDamageReceived < 0.0) {
				
				radDamageReceived = 0.0;
				
			}
			
		}

		if (player.getWorld().hasStorm() == true || player.getWorld().isThundering() == true) {
			
			radDamageReceived += instance.getConfigManager().radiationDamageIncreaseFromStorms;
			
		}
			
		if (isPlayerInside(player) == true) {
			
			radDamageReceived = 0.0;
			
		}
		
		if ((player.getLocation().getBlock().getType() == Material.WATER) || (player.getLocation().getBlock().getType() == Material.STATIONARY_WATER)) {
			
			radDamageReceived += instance.getConfigManager().radiationDamageIncreaseFromWater;
			
			
		}

		if (radDamageReceived > instance.getConfigManager().radiationMax) {
			
			radDamageReceived = instance.getConfigManager().radiationMax;
			
		}

		// instance.getLogger().info("GECK: " + instance.getGECKManager().isLocationProtectedByGECK(player.getLocation()));
		if (instance.getGECKManager().isLocationProtectedByGECK(player.getLocation()) == true) {
			
			radDamageReceived = 0.0;
			
		}

		return radDamageReceived;
		
	}
	
	public boolean armorAbsorbsRadiation(Player player) {
		
		boolean armorAbsorbs = false;
		
		PlayerInventory inventory = player.getInventory();
		ItemStack[] armor = inventory.getArmorContents();
		
		int whichArmor = (int)(Math.random() * armor.length);
		
		if (armor[whichArmor].getAmount() == 0) {
			
			armorAbsorbs = false;
			
		} else {
			
			armorAbsorbs = true;
			
			armor[whichArmor].setDurability((short)(armor[whichArmor].getDurability() + 2));
			
		}

		if ((armor[whichArmor].getType() == Material.LEATHER_HELMET) && (armor[whichArmor].getDurability() > 56))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.LEATHER_CHESTPLATE) && (armor[whichArmor].getDurability() > 81))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.LEATHER_LEGGINGS) && (armor[whichArmor].getDurability() > 76))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.LEATHER_BOOTS) && (armor[whichArmor].getDurability() > 66))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.GOLD_HELMET) && (armor[whichArmor].getDurability() > 78))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.GOLD_CHESTPLATE) && (armor[whichArmor].getDurability() > 113))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.GOLD_LEGGINGS) && (armor[whichArmor].getDurability() > 106))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.GOLD_BOOTS) && (armor[whichArmor].getDurability() > 92))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.IRON_HELMET) && (armor[whichArmor].getDurability() > 166))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.IRON_CHESTPLATE) && (armor[whichArmor].getDurability() > 241))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.IRON_LEGGINGS) && (armor[whichArmor].getDurability() > 226))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.IRON_BOOTS) && (armor[whichArmor].getDurability() > 196))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.CHAINMAIL_HELMET) && (armor[whichArmor].getDurability() > 166))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.CHAINMAIL_CHESTPLATE) && (armor[whichArmor].getDurability() > 241))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.CHAINMAIL_LEGGINGS) && (armor[whichArmor].getDurability() > 226))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.CHAINMAIL_BOOTS) && (armor[whichArmor].getDurability() > 196))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.DIAMOND_HELMET) && (armor[whichArmor].getDurability() > 364))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.DIAMOND_CHESTPLATE) && (armor[whichArmor].getDurability() > 529))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.DIAMOND_LEGGINGS) && (armor[whichArmor].getDurability() > 496))
			armor[whichArmor].setAmount(0);
		else if ((armor[whichArmor].getType() == Material.DIAMOND_BOOTS) && (armor[whichArmor].getDurability() > 430)) {
			armor[whichArmor].setAmount(0);
		}
		
		return armorAbsorbs;
		
	}
	
	public boolean isPlayerInside(Player player) {
		
		Location location = player.getLocation();
		location.setY(location.getY() + 1);
		
		boolean inside = false;
		for (int i = (int) location.getY(); i < player.getWorld().getHighestBlockYAt(location); i++) {
			
			location.setY(i);
			
			if (!location.getBlock().getType().equals(Material.AIR)){
				
				inside = true;
				break;
				
			}
			
		}

		return inside;

	}
	
	public boolean isPlayerInsideBedrock(Player player) {
		
		Location location = player.getLocation();
		location.setY(location.getY() + 1);
		
		Block highestBlock = player.getWorld().getHighestBlockAt(location);
		
		if (highestBlock.getType().equals(Material.BEDROCK)) {
			
			return true;
			
		} else {
			
			return false;
			
		}

	}

	
	
}
