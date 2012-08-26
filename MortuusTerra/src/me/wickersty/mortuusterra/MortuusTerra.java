package me.wickersty.mortuusterra;

import java.util.logging.Logger;
import me.wickersty.mortuusterra.listeners.ChatListener;
import me.wickersty.mortuusterra.listeners.ChunkListener;
import me.wickersty.mortuusterra.listeners.GECKListener;
import me.wickersty.mortuusterra.listeners.PlayerListener;
import me.wickersty.mortuusterra.listeners.ProtectionListener;
import me.wickersty.mortuusterra.listeners.SpawnListener;
import me.wickersty.mortuusterra.listeners.WorldListener;
import me.wickersty.mortuusterra.managers.ConfigManager;
import me.wickersty.mortuusterra.managers.CraftingManager;
import me.wickersty.mortuusterra.managers.FileManager;
import me.wickersty.mortuusterra.managers.HelpManager;
import me.wickersty.mortuusterra.managers.InfoManager;
import me.wickersty.mortuusterra.managers.MiscManager;
import me.wickersty.mortuusterra.managers.PermissionsManager;
import me.wickersty.mortuusterra.managers.PlayerManager;
import me.wickersty.mortuusterra.managers.ProtectionManager;
import me.wickersty.mortuusterra.managers.RadiationManager;
import me.wickersty.mortuusterra.managers.SupplyDropManager;
import me.wickersty.mortuusterra.managers.TutorialManager;
import me.wickersty.mortuusterra.managers.WorldManager;
import me.wickersty.mortuusterra.managers.commands.CommandManager;
import me.wickersty.mortuusterra.managers.commands.ConsoleCommandManager;
import me.wickersty.mortuusterra.managers.radiation.CraterManager;
import me.wickersty.mortuusterra.managers.radiation.FalloutShelterManager;
import me.wickersty.mortuusterra.managers.radiation.GECKManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MortuusTerra extends JavaPlugin {

	private static MortuusTerra instance;
	
	private static Logger logger = Logger.getLogger("Minecraft");
	
	private ChunkListener chunkListener;
	private ProtectionListener protectionListener;
	private SpawnListener spawnListener;
	private ChatListener chatListener;
	private PlayerListener playerListener;
	private WorldListener worldListener;
	private GECKListener geckListener;
	
	private CommandManager commandManager;
	private ConsoleCommandManager consoleCommandManager;
	private InfoManager infoManager;
	
	private FileManager fileManager;
	
	private ConfigManager configManager;
	private HelpManager helpManager;
	private TutorialManager tutorialManager;
	private PermissionsManager permissionsManager;
	private CraftingManager craftingManager;
	private WorldManager worldManager;
	private MiscManager miscManager;
	private ProtectionManager protectionManager;
	private PlayerManager playerManager;	
	private RadiationManager radiationManager;
	private SupplyDropManager supplyDropManager;
	
	private CraterManager craterManager;
	private FalloutShelterManager falloutShelterManager;
	private GECKManager geckManager;
	
	public void onEnable() {

		logger.info("|----------|");
		
		instance = this;

		getDataFolder().mkdir();
		
		commandManager = new CommandManager(instance);
		consoleCommandManager = new ConsoleCommandManager(instance);
		infoManager = new InfoManager(instance);
		
		fileManager = new FileManager(instance);

		configManager = new ConfigManager(instance);
		helpManager = new HelpManager(instance);
		tutorialManager = new TutorialManager();
		permissionsManager = new PermissionsManager(instance);
		craftingManager = new CraftingManager(instance);
		worldManager = new WorldManager(instance);
		miscManager = new MiscManager(instance);
		protectionManager = new ProtectionManager(instance);
		playerManager = new PlayerManager(instance);
		radiationManager = new RadiationManager(instance);
		supplyDropManager = new SupplyDropManager(instance);
		
		craterManager = new CraterManager(instance);
		falloutShelterManager = new FalloutShelterManager(instance);
		geckManager = new GECKManager(instance);
		
		chunkListener = new ChunkListener(instance);
		protectionListener = new ProtectionListener(instance);
		spawnListener = new SpawnListener(instance);
		chatListener = new ChatListener(instance);
		playerListener = new PlayerListener(instance);
		worldListener = new WorldListener(instance);
		geckListener = new GECKListener(instance);

		
		// event listeners
		getServer().getPluginManager().registerEvents(this.protectionListener, this);
		getServer().getPluginManager().registerEvents(this.spawnListener, this);
		getServer().getPluginManager().registerEvents(this.chunkListener, this);
		getServer().getPluginManager().registerEvents(this.chatListener, this);
		getServer().getPluginManager().registerEvents(this.playerListener, this);
		getServer().getPluginManager().registerEvents(this.worldListener, this);
		getServer().getPluginManager().registerEvents(this.geckListener, this);
		
		getFileManager().loadFiles();
		
		logger.info("|----------|");
		
	}
	
	public void onDisable() {
		
		logger.info("|----------|");

		getFileManager().saveFiles();

		getServer().getScheduler().cancelTasks(this);

		logger.info("|----------|");

	}
	
	public CommandManager getCommandManager() {
		
		return commandManager;
		
	}
	
	public ConsoleCommandManager getConsoleCommandManager() {
		
		return consoleCommandManager;
		
	}
	
	public InfoManager getInfoManager() {
		
		return infoManager;
		
	}
	
	public FileManager getFileManager() {
		
		return fileManager;
		
	}
	
	public HelpManager getHelpManager() {
		
		return helpManager;
		
	}

	public TutorialManager getTutorialManager() {
		
		return tutorialManager;
		
	}

	public PermissionsManager getPermissionsManager() {
		
		return permissionsManager;
		
	}

	public WorldManager getWorldManager() {
		
		return worldManager;
		
	}

	public CraftingManager getCraftingManager() {
		
		return craftingManager;
		
	}

	public MiscManager getMiscManager() {
		
		return miscManager;
		
	}

	public ProtectionManager getProtectionManager() {
		
		return protectionManager;
		
	}

	public PlayerManager getPlayerManager() {
		
		return playerManager;
		
	}
	
	public RadiationManager getRadiationManager() {
		
		return radiationManager;
		
	}

	public SupplyDropManager getSupplyDropManager() {
		
		return supplyDropManager;
		
	}

	public CraterManager getCraterManager() {
		
		return craterManager;
		
	}
	
	public FalloutShelterManager getFalloutShelterManager() {
		
		return falloutShelterManager;
		
	}
	
	public GECKManager getGECKManager() {
		
		return geckManager;
		
	}
	
	public ConfigManager getConfigManager() {
		
		return configManager;
		
	}
	
	public ChunkListener getChunkListener() {
		
		return chunkListener;
		
	}
	
	public PlayerListener getPlayerListener() {
		
		return playerListener;
		
	}

	public WorldListener getWorldListener() {
		
		return worldListener;
		
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (sender instanceof Player) {
			
			// player commands
			getCommandManager().processCommand((Player) sender, cmd, args);
			
			
		} else {
			
			// console commands
			getConsoleCommandManager().processCommand(cmd, args);
			
		}
		
		return true;
		
	}

}
