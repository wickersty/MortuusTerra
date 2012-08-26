package me.wickersty.mortuusterra.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.wickersty.mortuusterra.MortuusTerra;

public class HelpManager {

	@SuppressWarnings("unused")
	private final MortuusTerra instance;
	
	public HelpManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}

	public void showHelp(Player sender) {

		sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "HELP");
		sender.sendMessage(ChatColor.AQUA + "Check your stats and radiation levels: " + ChatColor.GRAY + "/?");
		sender.sendMessage(ChatColor.AQUA + "/mt help " + ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.AQUA + "/mt help admin" + ChatColor.GRAY + " Admin Commands");
		sender.sendMessage(ChatColor.AQUA + "/mt help nukes" + ChatColor.GRAY + " How to create nukes");
		sender.sendMessage(ChatColor.AQUA + "/mt help activate" + ChatColor.GRAY + " How to activate radiation-related objects");
		
	}

	public void showHelpPermissions(Player sender) {

		sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "PERMISSIONS");
		sender.sendMessage(ChatColor.AQUA + "mt.admin " + ChatColor.GRAY + " All Admin Commands");
		
	}

	public void showHelpAdmin(Player sender) {

		sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "ADMIN HELP");
		sender.sendMessage(ChatColor.AQUA + "/a <message>" + ChatColor.GRAY + "Admin Chat");
		sender.sendMessage(ChatColor.AQUA + "/create supplydrop <world> " + ChatColor.GRAY + "Instantly create a supply drop");
		sender.sendMessage(ChatColor.AQUA + "/create protection <name>" + ChatColor.GRAY + "After you've marked points with a stick");
		sender.sendMessage(ChatColor.AQUA + "/fix streetlights " + ChatColor.GRAY + "");
		sender.sendMessage(ChatColor.AQUA + "/fix drops " + ChatColor.GRAY + "Clears all floating drops");
		
	}

	public void showHelpNukes(Player sender) {

		sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "NUKES HELP");
		sender.sendMessage(ChatColor.GRAY + "Glass bottle in the middle.");
		sender.sendMessage(ChatColor.GRAY + "Snow Blocks in the corners.");
		sender.sendMessage(ChatColor.GRAY + "TNT in the remaining slots.");
	
	}

	public void showHelpActivate(Player sender) {

		sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "ACTIVATE HELP");
		sender.sendMessage(ChatColor.AQUA + "/activate geck " + ChatColor.GRAY + "When looking at the sponge");

	}

}
