package me.wickersty.mortuusterra.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.PlayerObject;

public class PlayerManager {

	private final MortuusTerra instance;

	private List<PlayerObject> players;

	public PlayerManager(MortuusTerra instance) {
		
		this.instance = instance;

		players = new ArrayList<PlayerObject>();

	}

	public PlayerObject getPlayerObjectByPlayerName(String playerName) {
		
		PlayerObject foundPlayer = null;
		
		for (PlayerObject player : players) {
			
			if (player.getPlayerName().equalsIgnoreCase(playerName)) {
				
				foundPlayer = player;
				
			}
			
		}
		
		return foundPlayer;
		
	}

	public void addPlayer(String playerName, Long joinDate, Integer health, Integer thirst, Integer hunger) {
		
		players.add(new PlayerObject(instance, playerName, joinDate, health, thirst, hunger));
		
	}

	public void removePlayer(String playerName) {
		
		PlayerObject playerObject = getPlayerObjectByPlayerName(playerName);

		players.remove(playerObject);
		
	}
	
	public void savePlayersToDisk() {
		
		try {

			File playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
			playersFile.delete();
			
			playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
			playersFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		instance.getLogger().info("Saving Players To Disk");

		try {
			
			PrintWriter playersFile = new PrintWriter(instance.getDataFolder() + File.separator + "players.txt");
				
			if (players.size() > 0) {

				for (PlayerObject player : players) {
					
					String playerString = player.getSerializedPlayerObject();
	
					playersFile.println(playerString);
					
				}

			}
			
			playersFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadPlayersFromDisk() {
		
		ensurePlayersFileExists();
		
		instance.getLogger().info("Loading Players From Disk");

		try {

			File playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
			Scanner inputFile = new Scanner(playersFile);
			
			while (inputFile.hasNextLine()) {
	
				String playerString = inputFile.nextLine();
				String[] playerArray = playerString.split("~");

				players.add(new PlayerObject(instance, playerArray[0], Long.valueOf(playerArray[1]), Integer.valueOf(playerArray[2]), Integer.valueOf(playerArray[3]), Integer.valueOf(playerArray[4])));
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}

	public void ensurePlayersFileExists() {
		
		instance.getLogger().info("Creating Players File");

		// create file if not exists
		try {
			
			File playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
	
			if (playersFile.exists() == false) {
				
				playersFile = new File(instance.getDataFolder() + File.separator + "players.txt");
				playersFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}

	public void activateGeigerCounter(Player player) {

		player.playEffect(player.getLocation(), Effect.CLICK1, 0);
			
	}

	public boolean playerOutOfBounds(Player player) {

		boolean playerOutOfBounds = false;
		
		double halfSize = instance.getConfigManager().worldBorderSize / 2;

		if (player.getLocation().getX() < -1.0 * halfSize) {
			
			playerOutOfBounds = true;
			
		}
		
		if (player.getLocation().getX() > halfSize) {
			
			playerOutOfBounds = true;
			
		}
		
		if (player.getLocation().getZ() < -1.0 * halfSize) {
			
			playerOutOfBounds = true;
			
		}
		
		if (player.getLocation().getZ() > halfSize) {
			
			playerOutOfBounds = true;
			
		}	 
		
		return playerOutOfBounds;
	
	}

	public void startTimedTeleport(final Player player, final Location teleportLocation) {
		
		final Location startingLocation = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());

		player.sendMessage(ChatColor.AQUA + "Teleporting in 5 seconds. Do not move.");

		instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {

		   public void run() {
			
			   instance.getLogger().info("Teleport Player if Unmoved: " + player.getName());

			   teleportPlayerIfUnmoved(player, startingLocation, teleportLocation);

		   }
			   
		}, 60L);
		
	}

	public void teleportPlayerIfUnmoved(Player player, Location startingLocation, Location teleportLocation) {

		instance.getLogger().info("B");

		if (!startingLocation.getWorld().equals(player.getWorld()) || startingLocation.getX() != player.getLocation().getX() || startingLocation.getY() != player.getLocation().getY() || startingLocation.getZ() != player.getLocation().getZ()) {
			
			instance.getLogger().info("Moved");

			player.sendMessage(ChatColor.AQUA + "You moved, so your teleportation request was cancelled.");
			
		} else {
		
			instance.getLogger().info("Unmoved");

			player.sendMessage(ChatColor.AQUA + "Teleporting...");

			player.teleport(teleportLocation);
			
		}
		
	}
	
}
