package me.wickersty.mortuusterra.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import me.wickersty.mortuusterra.MortuusTerra;

public class CraftingManager {
	
	private final MortuusTerra instance;
	
	public CraftingManager(MortuusTerra instance) {
		
		this.instance = instance;
		
		initializeRecipes();
		
	}
	
	public void initializeRecipes() {
		
		instance.getLogger().info("Initializing Recipes");
		
		ShapedRecipe nukeRecipe = new ShapedRecipe(new ItemStack(373, 1, (short) 16396));
		nukeRecipe.shape(new String[] { "CBC", "BAB", "CBC" });
		nukeRecipe.setIngredient('A', Material.GLASS_BOTTLE);
		nukeRecipe.setIngredient('B', Material.TNT);
		nukeRecipe.setIngredient('C', Material.SNOW_BLOCK);
		instance.getServer().addRecipe(nukeRecipe);
		
	}

}
