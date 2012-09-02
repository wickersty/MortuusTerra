package me.wickersty.mortuusterra.listeners;

import java.util.HashSet;
import java.util.Set;

import me.wickersty.mortuusterra.MortuusTerra;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.struct.ChatMode;

public class ChatListener implements Listener {

	private final MortuusTerra instance;
	
	public ChatListener(MortuusTerra instance) {
		
		this.instance = instance;
		
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void playerSpeaks(AsyncPlayerChatEvent event) {
		
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

			
			
			// do we have factions installed?
			if (instance.getServer().getPluginManager().isPluginEnabled("Factions") == true) {
				
				// handle chat interruption with respect to Factions
				FPlayer fPlayer = FPlayers.i.get(event.getPlayer());

				if (!fPlayer.getChatMode().equals(ChatMode.PUBLIC)) {
					
					// if the Faction Player is NOT in Public Chat Mode, relinquish control
					return;
					
				}
				
			}
			

		    
		    // where is this message coming from?
			if (instance.getConfigManager().isWorldEnabled(senderWorld.getName()) == true) {
				
				// cancel the event
				event.setCancelled(true);
				
				
				
				// prepare the message
				String message = ChatColor.DARK_AQUA + sender.getName() + ": " + ChatColor.GRAY + event.getMessage();
				
				// do we have factions installed?
				if (instance.getServer().getPluginManager().isPluginEnabled("Factions") == true) {

					FPlayer fPlayer = FPlayers.i.get(event.getPlayer());
					
					if (!fPlayer.getTag().equalsIgnoreCase("")) {
					
						message = ChatColor.WHITE + "{" + fPlayer.getTag() +  "} " + message;
						
					}
					
				}

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
				int recipientsReceived = 0;
				for (Player recipient : recipients) {
					
					//  (local chat)
					if (recipient.getLocation().distance(sender.getLocation()) <= instance.getConfigManager().clearChatRange) {
						
						// recipient receives clear message					
						recipient.sendMessage(message);
						
						recipientsReceived++;						
												
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
						
						recipientsReceived++;
						
					} else {
						
						// recipient receives no message
						
					}	
					
				}
				
				if (recipientsReceived <= 1) {
					
					sender.sendMessage(ChatColor.GRAY + "No one can hear you.");
					
				}
									
			} else {
	
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
	
}
