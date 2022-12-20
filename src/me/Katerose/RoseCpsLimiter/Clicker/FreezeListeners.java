package me.Katerose.RoseCpsLimiter.Clicker;

import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Sets;

import me.Katerose.NMS.versions.API.TitleAPI;
import me.Katerose.NMS.versions.API.VersionUtils;
import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;
import me.Katerose.RoseCpsLimiter.SettingsManager;
import me.Katerose.RoseCpsLimiter.API.Events.FreezeEndEvent;
import me.Katerose.RoseCpsLimiter.API.Events.OnFreezeEvent;
import me.Katerose.RoseCpsLimiter.IridiumColor.IridiumColorAPI;

public class FreezeListeners implements Listener{
	public ActionSender sender = new ActionSender();
	@EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(e.getDamager().getUniqueId())) {
			if (!SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_ATTACK")) {
				return;
			}
	        if (e.getDamager() instanceof Player) {
	        	if ((e.getDamager().hasPermission("rosecpslimiter.use.bypass")) 
	        			|| (e.getDamager().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
	        		return;
				}
	        	e.setCancelled(true);
	        }
		}
    }
	
	@EventHandler
    public void onEntityDamageL(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player player = (Player)e.getDamager();
			if (!RoseCpsLimiter.getMain().isfreeze.contains(player.getUniqueId())) {
				if ((player.hasPermission("rosecpslimiter.use.bypass")) 
						|| (player.isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
					return;
				}
	    		sender.sendCps(player);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(e.getPlayer().getUniqueId())) {
			if (!SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_BLOCK_BREAK")) {
				return;
			}
			if ((e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) 
					|| (e.getPlayer().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
				return;
			}
	        e.setCancelled(true);
	        e.getPlayer().sendMessage(IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Types.disable-block-break")));
		}
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(e.getPlayer().getUniqueId())) {
			if (!SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_TELEPORT")) {
				return;
			}
			if ((e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) 
					|| (e.getPlayer().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
				return;
			}
	        e.setCancelled(true);
	        e.getPlayer().sendMessage(IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Types.disable-use-teleport")));
		}
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(e.getPlayer().getUniqueId())) {
			if (!SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_USE_COMMANDS")) {
				return;
			}
			if ((e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) 
					|| (e.getPlayer().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
				return;
			}
	        e.setCancelled(true);
	        e.getPlayer().sendMessage(IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Types.disable-use-commands")));
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(e.getPlayer().getUniqueId())) {
			if (!SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_BLOCK_PLACE")) {
				return;
			}
			if ((e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) 
					|| (e.getPlayer().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
				return;
			}
	        e.setCancelled(true);
	        e.getPlayer().sendMessage(IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Types.disable-block-place")));
		}
	}
	
	@EventHandler
	public void onItemConsume(PlayerItemConsumeEvent e) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(e.getPlayer().getUniqueId())) {
			if (!SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_ITEM_CONSUME")) {
				return;
			}
			if ((e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) || (e.getPlayer().isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
				return;
			}
	        e.setCancelled(true);
	        e.getPlayer().sendMessage(IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Types.disable-item-consume")));
		}
	}
	
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		RoseCpsLimiter.getMain().isfreeze.remove(e.getPlayer().getUniqueId());
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		RoseCpsLimiter.getMain().isfreeze.remove(e.getPlayer().getUniqueId());
	}
	
	private Set<UUID> prevPlayersOnGround = Sets.newHashSet();
	 
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onMove(PlayerMoveEvent e) {
    	Player player = e.getPlayer();
    	if (RoseCpsLimiter.getMain().isfreeze.contains(e.getPlayer().getUniqueId())) {
	    	if (SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_JUMP")) {
	    		if ((e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) || (player.isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
		    		return;
		    	}
	    		
		        if (player.getVelocity().getY() > 0) {
		            double jumpVelocity = (double) 0.42F;
		            if (player.hasPotionEffect(PotionEffectType.JUMP)) {
		                jumpVelocity += (double) ((float) (player.getPotionEffect(PotionEffectType.JUMP).getAmplifier() + 1) * 0.1F);
		            }
		            if (e.getPlayer().getLocation().getBlock().getType() != Material.LADDER && prevPlayersOnGround.contains(player.getUniqueId())) {
		                if (!player.isOnGround() && Double.compare(player.getVelocity().getY(), jumpVelocity) == 0) {
		                    e.setCancelled(true);
		                }
		            }
		        }
		        if (player.isOnGround()) {
		            prevPlayersOnGround.add(player.getUniqueId());
		        } else {
		            prevPlayersOnGround.remove(player.getUniqueId());
		        }
	    	}
    	}
    }
    
    public void sendTitle(Player player, String Title, String subTitle) {
    	if (VersionUtils.isNewVersion()) {
			player.sendTitle(Title, subTitle, 20, 60, 20);
		} else {
		    TitleAPI.sendTitles(player, Title, subTitle);
	        
	        
		}
    }
    
    
    
    @EventHandler
    public void onFreeze(final OnFreezeEvent e) {
    	if ((!e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) || (e.getPlayer().isOp() && !SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) 
			for (String command : SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Run-Command")) {
				Player player = e.getPlayer();
				command = command.replaceAll("%player%", player.getName());
				if (command.toLowerCase().startsWith("[PLAYER]")) {
					command = command.substring("[PLAYER]".length());
					Bukkit.dispatchCommand((CommandSender)player, command);
					continue;
				} 
				if (command.toLowerCase().startsWith("[OP]")) {
					command = command.substring("[OP]".length());
					RoseCpsLimiter.getMain().opSender(player, command);
					continue;
				} 
				ConsoleCommandSender console2 = Bukkit.getConsoleSender();
				Bukkit.dispatchCommand((CommandSender)console2, command);
			}  
    	if (SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("RESET_FOODBAR") && (
    			!(e.getPlayer().isOp() && e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) || 
    			(e.getPlayer().isOp() && !SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))))
    		e.getPlayer().setFoodLevel(-1); 
    	if (SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Types").contains("DISABLE_FLY") && (
    			!(e.getPlayer().isOp() && e.getPlayer().hasPermission("rosecpslimiter.use.bypass")) || 
    			(e.getPlayer().isOp() && !SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) && 
    			e.getPlayer().isFlying())
    		e.getPlayer().setFlying(false); 
    	if (SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Sender.Title.enable")) {
    		final String title = IridiumColorAPI.process(SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Sender.Title.title"));
    		final String subtitle = IridiumColorAPI.process(SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Sender.Title.subtitle"));
    		sendTitle(e.getPlayer(), title, subtitle);
    		if (SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Sender.Title.shake"))
    			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)RoseCpsLimiter.getMain(), new Runnable() {
    				public void run() {
    					sendTitle(e.getPlayer(), title, subtitle);
    				}
    			},10L); 
    	 } 
    	Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)RoseCpsLimiter.getMain(), new Runnable() {
    		public void run() {
    			(RoseCpsLimiter.getMain()).isfreeze.remove(e.getPlayer().getUniqueId());
    			Bukkit.getServer().getPluginManager().callEvent(new FreezeEndEvent(e.getPlayer()));
    		}
    	},  RoseCpsLimiter.getMain().getConfig().getInt("Settings.Freeze-Second") * 20L);
    }
    
}
