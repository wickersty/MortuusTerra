package me.wickersty.mortuusterra.managers.commands;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.PlayerObject;

public class CommandManager {

	private final MortuusTerra instance;
	
	public CommandManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}

	public void processCommand(Player sender, Command cmd, String[] args) {

		if (cmd.getName().equalsIgnoreCase("?")) {

			if (args.length == 0) {
				
				instance.getInfoManager().displayPlayerInfo(sender);
				
			}
			
			if (args.length == 1) {
				
				if (sender.getName().equalsIgnoreCase(args[0])) {
					
					instance.getInfoManager().displayPlayerInfo(sender);
					
				} else {
					
					if (sender.isOp() == true || instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") == true) {
						
						instance.getInfoManager().displayPlayerInfo(instance.getServer().getPlayer(args[0]));
						
					}
					
				}
				
			}
			
		}

		if (cmd.getName().equalsIgnoreCase("spawn")) {

			if (instance.getConfigManager().isWorldEnabled(sender.getWorld().getName()) == true) {
			
				sender.teleport(sender.getWorld().getSpawnLocation());
				
			}
			
		}
		
		if (cmd.getName().equalsIgnoreCase("a")) {
			
			// ops or admins only
			if (sender.isOp() == true || instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") == true) {
				
				List<Player> recipients = sender.getWorld().getPlayers();
				
				String message = "";
				
				for (String arg : args) {
					
					message += arg + " ";
					
				}

				for (Player recipient : recipients) {

					if (recipient.isOp() == true || instance.getPermissionsManager().playerHasPermissions(recipient, "mt.admin") == true) {
						
						recipient.sendMessage(ChatColor.RED + "[Admin] " + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + " " + message);
						
					}
					
				}
				
			}
			
		}

		if (cmd.getName().equalsIgnoreCase("mt")) {

			if (args.length == 0) {
				
				return;
				
			}
			
			if (args[0].equalsIgnoreCase("admin")) {
				
				if (args.length == 1) {
					
					return;
					
				}
				
				// ops or admins only
				if (sender.isOp() == false || instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") == false) {
					
					return;
					
				}

				if (args.length == 3 && args[1].equalsIgnoreCase("world")) {

					sender.teleport(instance.getServer().getWorld(args[2]).getSpawnLocation());
					
				}
				
				if (args.length == 5 && args[1].equalsIgnoreCase("set")) {

					if (args[2].equalsIgnoreCase("thirst")) {
						
						PlayerObject playerObject = instance.getPlayerManager().getPlayerObjectByPlayerName(args[3]);
						
						if (playerObject == null) {
							
							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY +"User not online.");
							
						} else {
							
							playerObject.thirst = Integer.valueOf(args[4]);
							
						}
						
					}

				}
				
				if (args[1].equalsIgnoreCase("tp")) {

					Double x = Double.valueOf(args[2]);
					Double y = Double.valueOf(args[3]);
					Double z = Double.valueOf(args[4]);
					
					sender.teleport(new Location(sender.getWorld(), x, y, z));
					
				}
				
				if (args[1].equalsIgnoreCase("setspawn")) {

					Double x = sender.getLocation().getX();
					Integer xx = x.intValue();

					Double y = sender.getLocation().getY();
					Integer yy = y.intValue();

					Double z = sender.getLocation().getZ();
					Integer zz = z.intValue();

					sender.getWorld().setSpawnLocation(xx, yy, zz);
					
					sender.sendMessage(ChatColor.DARK_RED + "[Mortuust Terra] " +  ChatColor.GRAY + "Spawn Location Set");
					
					
				}
				
				
				if (args[1].equalsIgnoreCase("fix")) {
					
					if (args.length == 2) {
						
						return;
						
					}
					
					if (args[2].equalsIgnoreCase("streetlights")) {
						
						instance.getMiscManager().fixStreetlights(sender, sender.getLocation());
						
					}
					
					if (args[2].equalsIgnoreCase("drops")) {
						
						instance.getMiscManager().clearDrops(sender, sender.getWorld());
						
					}					
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("activate")) {
				
				if (args.length == 1) {
					
					return;
					
				}
				
				if (args[1].equalsIgnoreCase("geck")) {
					
					if (instance.getGECKManager().getGECKObjectByLocation(sender.getTargetBlock(null, 100).getLocation()) == null) {
						
						if (instance.getGECKManager().isValidGECK(sender.getTargetBlock(null, 100).getLocation()) == true) {
							
							// add geck to the geck data
							instance.getGECKManager().addGECK(sender.getTargetBlock(null, 100).getRelative(BlockFace.DOWN).getLocation());

							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " GECK Activated");
							
						} else {
							
							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " Not a valid GECK");
							
						}
												
					} else {
						
						sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " GECK Already Activated");
						
					}
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("delete")) {

				if (args.length == 1) {
					
					return;
					
				}

				if (args.length == 2 && args[1].equalsIgnoreCase("drought") && (instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {

					instance.getLogger().info("Ending Drought");
					
					if (instance.getWorldManager().drought.getDroughtActive() == false) {
						
						sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "There is no drought in this world.");
					
						return;
						
					}
					
					instance.getWorldManager().endDrought();
					
					sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Drought Ended");
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("create")) {
				
				if (args.length == 1) {
					
					return;
					
				}

				if (args.length == 2 && args[1].equalsIgnoreCase("drought") && (instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {
					
					instance.getLogger().info("Creating Drought");

					if (instance.getWorldManager().drought.getDroughtActive() == true) {
						
						sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "There is already a drought in this world.");
					
						return;
						
					}
					
					instance.getWorldManager().createDrought();
					
					sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Drought Created");
					
				}
				
				if (args.length == 2 && args[1].equalsIgnoreCase("crater") && (instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {

					final World world = sender.getWorld();
					final Location location = sender.getLocation();
		
					sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Clear out! The nuke is about to detonate!");
		
					@SuppressWarnings("unused")
					int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
						public void run() {
		
							world.createExplosion(location, 3);
							instance.getCraterManager().addCrater(location);
							instance.getChunkListener().placeCrater(location);
							
						}
					}, 90);
					
				}
					
				if (args.length == 2 && args[1].equalsIgnoreCase("supplydrop") && (instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {
					
					instance.getWorldListener().deliverSupplyDrop(sender.getWorld());
					
				}
				
				if (args[1].equalsIgnoreCase("protection") && (instance.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {
					
					if (args.length == 2) {
						
						return;
						
					}
					
					if (instance.getProtectionManager().getProtectionObjectByName(args[2]) == null) {
						
						if (instance.getProtectionManager().protectionMarksPresent(sender) == true) {
							
							instance.getProtectionManager().addProtection(args[2], sender);

							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " Protection Created");							
							
						} else {

							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " First mark points using the protection tool");
							
						}
						
					} else {
						
						sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " A Protection already exists by that name.");
						
					}
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("list")) {
				
				
				
			}

			if (args[0].equalsIgnoreCase("help")) {
				
				if (args.length == 1) {
					
					instance.getHelpManager().showHelp(sender);
					
				} else {
					
					if (args[1].equalsIgnoreCase("permissions")) {
						
						instance.getHelpManager().showHelpPermissions(sender);
						
					}
					
					if (args[1].equalsIgnoreCase("admin")) {
						
						instance.getHelpManager().showHelpAdmin(sender);
						
					}
					
					if (args[1].equalsIgnoreCase("nukes")) {
						
						instance.getHelpManager().showHelpNukes(sender);
						
					}
					
					if (args[1].equalsIgnoreCase("activate")) {
						
						instance.getHelpManager().showHelpActivate(sender);
						
					}
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("")) {
				
				instance.getHelpManager().showHelp(sender);
				
			}
			
		}		
		
	}

}
