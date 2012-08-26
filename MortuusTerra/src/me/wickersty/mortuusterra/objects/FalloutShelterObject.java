package me.wickersty.mortuusterra.objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
// import org.bukkit.block.Chest;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class FalloutShelterObject {
	
	Location falloutShelterLocation;

	public FalloutShelterObject(Location falloutShelterLocation, Boolean generateFalloutShelter) {
		
		this.falloutShelterLocation = falloutShelterLocation;
		
		if (generateFalloutShelter == true) {

			generateFalloutShelter();
			
		}

	}

	public Location getFalloutShelterLocation() {

		return falloutShelterLocation;
			
	}

	public String getSerializedFalloutShelterObject() {

		return falloutShelterLocation.getWorld().getName() + "~" + falloutShelterLocation.getX() + "~" + falloutShelterLocation.getY() + "~" + falloutShelterLocation.getZ();
	
	}
	
	private void generateFalloutShelter() {
		
		Block centerBlock = falloutShelterLocation.getBlock();
		
		Block b11 = centerBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b12 = centerBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH);
		Block b13 = centerBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b14 = centerBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b15 = centerBlock.getRelative(BlockFace.NORTH);
		Block b16 = centerBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b17 = centerBlock.getRelative(BlockFace.WEST);
		Block b18 = centerBlock;
		Block b19 = centerBlock.getRelative(BlockFace.EAST);

		Block b110 = centerBlock.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
		Block b111 = centerBlock.getRelative(BlockFace.SOUTH);
		Block b112 = centerBlock.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST);
		
		
		Block centerBlock2 = centerBlock.getRelative(BlockFace.UP);

		Block b21 = centerBlock2.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b22 = centerBlock2.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH);
		Block b23 = centerBlock2.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b24 = centerBlock2.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b25 = centerBlock2.getRelative(BlockFace.NORTH);
		Block b26 = centerBlock2.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b27 = centerBlock2.getRelative(BlockFace.WEST);
		Block b28 = centerBlock2;
		Block b29 = centerBlock2.getRelative(BlockFace.EAST);

		Block b210 = centerBlock2.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
		Block b211 = centerBlock2.getRelative(BlockFace.SOUTH);
		Block b212 = centerBlock2.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST);
		
		
		Block centerBlock3 = centerBlock2.getRelative(BlockFace.UP);

		Block b31 = centerBlock3.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b32 = centerBlock3.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH);
		Block b33 = centerBlock3.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b34 = centerBlock3.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b35 = centerBlock3.getRelative(BlockFace.NORTH);
		Block b36 = centerBlock3.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b37 = centerBlock3.getRelative(BlockFace.WEST);
		Block b38 = centerBlock3;
		Block b39 = centerBlock3.getRelative(BlockFace.EAST);

		Block b310 = centerBlock3.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
		Block b311 = centerBlock3.getRelative(BlockFace.SOUTH);
		Block b312 = centerBlock3.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST);
		
		
		Block centerBlock4 = centerBlock3.getRelative(BlockFace.UP);

		Block b41 = centerBlock4.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b42 = centerBlock4.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH);
		Block b43 = centerBlock4.getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b44 = centerBlock4.getRelative(BlockFace.NORTH).getRelative(BlockFace.WEST);
		Block b45 = centerBlock4.getRelative(BlockFace.NORTH);
		Block b46 = centerBlock4.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);

		Block b47 = centerBlock4.getRelative(BlockFace.WEST);
		Block b48 = centerBlock4;
		Block b49 = centerBlock4.getRelative(BlockFace.EAST);

		Block b410 = centerBlock4.getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
		Block b411 = centerBlock4.getRelative(BlockFace.SOUTH);
		Block b412 = centerBlock4.getRelative(BlockFace.SOUTH).getRelative(BlockFace.EAST);
		
		
		
		b11.setType(Material.BEDROCK);
		b12.setType(Material.BEDROCK);
		b13.setType(Material.BEDROCK);
		b14.setType(Material.BEDROCK);
		b15.setType(Material.BEDROCK);
		b16.setType(Material.BEDROCK);
		b17.setType(Material.BEDROCK);
		b18.setType(Material.BEDROCK);
		b19.setType(Material.BEDROCK);
		b110.setType(Material.BEDROCK);
		b111.setType(Material.BEDROCK);
		b112.setType(Material.BEDROCK);

		b21.setType(Material.BEDROCK);
		b22.setType(Material.BEDROCK);
		b23.setType(Material.BEDROCK);
		b24.setType(Material.BEDROCK);
		b25.setType(Material.CHEST);
		b26.setType(Material.BEDROCK);
		b27.setType(Material.BEDROCK);
		b28.setType(Material.AIR);
		b29.setType(Material.BEDROCK);
		b210.setType(Material.BEDROCK);
		b211.setType(Material.OBSIDIAN);
		b212.setType(Material.BEDROCK);

		b31.setType(Material.BEDROCK);
		b32.setType(Material.BEDROCK);
		b33.setType(Material.BEDROCK);
		b34.setType(Material.BEDROCK);
		b35.setType(Material.AIR);
		b36.setType(Material.BEDROCK);
		b37.setType(Material.BEDROCK);
		b38.setType(Material.AIR);
		b39.setType(Material.BEDROCK);
		b310.setType(Material.BEDROCK);
		b311.setType(Material.OBSIDIAN);
		b312.setType(Material.BEDROCK);

		b41.setType(Material.BEDROCK);
		b42.setType(Material.BEDROCK);
		b43.setType(Material.BEDROCK);
		b44.setType(Material.BEDROCK);
		b45.setType(Material.BEDROCK);
		b46.setType(Material.BEDROCK);
		b47.setType(Material.BEDROCK);
		b48.setType(Material.BEDROCK);
		b49.setType(Material.BEDROCK);
		b410.setType(Material.BEDROCK);
		b411.setType(Material.BEDROCK);
		b412.setType(Material.BEDROCK);
		
		Chest chest = (Chest) b25.getState();
		Inventory chestInventory = chest.getBlockInventory();
		
		chestInventory.clear();
		chestInventory.addItem(new ItemStack(46, 4));
		chestInventory.addItem(new ItemStack(42, 2));
		chestInventory.addItem(new ItemStack(50, 64));
		chestInventory.addItem(new ItemStack(7, 8));
		chestInventory.addItem(new ItemStack(59, 32));
		chestInventory.addItem(new ItemStack(92, 2));
		chestInventory.addItem(new ItemStack(83, 16));
		chestInventory.addItem(new ItemStack(1, 64));
		chestInventory.addItem(new ItemStack(104, 8));
		chestInventory.addItem(new ItemStack(105, 8));
		chestInventory.addItem(new ItemStack(256, 4));
		chestInventory.addItem(new ItemStack(257, 4));
		chestInventory.addItem(new ItemStack(258, 4));
		chestInventory.addItem(new ItemStack(272, 4));
		chestInventory.addItem(new ItemStack(263, 32));
		chestInventory.addItem(new ItemStack(259, 1));
		chestInventory.addItem(new ItemStack(264, 8));
		chestInventory.addItem(new ItemStack(266, 16));
		
	}

}
