package me.wickersty.mortuusterra.listeners;

// import java.util.HashSet;
// import java.util.Set;
import me.wickersty.mortuusterra.MortuusTerra;
/*
import me.wickersty.mortuusterra.events.MTPlayerChatEvent;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
*/
import org.bukkit.event.Listener;
// import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

	@SuppressWarnings("unused")
	private final MortuusTerra instance;
	
	public ChatListener(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	/*
	@EventHandler(priority=EventPriority.HIGHEST)
	public void playerSpeaks(PlayerChatEvent event) {
		
		if (instance.getConfigManager().chatFeaturesEnabled == false) {
			
			return;
			
		} else {
		
			Player sender = event.getPlayer();
			World senderWorld = sender.getWorld();
			
			// make new set, separate from event's player set
			Set<Player> recipients = new HashSet<Player>();
			
			for (Player recipient : event.getRecipients()) {
				
				recipients.add(recipient);
				
			}

		    
		    // where is this message coming from?
			if (instance.getConfigManager().isWorldEnabled(senderWorld.getName()) == true) {
				
				instance.getLogger().info("PlayerChatEvent From Enabled World");
	
			    // Create the event here
				MTPlayerChatEvent playerChatEvent = new MTPlayerChatEvent(sender);
	
				playerChatEvent.setMessage(event.getMessage());
				playerChatEvent.setRecipients(recipients);
	
				// Call the event
			    instance.getServer().getPluginManager().callEvent(playerChatEvent);
	
				// from enabled world, so cancel the event and handle with custom code
				// event.setCancelled(true);
				
				// prep the message
				String message = ChatColor.DARK_AQUA + sender.getName() + ": " + ChatColor.GRAY + event.getMessage();
				
	
				
				
				if (!(playerChatEvent.getMessage().equalsIgnoreCase(""))) {
					
					if (playerChatEvent.getMessageAction() == null) {
						
						playerChatEvent.setMessageAction("nothing");
						
					}
	
					if (playerChatEvent.getMessageAction().equalsIgnoreCase("replace")) {
	
						message = sender.getName() + ": " + ChatColor.GRAY + playerChatEvent.getMessage();
						
					} else if (playerChatEvent.getMessageAction().equalsIgnoreCase("supress")) {
						
						event.setCancelled(true);
						
						return;
	
					} else if (playerChatEvent.getMessageAction().equalsIgnoreCase("nothing")) {
	
						// nothing
						
					} else if (playerChatEvent.getMessageAction().equalsIgnoreCase("prefix")) {
						
						// nothing
	
					}
					
				}
	
				
				
				message = playerChatEvent.getMessagePrefix() + ChatColor.WHITE + event.getPlayer().getName() + ": " + ChatColor.GRAY + playerChatEvent.getMessage();
				
				// remove recipients from the list who are in worlds other than the sender's
				Set<Player> recipientsToDelete = new HashSet<Player>();
				
				for (Player recipient : recipients) {
					
					if (!(recipient.getWorld().equals(sender.getWorld()))) {
	
						recipientsToDelete.add(recipient);
						
					}
					
				}
				
				for (Player recipientToDelete : recipientsToDelete) {
					
					recipients.remove(recipientToDelete);
					
				}
								
				
				// our recipients list now contains only players from within the same world as the sender
				for (Player recipient : recipients) {
	
					instance.getLogger().info("Chat Local: " + playerChatEvent.getChatLocal().toString());
					
					if (playerChatEvent.getChatLocal() == true) {
						
						// not from another plugin (local chat)
						if (recipient.getLocation().distance(sender.getLocation()) <= instance.getConfigManager().clearChatRange) {
							
							// recipient receives clear message					
							recipient.sendMessage(message);
													
						} else if (recipient.getLocation().distance(sender.getLocation()) <= instance.getConfigManager().garbledChatRange) { 
							
							// recipient receives garbled message
							String thisMessage = message;
							thisMessage.replace("a", "#");
							thisMessage.replace("e", " ");
							thisMessage.replace("i", "#");
							thisMessage.replace("o", ".");
							thisMessage.replace("u", "#");
							thisMessage.replace("y", " ");
		
							recipient.sendMessage(ChatColor.WHITE + sender.getName() + ChatColor.GRAY + " says: " + thisMessage);					
							
						} else {
							
							// recipient receives no message
							
						}					
						
					} else {
						
						// from another plugin module
						recipient.sendMessage(message);					
		
					}
				
				}
				
				
			} else {
	
				instance.getLogger().info("PlayerChatEvent From Non-Enabled World");
				
				// from non-enabled world, so remove players from recipients list that are in enabled worlds
				// and let the event play out by itself

				Set<Player> recipientsToDelete = new HashSet<Player>();
				
				for (Player recipient : recipients) {
					
					if (instance.getConfigManager().isWorldEnabled(recipient.getWorld().getName()) == true) {
	
						recipientsToDelete.add(recipient);
						
					}
					
				}
				
				for (Player recipientToDelete : recipientsToDelete) {
					
					recipients.remove(recipientToDelete);
					
				}
				
								
				event.getRecipients().clear();
				event.getRecipients().addAll(recipients);
				
				event.setCancelled(false);
				
				return;
				
			}
			
		}
		
	}
	*/
	
}
