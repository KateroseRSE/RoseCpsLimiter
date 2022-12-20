package me.Katerose.RoseCpsLimiter.Clicker;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;
import me.Katerose.RoseCpsLimiter.SettingsManager;

public class Left implements Listener{
	
	public ArrayList<UUID> dropp = new ArrayList<>();
	public ActionSender sender = new ActionSender();
	  
	@EventHandler(priority = EventPriority.MONITOR)
	public void drop(final PlayerDropItemEvent event) {
		dropp.add(event.getPlayer().getUniqueId());
		Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)RoseCpsLimiter.getMain(), new Runnable() {
	          public void run() {
	            dropp.remove(event.getPlayer().getUniqueId());
	          }
	        },  2L);
	  }
	  
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerClicks(final PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		Action action = e.getAction();
		if (!(RoseCpsLimiter.getMain()).isfreeze.contains(player.getUniqueId())) {
			if (e.getPlayer().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass") || player.hasPermission("rosecpslimiter.use.bypass"))
				return; 
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)RoseCpsLimiter.getMain(), new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					if (!dropp.contains(player.getUniqueId())) {
						if (player.getItemInHand() == new ItemStack(Material.AIR))
							return; 
						if (e.getAction().equals(Action.PHYSICAL))
							return; 
						if (player.getItemInHand() == new ItemStack(Material.DIAMOND_SWORD))
							return; 
	                return;
					} 
				}
			},  1L);
			if (action == Action.LEFT_CLICK_AIR || 
					action == Action.LEFT_CLICK_BLOCK || 
					action == Action.RIGHT_CLICK_AIR || 
					action == Action.RIGHT_CLICK_BLOCK)
				sender.sendCps(player); 
	    } 
	  }
}