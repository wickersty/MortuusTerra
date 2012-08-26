package me.wickersty.mortuusterra.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.events.EntityRadiationDamageEvent;
import me.wickersty.mortuusterra.objects.PlayerObject;
import me.wickersty.mortuusterra.objects.SupplyDropObject;

public class PlayerListener implements Listener {

	private final MortuusTerra instance;
	
	public PlayerListener(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerIsAttackedByPlayer(EntityDamageByEntityEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getEntity().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
			
			Player attacker = (Player) event.getDamager();
			Player victim = (Player) event.getEntity();
			
			PlayerObject victimObject = instance.getPlayerManager().getPlayerObjectByPlayerName(victim.getName());
			PlayerObject attackerObject = instance.getPlayerManager().getPlayerObjectByPlayerName(attacker.getName());
			
			if (victimObject == null) {
				
				return;
				
			}

			if (attackerObject == null) {
				
				return;
				
			}

			// instance.getLogger().info("Victim Join Date: " + victimObject.joinDate + " | Server Time: " + victim.getWorld().getFullTime());
			
			if (victim.getWorld().getFullTime() - victimObject.joinDate < (24000 * 5)) {
				
				// Player has not yet been around for 5 Minecraft days
				event.setCancelled(true);
				
				victim.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "You are protected from pvp for 5 Minecraft days.");
				attacker.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "New players cannot be attacked for 5 Minecrat days.");
				
			}

			if (attacker.getWorld().getFullTime() - attackerObject.joinDate < (24000 * 5)) {

				// attacker has not yet been around for 5 minecraft days
				// therefore he loses his protection

				victim.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + attacker.getName() + " has lost his/her 5 Day protection by attacking you.");
				attacker.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "You have lost your 5 Day protection by attacking another player.");
				
			}
			
		} else {
			
			return;
			
		}
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerMoves(PlayerMoveEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		lightningStrikeCheck(event);

		playerBioCheck(event);
		
		radiationCheck(event);
		
	}
		
	public void radiationCheck(PlayerMoveEvent event) {
		
		double radiationDamage = instance.getRadiationManager().calculateRadiationDamage(event.getPlayer());

		if (radiationDamage > 0.0) {

		    instance.getRadiationManager().radiationDamageReceived(event.getPlayer());

			Double radiationSicknessIncreaseChance = Math.random();
			
			Double radiationDamageTrigger = radiationDamage * .01;
			
			if (radiationDamageTrigger > radiationSicknessIncreaseChance) {
				
				event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
				
			}
			
		}
		
	}
	
	public void playerBioCheck(PlayerMoveEvent event) {
		
		// udpate player health, hunger, and thirst
		instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).health = event.getPlayer().getHealth();
		instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).hunger = event.getPlayer().getFoodLevel();

		if (instance.getConfigManager().thirstEnabled == false) {
			
			return;
			
		}
		
		instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).calculateThirst(event.getPlayer().getWorld().getFullTime());
		
	}
	
	public void lightningStrikeCheck(PlayerMoveEvent event) {
		
		if (instance.getRadiationManager().isPlayerInside(event.getPlayer()) == true) {
			
			return;
			
		}
				
		if (instance.getConfigManager().nuclearLightningEnabled == true) {

			if (Math.random() < (instance.getConfigManager().nuclearLightningChance / 4)) {
			
				Location lightningLocation = event.getPlayer().getLocation();
				lightningLocation.setY(lightningLocation.getY() + 30);
				
				event.getPlayer().getLocation().getWorld().strikeLightning(lightningLocation);
				
			}
			
		}		
		
	}
	
	@EventHandler(priority=EventPriority.LOWEST)	
	public void playerDrinksRain(WeatherChangeEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (instance.getConfigManager().thirstEnabled == false) {
			
			return;
			
		}

		if (event.getWorld().isThundering() == true) {
		
			for (Player player : event.getWorld().getPlayers()) {
				
				if (event.getWorld().getHighestBlockAt(player.getLocation()).getType().equals(Material.AIR) || event.getWorld().getHighestBlockAt(player.getLocation()).getType().equals(Material.GLASS)) {
					
					instance.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).thirst = 20;
					player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "You drink from the rain and replenish your thirst.");
					
				}
				
			}

		}
		
	}

	@EventHandler(priority=EventPriority.LOWEST)	
	public void playerDrinksWater(PlayerInteractEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (instance.getConfigManager().thirstEnabled == false) {
			
			return;
			
		}

		if ((event.getAction().toString().equals("RIGHT_CLICK_BLOCK") || event.getAction().toString().equals("RIGHT_CLICK_AIR")) && event.getMaterial().toString().equalsIgnoreCase("POTION") && event.getItem().getDurability() == 0) {

			event.getPlayer().getItemInHand().setType(Material.GLASS_BOTTLE);

			final Player finalPlayer = event.getPlayer();
						
			@SuppressWarnings("unused")
			int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
				public void run() {

					finalPlayer.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "You replenish your thirst.");
					
				}
			}, 100);
			
			instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).thirst = 20;
		
		}
				
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void playerSpawns(PlayerDeathEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getEntity().getWorld().getName()) == false) {
			
			return;
			
		}
		
		Long currentTimestamp = event.getEntity().getWorld().getTime();

		PlayerObject playerObject = instance.getPlayerManager().getPlayerObjectByPlayerName(event.getEntity().getName());
		
		if (playerObject != null && (currentTimestamp < playerObject.getLastKitAwarded())) {
			
			// award kit
			event.getEntity().getInventory().addItem(new ItemStack(Material.COMPASS, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_HELMET, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.BREAD, 5));
			event.getEntity().getInventory().addItem(new ItemStack(Material.SEEDS, 5));
			
		}
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerLogsIn(PlayerJoinEvent event) {
		
		if (instance.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()) == null) {
			
			instance.getPlayerManager().addPlayer(event.getPlayer().getName(), event.getPlayer().getWorld().getFullTime(), 20, 20, 20);
			
		}
		
	}
		
	@EventHandler(priority=EventPriority.LOW)
	public void playerMarksProtection(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.getPlayer().isOp() == false && instance.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.admin") == false && instance.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.create.*") == false && instance.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.create.protection") == false) {
			
			return;
			
		}
				
		if (event.getPlayer().getItemInHand().toString().contains("STICK")) {
			
			if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
				
				Location mark1 = event.getClickedBlock().getLocation();
				instance.getProtectionManager().protectionMarksOne.put(event.getPlayer(), mark1);
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Protection Point One Set");
				
				event.setCancelled(true);
				
			}
			
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				
				Location mark2 = event.getClickedBlock().getLocation();
				instance.getProtectionManager().protectionMarksTwo.put(event.getPlayer(), mark2);
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Protection Point Two Set");
				
			}
			
		}
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void playerDetonatesNuke(PlayerInteractEvent event) {
	
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (instance.getConfigManager().generateCratersPlayer == false) {
			
			return;		
			
		}

		if ((event.getAction().toString().equals("RIGHT_CLICK_BLOCK") || event.getAction().toString().equals("RIGHT_CLICK_AIR")) && event.getMaterial().toString().equalsIgnoreCase("POTION") && event.getItem().getDurability() == 16396) {
			
			if (instance.getProtectionManager().locationIsProtected(event.getPlayer().getLocation()) == false) {

				if (Math.random() > instance.getConfigManager().generateCratersPlayerChance) {
					
					event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "The nuke did not detonate.");
					
					event.setCancelled(true);
		
				} else {
				
					final World world = event.getPlayer().getWorld();
					final Location location = event.getPlayer().getLocation();
					final Effect effect = Effect.SMOKE;
		
					event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Clear out! The nuke is about to detonate!");
		
					@SuppressWarnings("unused")
					int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
						public void run() {
		
							// world.createExplosion(location, 2);
							world.playEffect(location, effect, 20);
							instance.getCraterManager().addCrater(location);
							instance.getChunkListener().placeCrater(location);
							
						}
					}, 90);
						
				}
				
			} else {
				
				instance.getProtectionManager().informPlayer(event.getPlayer());
				
			}
			
		}
		
	}

	/*
	@EventHandler(priority=EventPriority.LOW)
	public void playerUsesGeigerCounter(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().getItemInHand().getTypeId() == instance.getConfigManager().geigerCounterItemId) {
			
			double radiationDamage = instance.getRadiationManager().calculateRadiationDamage(event.getPlayer());

			event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + radiationDamage + " Rads");
			
		}
		
	}
	*/
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerSearchesForSupplyDropSignal(PlayerInteractEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (instance.getConfigManager().supplyDropsEnabled == true && event.getPlayer().isSneaking() == false && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {

			SupplyDropObject closestSupplyDrop = instance.getSupplyDropManager().getNearestSupplyDrop(event.getPlayer().getLocation());
			
			if (closestSupplyDrop == null) {
				
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "There are no Supply Drops in the city.");
				
			} else {
				
				event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Targeting nearest Supply Drop.");
				
				event.getPlayer().setCompassTarget(closestSupplyDrop.getDropLocation());
				
			}
						
		}
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerReceivesRadiationDamage(EntityRadiationDamageEvent event) {
		
		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.isCancelled() == true) {
			
			return;
			
		}

		// adjustment
		Double radiationDamageReceived = event.getRadiationDamage();		
		
		Integer tempInt = radiationDamageReceived.intValue();
		radiationDamageReceived = Double.valueOf(tempInt);
		
		double ran = Math.random();
		if (ran <= (radiationDamageReceived * .0015)) {
						
			instance.getPlayerManager().activateGeigerCounter(event.getPlayer());
			
			if (instance.getRadiationManager().armorAbsorbsRadiation(event.getPlayer()) == false) {
				
				event.getPlayer().damage(1);
				
			}
			
		}
				
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerMovesOutOfBounds(PlayerMoveEvent event) {

		if (instance.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (instance.getConfigManager().worldBorderEnabled == false) {
			
			return;
			
		}

		if (instance.getPlayerManager().playerOutOfBounds(event.getPlayer()) == true) {
			
			if (instance.getConfigManager().worldBorderEvent.equalsIgnoreCase("wrap")) {
				
				Location to = event.getPlayer().getLocation();
				double size = instance.getConfigManager().worldBorderSize;
				double halfSize = size / 2; 
				
				if (to.getX() < -1.0 * halfSize) {
					to = to.add(size, 0.0, 0.0);
					to.getWorld().loadChunk((int)to.getX(), (int)to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}
				
				if (to.getX() > halfSize) {
					to = to.add(-1 * size, 0.0, 0.0);
					to.getWorld().loadChunk((int)to.getX(), (int)to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}
				
				if (to.getZ() < -1.0 * halfSize) {
					to = to.add(0.0, 0.0, size);
					to.getWorld().loadChunk((int)to.getX(), (int)to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}
				
				if (to.getZ() > halfSize) {
					to = to.add(0.0, 0.0, -1 * size);
					to.getWorld().loadChunk((int)to.getX(), (int)to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}	
				
			}
			
			if (instance.getConfigManager().worldBorderEvent.equalsIgnoreCase("radiation")) {
				
				if (Math.random() < 0.01) {
					
					instance.getPlayerManager().activateGeigerCounter(event.getPlayer());
					event.getPlayer().damage(1);
					
				}
				
			}

			if (instance.getConfigManager().worldBorderEvent.equalsIgnoreCase("lightning")) {

				if (Math.random() < 0.02) {

					event.getPlayer().getWorld().strikeLightningEffect(event.getPlayer().getLocation());
					event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());
					
				}
				
			}			
			
		}
		
	}

}
