package me.Katerose.ProgressBar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import me.Katerose.NMS.versions.API.ActionBarAPI;
import me.Katerose.NMS.versions.API.VersionUtils;
import me.Katerose.RoseCpsLimiter.Commands;
import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;
import me.Katerose.RoseCpsLimiter.SettingsManager;
import me.Katerose.RoseCpsLimiter.API.GetRCLAPI;
import me.Katerose.RoseCpsLimiter.IridiumColor.IridiumColorAPI;

public class Creator {
	
	public static void sendBar(Player player, String actionbar) {
		if (VersionUtils.isNewVersion()) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionbar));
		}else {
			ActionBarAPI.sendActionBar(player, actionbar, 2, (Plugin)RoseCpsLimiter.getPlugin(RoseCpsLimiter.class));
		}
	}
	
	public static char getHeadCharacter()
    {
        String s = SettingsManager.getConfig().getString("Messages.Toggle.Progress.char");
        char[] s1 = s.toCharArray();
            return s1[0];
    }
	
	public static void kickPlayer(Player player, String message) {
		player.kickPlayer(ChatColor.translateAlternateColorCodes('&', message));
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §e" + player.getName() + " §4kicked from server.");
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §5CPS: §d" + RoseCpsLimiter.getMain().leftclicks.get(player.getUniqueId()));
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §5Limit: §d" + SettingsManager.getConfig().getInt("Settings.Limit"));
		Bukkit.getConsoleSender().sendMessage(" ");
	}
	
	public static void getTA(Player p) {
		Integer limit = SettingsManager.getConfig().getInt("Settings.Limit");
		if (Commands.show.contains(p.getUniqueId())) {
			if ((p.hasPermission("rosecpslimiter.use.bypass")) || (p.isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
				sendBar(p, ChatColor.DARK_PURPLE + "BYPASS");
			}else {
				if (SettingsManager.getConfig().getBoolean("Messages.Toggle.Progress.enable")) {
    				if (!RoseCpsLimiter.getMain().isfreeze.contains(p.getUniqueId())) {
        				if (!RoseCpsLimiter.getMain().leftclicks.containsKey(p.getUniqueId())) {
        					
        					/*
        					 *  SEND ACTIONBAR
        					 */
        				
        					sendBar(p, RoseCpsLimiter.getMain().sendBar()
        							.getProgressBar(limit, 0, getHeadCharacter(), ChatColor.GRAY ,ChatColor.GREEN));
        					
        				}else {
        					
        					/*
        					 *  SEND ACTIONBAR
        					 */
        					
        					sendBar(p, RoseCpsLimiter.getMain().sendBar().getProgressBar(limit, 
        							RoseCpsLimiter.getMain().leftclicks.get(p.getUniqueId()), getHeadCharacter(), ChatColor.GRAY , ChatColor.GREEN));
        					
        				}
    				}else {
    					if (!RoseCpsLimiter.getMain().leftclicks.containsKey(p.getUniqueId())) {
    						
    						/*
        					 *  SEND ACTIONBAR
        					 */
    						
    						sendBar(p, RoseCpsLimiter.getMain().sendBar()
    								.getProgressBar(limit, RoseCpsLimiter.getMain().leftclicks2.get(p.getUniqueId()), 
    										getHeadCharacter(), ChatColor.RED, ChatColor.RED));
        					
        				}else {
        					
        					/*
        					 *  SEND ACTIONBAR
        					 */
        					
        					sendBar(p, RoseCpsLimiter.getMain().sendBar()
        							.getProgressBar(limit, RoseCpsLimiter.getMain().leftclicks.get(p.getUniqueId()), 
        									getHeadCharacter(), ChatColor.RED, ChatColor.RED));
        					
        				}
    				}
				}else {
					if (!RoseCpsLimiter.getMain().isfreeze.contains(p.getUniqueId()) 
							|| SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Sender.ActionBar.enable") == false) {
        				if (!RoseCpsLimiter.getMain().leftclicks.containsKey(p.getUniqueId())) {
        					sendBar(p, IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Toggle.actionbar")
        							.replace("{cps}", String.valueOf(RoseCpsLimiter.getMain().leftclicks2.get(p.getUniqueId())))
        							.replace("{limit}", String.valueOf(SettingsManager.getConfig().getInt("Settings.Limit")))
        							.replace("{lastcps}", String.valueOf(GetRCLAPI.getLastCps(p)))));
        				}else {
        					sendBar(p, IridiumColorAPI.process(SettingsManager.getConfig().getString("Messages.Toggle.actionbar")
        							.replace("{cps}", String.valueOf(RoseCpsLimiter.getMain().leftclicks.get(p.getUniqueId())))
        							.replace("{limit}", String.valueOf(SettingsManager.getConfig().getInt("Settings.Limit")))
        							.replace("{lastcps}", String.valueOf(GetRCLAPI.getLastCps(p)))));
        				}
    				}
    				else {
    					sendBar(p, IridiumColorAPI.process(SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Sender.ActionBar.actionbar")));
    				}
					if (!RoseCpsLimiter.getMain().leftclicks.containsKey(p.getUniqueId())) {
						RoseCpsLimiter.getMain().leftclicks2.put(p.getUniqueId(), 0);
					}
				}
			}
		}
	}
}
