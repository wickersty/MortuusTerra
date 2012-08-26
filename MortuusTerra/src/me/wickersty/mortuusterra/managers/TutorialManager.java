package me.wickersty.mortuusterra.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TutorialManager {
	
	public TutorialManager() {
		
		
		
	}
	
	public void showTutorialList(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.GRAY + "List");
		player.sendMessage(ChatColor.AQUA + "/tutorial history");
		player.sendMessage(ChatColor.AQUA + "/tutorial radiation");
		player.sendMessage(ChatColor.AQUA + "/tutorial zombies");
		player.sendMessage(ChatColor.AQUA + "/tutorial vampires");
		player.sendMessage(ChatColor.AQUA + "/tutorial electricity");
		player.sendMessage(ChatColor.AQUA + "/tutorial communications");
		
	}

	public void showHistory(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "History");
		player.sendMessage(ChatColor.GRAY + "A world-wide nuclear war broke out in the year 2021. Within");
		player.sendMessage(ChatColor.GRAY + "days, civilization was reduced to rubble and endless oceans");
		player.sendMessage(ChatColor.GRAY + "of fire and lava. Mortuus Terra mostly remained standing,");
		player.sendMessage(ChatColor.GRAY + "flooded with radiation. As a result, most of its citizens");
		player.sendMessage(ChatColor.GRAY + "either died or mutated into Zombies. They hid underground");
		player.sendMessage(ChatColor.GRAY + "until it was safe enough to return to the surface. You have");
		player.sendMessage(ChatColor.GRAY + "climbed out of the sewers to stake your place in the city.");
		
	}
	
	public void showRadiation(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Radiation");
		player.sendMessage(ChatColor.GRAY + "Mortuus Terra is flooded with radiation. As time goes on, the");
		player.sendMessage(ChatColor.GRAY + "radiation levels slowly diminish, but the city remains unsafe");
		player.sendMessage(ChatColor.GRAY + "for long periods of time on the surface. Wearing armor will");
		player.sendMessage(ChatColor.GRAY + "protect you, but the radiation will eventually eat through the");
		player.sendMessage(ChatColor.GRAY + "armor and destroy it. You can left-click a compass to check");
		player.sendMessage(ChatColor.GRAY + "current radiation levels, and find shelter inside buildings or");
		player.sendMessage(ChatColor.GRAY + "underground. Storms and water carry increased radiation.");
		player.sendMessage(ChatColor.AQUA + "Commands: /mt help");
		
	}

	public void showZombies1(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Zombies " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "The Zombies you see wandering around Mortuus Terra are");
		player.sendMessage(ChatColor.GRAY + "mutated citizens that have long since lost their humanity.");
		player.sendMessage(ChatColor.GRAY + "They want nothing more than to find living humans and feed");
		player.sendMessage(ChatColor.GRAY + "on their brains. Zombies are slow, but they usually appear");
		player.sendMessage(ChatColor.GRAY + "in groups and can cause devastating damage. They also carry");
		player.sendMessage(ChatColor.GRAY + "infection. If you are bitten and become infected, you will");
		player.sendMessage(ChatColor.GRAY + "turn into a Zombie the next time you die! Cures can be crafted");
		player.sendMessage(ChatColor.WHITE + "/tutorial zombies 2");
		
	}

	public void showZombies2(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Zombies " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "by surrounding Glass Bottles with Rotten Flesh in a Workbench.");
		player.sendMessage(ChatColor.GRAY + "If you become a Zombie, you will find yourself more powerful,");
		player.sendMessage(ChatColor.GRAY + "harder to kill, unable to drown, and immune to radiation.");
		player.sendMessage(ChatColor.GRAY + "However, due to being undead and without a brain, you are");
		player.sendMessage(ChatColor.GRAY + "unable to use tools or weapons and cannot speak normally.");
		player.sendMessage(ChatColor.GRAY + "If your server is using DisguiseCraft, you will also literally");
		player.sendMessage(ChatColor.GRAY + "turn into a Zombie, and be seen as such by other players.");
		player.sendMessage(ChatColor.AQUA + "Commands: /mtz help");
		
	}
	
	public void showVampires1(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Vampires " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "Some years after humans began to reclaim the surface, a");
		player.sendMessage(ChatColor.GRAY + "Vampire was spotted in the city. No one knows where it came");
		player.sendMessage(ChatColor.GRAY + "from, but the general assumption is that it roamed after the");
		player.sendMessage(ChatColor.GRAY + "bombs fell, searching for victims to feed upon. Then, one day,");
		player.sendMessage(ChatColor.GRAY + "the Vampire turned one of its victims into another Vampire, and");
		player.sendMessage(ChatColor.GRAY + "infection began to spread. If you see a tall, dark, sinister");
		player.sendMessage(ChatColor.GRAY + "figure with glowing eyes, running would be recommended. Vampires");
		player.sendMessage(ChatColor.WHITE + "/tutorial vampires 2");
		
	}

	public void showVampires2(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Vampires " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "are lethal creatures and kill without mercy.");
		player.sendMessage(ChatColor.GRAY + "");
		player.sendMessage(ChatColor.GRAY + "If you become a Vampire, you will gain several");
		player.sendMessage(ChatColor.GRAY + "powers, such as short-range teleportation and the");
		player.sendMessage(ChatColor.GRAY + "ability to glide harmlessly to the ground from great");
		player.sendMessage(ChatColor.GRAY + "heights. Vampires must use coffins each morning to");
		player.sendMessage(ChatColor.GRAY + "reactivate their powers, and can only feed on Humans");
		player.sendMessage(ChatColor.WHITE + "/tutorial vampires 3");
		
	}

	public void showVampires3(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Vampires " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "to restore their hunger. A Vampire Orb is used");
		player.sendMessage(ChatColor.GRAY + "to infect Humans as well as to teleport. It can be");
		player.sendMessage(ChatColor.GRAY + "crafted by surrounding a Diamond with Obsidian in a");
		player.sendMessage(ChatColor.GRAY + "Workbench. Vampries can also craft some vampire music");
		player.sendMessage(ChatColor.GRAY + "by surrounding a Jukebox with Obsidian. Finally, a cure");
		player.sendMessage(ChatColor.GRAY + "to Vampirism can be crafted by placing Books on either");
		player.sendMessage(ChatColor.GRAY + "side of a Glass Bottle.");
		player.sendMessage(ChatColor.AQUA + "Commands: /mtv help");
		
	}
	
	public void showElectricity(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Electricity " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "In Mortuus Terra, redstone wiring and devices will");
		player.sendMessage(ChatColor.GRAY + "not work without a power source, just like real life.");
		player.sendMessage(ChatColor.GRAY + "You can create a Generator by following the design");
		player.sendMessage(ChatColor.GRAY + "at www.mortuusterra.com/generator. A Generator will");
		player.sendMessage(ChatColor.GRAY + "provide electricity within a specific range, allowing");
		player.sendMessage(ChatColor.GRAY + "redstone devices and creations to be powered.");
		player.sendMessage(ChatColor.GRAY + "");
		player.sendMessage(ChatColor.AQUA + "Commands: /mte help");
		
	}

	public void showCommunication(Player player) {
		
		player.sendMessage(ChatColor.AQUA + "Tutorial" + ChatColor.WHITE + "Communication " + ChatColor.GRAY + "(if installed)");
		player.sendMessage(ChatColor.GRAY + "In Mortuus Terra, there are several ways to communicate.");
		player.sendMessage(ChatColor.GRAY + "Only people near you will hear you talk. You can also");
		player.sendMessage(ChatColor.GRAY + "use Walkie Talkies and Cellular Phones. Walkie Talkies");
		player.sendMessage(ChatColor.GRAY + "are useful for talking with groups of people regardless.");
		player.sendMessage(ChatColor.GRAY + "of distance. Cellular Phones are great for speaking");
		player.sendMessage(ChatColor.GRAY + "directly with someone, or leaving text messages for people");
		player.sendMessage(ChatColor.GRAY + "who are not online.");
		player.sendMessage(ChatColor.GRAY + "Commands: /mtr help");
		
	}
	
}
