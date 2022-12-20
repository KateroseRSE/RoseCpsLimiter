package me.Katerose.RoseCpsLimiter.Clicker;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.Katerose.ProgressBar.Creator;
import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;
import me.Katerose.RoseCpsLimiter.SettingsManager;
import me.Katerose.RoseCpsLimiter.API.Events.OnFreezeEvent;
import me.Katerose.RoseCpsLimiter.Handler.Data;
import me.Katerose.RoseCpsLimiter.IridiumColor.IridiumColorAPI;

public class ActionSender {
	
	public void adminBypass(Player player, String msg) {
	    if (player.isOp() && SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass") || player.hasPermission("rosecpslimiter.use.bypass")) {
	    	(RoseCpsLimiter.getMain()).isfreeze.add(player.getUniqueId());
	    	String prefix = SettingsManager.getConfig().getString("Settings.Prefix");
	    	String chat = SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Sender.Chat.chat");
	    	if (SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Sender.Chat.enable"))
	    		player.sendMessage(IridiumColorAPI.process(String.valueOf(prefix) + chat.replace("{sec}", String.valueOf(SettingsManager.getConfig().getInt("Settings.Freeze-Second"))))); 
	    	Bukkit.getServer().getPluginManager().callEvent(new OnFreezeEvent(player));
	    } else if (!SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass")) {
	    	Creator.kickPlayer(player, msg);
	    } else {
	    	Creator.kickPlayer(player, msg);
	    } 
	}
	  
	public void sendCps(final Player player) {
		if (!(RoseCpsLimiter.getMain()).leftclicks.containsKey(player.getUniqueId()))
			Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)RoseCpsLimiter.getMain(), new Runnable() {
				public void run() {
					
					for (String key : SettingsManager.getConfig().getConfigurationSection("Protect.Left-Click.On-Freeze.Exceeding-Limit").getKeys(false)) {
						int cps = ((Integer)(RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId())).intValue();
						if (cps >= Integer.parseInt(key)) {
							if ((!player.isOp() && !player.hasPermission("rosecpslimiter.use.bypass")) || (player.isOp() && !SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.AdminBypass"))) {
								for (String command : SettingsManager.getConfig().getStringList("Protect.Left-Click.On-Freeze.Exceeding-Limit." + key + ".Run-Command")) {
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
								(RoseCpsLimiter.getMain()).isfreeze.add(player.getUniqueId());
								if (SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Sender.Chat.enable"))
									player.sendMessage(IridiumColorAPI.process(String.valueOf(SettingsManager.getConfig().getString("Settings.Prefix")) + SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Sender.Chat.chat").replace("{sec}", String.valueOf(SettingsManager.getConfig().getInt("Settings.Freeze-Second"))))); 
								Bukkit.getServer().getPluginManager().callEvent(new OnFreezeEvent(player));
							} 
							(RoseCpsLimiter.getMain()).leftlastclick.put(player.getUniqueId(), (Integer)(RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId()));
							Data.warnDataFile(player, true, cps);
							(RoseCpsLimiter.getMain()).leftclicks.remove(player.getUniqueId());
							return;
						} 
					}
					
					if (((Integer)(RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId())) >= SettingsManager.getConfig().getInt("Settings.Limit")) {
						if (SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Kick.enable")) {
							String msg = SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Kick.message");
							msg = msg.replace("{cps}", String.valueOf((RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId())));
							msg = msg.replace("{limit}", String.valueOf(SettingsManager.getConfig().getInt("Settings.Limit")));
							msg = msg.replace("<nextline>", "\n");
							(RoseCpsLimiter.getMain()).leftlastclick.remove(player.getUniqueId());
							(RoseCpsLimiter.getMain()).leftlastclick.put(player.getUniqueId(), Integer.valueOf(0));
							adminBypass(player, msg);
						} else {
							(RoseCpsLimiter.getMain()).isfreeze.add(player.getUniqueId());
							if (SettingsManager.getConfig().getBoolean("Protect.Left-Click.On-Freeze.Sender.Chat.enable"))
								player.sendMessage(IridiumColorAPI.process(String.valueOf(SettingsManager.getConfig().getString("Settings.Prefix")) + SettingsManager.getConfig().getString("Protect.Left-Click.On-Freeze.Sender.Chat.chat").replace("{sec}", String.valueOf(SettingsManager.getConfig().getInt("Settings.Freeze-Second"))))); 
							Bukkit.getServer().getPluginManager().callEvent(new OnFreezeEvent(player));
						}  
						Data.warnDataFile(player, false, ((Integer)(RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId())));
					}
					(RoseCpsLimiter.getMain()).leftlastclick.put(player.getUniqueId(), (Integer)(RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId()));
					(RoseCpsLimiter.getMain()).leftclicks.remove(player.getUniqueId());
				}
			},25L); 
	    check(player);
	  }
	  
	  public void check(Player player) {
		  if ((RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId()) == null)
			  (RoseCpsLimiter.getMain()).leftclicks.put(player.getUniqueId(), Integer.valueOf(0)); 
		  (RoseCpsLimiter.getMain()).leftclicks.put(player.getUniqueId(), Integer.valueOf(((Integer)(RoseCpsLimiter.getMain()).leftclicks.get(player.getUniqueId())).intValue() + 1));
	  }
}
