package me.wickersty.mortuusterra.managers.commands;

import org.bukkit.command.Command;

import me.wickersty.mortuusterra.MortuusTerra;

public class ConsoleCommandManager {

	private final MortuusTerra instance;
	
	public ConsoleCommandManager(MortuusTerra instance) {
		
		this.instance = instance;
		
	}

	public void processCommand(Command cmd, String[] args) {


		
	}

	public void temp() {
		
		instance.getLogger().info("Class Loaded");
		
	}

}
