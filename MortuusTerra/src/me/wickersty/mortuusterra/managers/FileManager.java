package me.wickersty.mortuusterra.managers;

import me.wickersty.mortuusterra.MortuusTerra;

public class FileManager {

	private final MortuusTerra instance;
	
	public FileManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	public void loadFiles() {
		
		instance.getSupplyDropManager().loadData();
		instance.getProtectionManager().loadProtectionsFromDisk();
		instance.getPlayerManager().loadPlayersFromDisk();
		instance.getCraterManager().loadCratersFromDisk();
		instance.getFalloutShelterManager().loadFalloutSheltersFromDisk();
		instance.getGECKManager().loadGECKsFromDisk();
		
	}
	
	public void saveFiles() {
		
		instance.getSupplyDropManager().saveData();
		instance.getProtectionManager().saveProtectionsToDisk();
		instance.getPlayerManager().savePlayersToDisk();
		instance.getCraterManager().saveCratersToDisk();
		instance.getFalloutShelterManager().saveFalloutSheltersToDisk();
		instance.getGECKManager().saveGECKsToDisk();
		
	}
	
}
