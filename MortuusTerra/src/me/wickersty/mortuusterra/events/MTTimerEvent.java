package me.wickersty.mortuusterra.events;

import me.wickersty.mortuusterra.MortuusTerra;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MTTimerEvent extends Event {

	@SuppressWarnings("unused")
	private final MortuusTerra instance;
	private static final HandlerList handlers = new HandlerList();
	
	World world;
	public Boolean quarterDay;
	public Boolean fullDay;
	
	public MTTimerEvent(MortuusTerra instance) {
		
		this.instance = instance;
		
		this.world = null;
		this.quarterDay = false;
		this.fullDay = false;
		
	}
	
	public HandlerList getHandlers() {
		
		return handlers;
		
	}
	
	public static HandlerList getHandlerList() {
		
		return handlers;
		
	}
	
	public void setWorld(World world) {
		
		this.world = world;
		
	}

	public void setQuarterDay(Boolean quarterDay) {
		
		this.quarterDay = quarterDay;
		
	}

	public void setFullDay(Boolean fullDay) {
		
		this.fullDay = fullDay;
		
	}

	
}
