package me.wickersty.mortuusterra.events;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MTPlayerChatEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private Set<Player> recipients;
	private String chatMessage;
	private String chatPrefix;
	private Boolean chatLocal;
	private String messageAction;
	
	public MTPlayerChatEvent(Player player) {
		
		this.player = player;
		
		if (chatPrefix == null) {
			
			this.chatPrefix = "";
			
		}
		
		if (chatMessage == null) {

			this.chatMessage = "";
			
		}
		
		if (chatLocal == null) {
			
			this.chatLocal = true;
			
		}

	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
	public void setRecipients(Set<Player> recipients) {
		
		this.recipients = recipients;
		
	}
	
	public Set<Player> getRecipients() {
		
		return recipients;
		
	}
	
	public String getMessagePrefix() {
		
		return chatPrefix;
		
	}
	
	public void setMessagePrefix(String chatPrefix) {
		
		this.chatPrefix = chatPrefix;
		
	}
	
	public String getMessage() {
		
		return chatMessage;
		
	}
	
	public Boolean getChatLocal() {
		
		return this.chatLocal;
		
	}
	
	public void setChatLocal(Boolean chatLocal) {
		
		this.chatLocal = chatLocal;
		
	}
	
	public void updateMessage(String chatMessage) {
		
		this.chatMessage = chatMessage;
		
	}
	
	public void setMessage(String chatMessage) {
		
		this.chatMessage = chatMessage;
		
	}

	public String getMessageAction() {
		
		return messageAction;
		
	}

	public void setMessageAction(String messageAction) {
		
		this.messageAction = messageAction;
		
	}

	public HandlerList getHandlers() {
		
		return handlers;
		
	}
	
	public static HandlerList getHandlerList() {
		
		return handlers;
		
	}
	
}
