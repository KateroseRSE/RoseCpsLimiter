package me.Katerose.RoseCpsLimiter.PlaceHolder;

import org.bukkit.entity.Player;

import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class RegisterPlaceholders extends PlaceholderExpansion {
	
	public String getIdentifier() {
		return "RoseCpsLimiter";
	}

	public String getPlugin() {
		return null;
	}

	public String getAuthor() {
		return "Katerose";
	}
	
	public String getVersion() {
		return RoseCpsLimiter.getMain().getDescription().getVersion();
	}
	
    public String onPlaceholderRequest(Player player, String identifier) {
    	if (identifier.equals("cps")) 
    	{
    		if (RoseCpsLimiter.getMain().leftclicks.get(player.getUniqueId()) == null) 
    		{
    			return String.valueOf(0);
    		}
    		else 
    		{
    			return String.valueOf(RoseCpsLimiter.getMain().leftclicks.get(player.getUniqueId()));
    		}
        }
    	if (identifier.equals("lastcps")) 
    	{
    		if (RoseCpsLimiter.getMain().leftlastclick.get(player.getUniqueId()) == null) 
    		{
    			return String.valueOf(0);
    		}
    		else 
    		{
    			return String.valueOf(RoseCpsLimiter.getMain().leftlastclick.get(player.getUniqueId()));
    		}
        }
    	if (identifier.equals("isfreeze")) 
    	{
    		if (RoseCpsLimiter.getMain().isfreeze.contains(player.getUniqueId())) 
    		{
    			return String.valueOf(true);
    		}
    		else 
    		{
    			return String.valueOf(false);
    		}
        }
    	return null;
    }
}
