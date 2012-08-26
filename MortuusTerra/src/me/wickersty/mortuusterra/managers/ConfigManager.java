package me.wickersty.mortuusterra.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.wickersty.mortuusterra.MortuusTerra;
import me.wickersty.mortuusterra.objects.SupplyDropContentsObject;

public class ConfigManager {

	private final MortuusTerra instance;
	
	// config files
	private File configFile;
	private FileConfiguration config;

	// world
	public String[] worldsEnabled;
	public boolean worldBorderEnabled;
	public int worldBorderSize;
	public String worldBorderEvent;
	
	// additional features
	public boolean thirstEnabled;
	
	// droughts
	public boolean droughtsEnabled;
	public double droughtsChance;
	public int droughtsMinLength;
	public int droughtsMaxLength;	
	
	// chat
	public boolean chatFeaturesEnabled;
	public Integer clearChatRange;
	public Integer garbledChatRange;

	// spawn
	public String spawnType;
	public int spawnProtectionRange;

	// fallout shelters
	public boolean generateFalloutSheltersEnabled;
	public double generateFalloutSheltersChance;
	
	// craters
	public boolean generateCratersEnabled;
	public double generateCratersChance;
	public boolean generateCratersAdmin;
	public boolean generateCratersPlayer;
	public double generateCratersPlayerChance;

	// nuclear weather
	public boolean nuclearLightningEnabled;
	public double nuclearLightningChance;	

	// radiation
	public boolean radiationEnabled;
	public double radiationMax;
	public double radiationDamageBase;
	public double radiationDamageIncreaseFromWater;
	public double radiationDamageIncreaseFromStorms;

	// supply drops
	public boolean supplyDropsEnabled;
	public double supplyDropsChance;
	public List<SupplyDropContentsObject> supplyDropsContents;
	
	// survival kits
	public boolean survivalKitsEnabled;
	public Integer survivalKitsHoursBetween;

	public ConfigManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		config = instance.getConfig();
		
		configFile = new File(instance.getDataFolder() + File.separator + "config.yml");

		supplyDropsContents = new ArrayList<SupplyDropContentsObject>();

		initializeConfig();
		
		loadConfig();
		
		saveConfig();		

	}


	public void initializeConfig() {
		
		boolean configFileExists = configFile.exists();
		
		if (configFileExists == true) {

			try {

				config.options().copyDefaults(true);
				config.load(this.configFile);
				
			} catch (Exception e) {

				e.printStackTrace();
				
			}
			
		} else {
			
			instance.getLogger().info("Creating Default Config File");
			
			config.options().copyDefaults(true);
			
		}
				
	}
	
	public void loadConfig() {
		
		// worlds
		worldsEnabled = config.getString("settings.worlds.enabled").split(",");
		worldBorderEnabled = config.getBoolean("settings.worlds.border.enabled");
		worldBorderSize = config.getInt("settings.worlds.border.size");
		worldBorderEvent = config.getString("settings.worlds.border.event");
		
		// additional features
		thirstEnabled = config.getBoolean("settings.additional-features.thirst.enabled");
		
		// droughts
		droughtsEnabled = config.getBoolean("settings.additional-features.droughts.enabled");
		droughtsChance = config.getDouble("settings.additional-features.droughts.chance");
		droughtsMinLength = config.getInt("settings.additional-features.droughts.min-length");
		droughtsMaxLength = config.getInt("settings.additional-features.droughts.max-length");
		
		// spawn
		spawnType = config.getString("settings.worlds.spawn.type");
		spawnProtectionRange = config.getInt("settings.worlds.spawn.protection-range");	
		
		// chat
		chatFeaturesEnabled = config.getBoolean("settings.chat.chat-features-enabled");
		clearChatRange = config.getInt("settings.chat.clear-chat-range");
		garbledChatRange = config.getInt("settings.chat.garbled-chat-range");
		
		// fallout shelters
		generateFalloutSheltersEnabled = config.getBoolean("settings.auto-generation.fallout-shelters.enabled");
		generateFalloutSheltersChance = config.getDouble("settings.auto-generation.fallout-shelters.chance");
		
		// craters
		generateCratersEnabled = config.getBoolean("settings.auto-generation.craters.enabled");
		generateCratersChance = config.getDouble("settings.auto-generation.craters.chance");
		generateCratersAdmin = config.getBoolean("settings.auto-generation.craters.nukes.admin.enabled");
		generateCratersPlayer = config.getBoolean("settings.auto-generation.craters.nukes.player.enabled");
		generateCratersPlayerChance = config.getDouble("settings.auto-generation.craters.nukes.player.chance");
		
		// nuclear weather
		nuclearLightningEnabled = config.getBoolean("settings.radiation.nuclear-weather.lightning.enabled");
		nuclearLightningChance = config.getDouble("settings.radiation.nuclear-weather.lightning.strike-chance");
		
		// radiation
		radiationEnabled = config.getBoolean("settings.radiation.enabled");
		radiationMax = config.getDouble("settings.radiation.maximum");
		radiationDamageBase = config.getDouble("settings.radiation.damage.damage-chance.base");
		radiationDamageIncreaseFromWater = config.getDouble("settings.radiation.damage.damage-chance.increase-from-water");
		radiationDamageIncreaseFromStorms = config.getDouble("settings.radiation.damage.damage-chance.increase-from-storms");

		// supply drops
		supplyDropsEnabled = config.getBoolean("settings.supply-drops.enabled");
		supplyDropsChance = config.getDouble("settings.supply-drops.supply-drop-chance");
		
		for (String key : config.getConfigurationSection("settings.supply-drops.items").getKeys(false)) {
				
			int itemId = config.getInt("settings.supply-drops.items." + key + ".item-id");
			int itemQuantity = config.getInt("settings.supply-drops.items." + key + ".quantity");
			double itemChance = config.getDouble("settings.supply-drops.items." + key + ".chance");
			
			supplyDropsContents.add(new SupplyDropContentsObject(itemId, itemQuantity, itemChance));
					
		}

		// survival kits
		survivalKitsEnabled = config.getBoolean("settings.survival-kits.enabled");
		survivalKitsHoursBetween = config.getInt("settings.survival-kits.hours-between-kits");
		
	}

	public void saveConfig() {

		try {

			config.save(configFile);
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}

	public boolean isWorldEnabled(String worldName) {
		
		boolean isEnabled = false;
		
		for (String world : worldsEnabled) {
			
			if (world.equalsIgnoreCase(worldName)) {
				
				isEnabled = true;
				
			}
			
		}
		
		return isEnabled;
		
	}


	public Inventory getSupplyDropContents(Inventory dropInventory) {

		List<SupplyDropContentsObject> supplyDropsContents = instance.getConfigManager().supplyDropsContents;
		
		for (SupplyDropContentsObject supplyDropsContent : supplyDropsContents) {

			if (Math.random() < supplyDropsContent.itemChance) {

				int i = 0;
				while (i < supplyDropsContent.itemQuantity) {
				
					dropInventory.addItem(new ItemStack(supplyDropsContent.itemId, 1));
					
					i++;
					
				}
				
			}
			
		}
		
		// always a few nukes and cures
		dropInventory.addItem(new ItemStack(373, 2, (short) 16396)); // nuke
		dropInventory.addItem(new ItemStack(373, 2, (short) 16405)); // zombie cure

		return dropInventory;
	
	}
	
}
