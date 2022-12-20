package me.Katerose.RoseCpsLimiter.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnFreezeEvent extends Event {
	
    private static final HandlerList handlers = new HandlerList();
    static Player player;

    public OnFreezeEvent(Player p) {
    	player = p;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public Player getPlayer() {
    	return player;
    }

}
