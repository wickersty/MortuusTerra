package me.wickersty.mortuusterra.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityRadiationDamageEvent extends Event implements Cancellable {

   private static final HandlerList handlers = new HandlerList();

   private Double radiationDamageReceived;
   private boolean containsWaterBasedRadiation;
   private boolean containsStormBasedRadiation;
   private Player player;
   private Boolean cancelled;

   public EntityRadiationDamageEvent(Player player, Double radiationDamageReceived) {
	   
	   this.player = player;
	   this.radiationDamageReceived = radiationDamageReceived;
	   
	   if (this.cancelled == null) {
		   
		   this.cancelled = false;
		   
	   }
	   
   }
   
   public Double getRadiationDamage() {
	   
	   return radiationDamageReceived;
	   
   }
   
   public boolean containsWaterBasedRadiation() {
	   
	   return containsWaterBasedRadiation;
	   
   }
   
   public boolean containsStormBasedRadiation() {
	   
	   return containsStormBasedRadiation;
	   
   }
      
   public void setContainsWaterBasedRadiation(Boolean containsWaterBasedRadiation) {
	   
	   this.containsWaterBasedRadiation = containsWaterBasedRadiation;
	   
   }
   
   public void setContainsStormBasedRadiation(Boolean containsStormBasedRadiation) {
	   
	   this.containsStormBasedRadiation = containsStormBasedRadiation;
	   
   }
      
   public void setRadiationDamage(Double radiationDamageReceived) {
	   
	   this.radiationDamageReceived = radiationDamageReceived;
	   
   }
   
   public Player getPlayer() {
	   
	   return player;
	   
   }
   
   public HandlerList getHandlers() {
	   
	   return handlers;
	   
   }
   
   public static HandlerList getHandlerList() {
	   
	   return handlers;
	   
   }

   @Override
   public boolean isCancelled() {

	   return this.cancelled;
	   
   }
   
   @Override
   public void setCancelled(boolean cancelled) {

	   this.cancelled = cancelled;

   }
   
}
