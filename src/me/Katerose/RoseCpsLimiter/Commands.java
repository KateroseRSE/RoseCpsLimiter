package me.Katerose.RoseCpsLimiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.util.StringUtil;

import me.Katerose.RoseCpsLimiter.IridiumColor.IridiumColorAPI;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor, TabExecutor{
	
	public static ArrayList<UUID> show = new ArrayList<UUID>();
	private String prefix = SettingsManager.getConfig().getString("Settings.Prefix");
	private String unknown = SettingsManager.getConfig().getString("Messages.Main.unknown-command");
	private String permission = SettingsManager.getConfig().getString("Messages.Main.permission");
	private String reload = SettingsManager.getConfig().getString("Messages.Main.reload");
	private void sendPermissions(CommandSender sender) {sender.sendMessage(IridiumColorAPI.process(prefix + "&aPermissions;"));int e = 0;for (Permission i : RoseCpsLimiter.getPlugin(RoseCpsLimiter.class).getDescription().getPermissions()) {e++;sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c{&4" +e + "&c}&e " + i.getName()));}}
	private void sendHelp(CommandSender sender) {sender.sendMessage(ChatColor.DARK_GRAY + "------------------------------------");sender.sendMessage(" ");sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8▪ &c/rosecpslimiter reload"));sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8▪ &c/rosecpslimiter version"));sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8▪ &c/rosecpslimiter help"));sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8▪ &c/rosecpslimiter toggle"));sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &8▪ &c/rosecpslimiter permissions"));sender.sendMessage(" ");sender.sendMessage(ChatColor.DARK_GRAY + "------------------------------------");}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("rosecpslimiter")) {
			if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
				if(!(sender.hasPermission("rosecpslimiter.use.help"))) {
					sender.sendMessage(IridiumColorAPI.process(prefix + permission));
					return true;
				}
				sendHelp(sender);
				return true;
			}
			else if (args[0].equalsIgnoreCase("permissions")) {
				if(!(sender.hasPermission("rosecpslimiter.use.permissions"))) {
					sender.sendMessage(IridiumColorAPI.process(prefix + permission));
					return true;
				}
				if (args.length > 1) {
					sender.sendMessage(IridiumColorAPI.process(prefix + unknown));
					return true;
				}	
				sendPermissions(sender);
				return true;
			}
			else if (args[0].equalsIgnoreCase("toggle")) {
				if(!(sender.hasPermission("rosecpslimiter.use.toggle"))) {
					sender.sendMessage(IridiumColorAPI.process(prefix + permission));
					return true;
				}
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length > 1) {
						sender.sendMessage(IridiumColorAPI.process(prefix + unknown));
						return true;
					}
					if (!show.contains(p.getUniqueId())) {
						show.add(p.getUniqueId());
						sender.sendMessage(IridiumColorAPI.process(prefix + SettingsManager.getConfig().getString("Messages.Toggle.enabled")));
						return true;
					}else
					{
						show.remove(p.getUniqueId());
						sender.sendMessage(IridiumColorAPI.process(prefix + SettingsManager.getConfig().getString("Messages.Toggle.disabled")));
						return true;
						
					}
				}else {
					Bukkit.getConsoleSender().sendMessage("[RoseCpsLimiter] This command only available for players.");
				}
			}
			else if (args[0].equalsIgnoreCase("reload")) {
				if(!(sender.hasPermission("rosecpslimiter.use.reload"))) {
					sender.sendMessage(IridiumColorAPI.process(prefix + permission));
					return true;
				}
				if (args.length > 1) {
					sender.sendMessage(IridiumColorAPI.process(prefix + unknown));
					return true;
				}
				SettingsManager.reloadConfig();
				sender.sendMessage(IridiumColorAPI.process(prefix + reload));
				Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §cCPS Limit: §e" + SettingsManager.getConfig().getInt("Settings.Limit"));
				return true;
			}
			else if (args[0].equalsIgnoreCase("version")) {
				if(!(sender.hasPermission("rosecpslimiter.use.version"))) {
					sender.sendMessage(IridiumColorAPI.process(prefix + permission));
					return true;
				}
				if (args.length > 1) {
					sender.sendMessage(IridiumColorAPI.process(prefix + unknown));
					return true;
				}
				sender.sendMessage(IridiumColorAPI.process(prefix + ChatColor.GRAY + "Version: " + ChatColor.RED + RoseCpsLimiter.getPlugin(RoseCpsLimiter.class).getDescription().getVersion()));
				return true;
			}else {
				 if (!(args[0].equalsIgnoreCase("version") || 
						 args[0].equalsIgnoreCase("help") || 
						 args[0].equalsIgnoreCase("reload") || 
						 args[0].equalsIgnoreCase("toggle") || 
						 args[0].equalsIgnoreCase("permissions"))) {
					 sender.sendMessage(IridiumColorAPI.process(prefix + unknown));
					 return true;
				 }
			}
		}      
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		List<String> completions = new ArrayList<>();
		List<String> commands = new ArrayList<>();
		
		if (arg1.getName().equalsIgnoreCase("rosecpslimiter")) {
			if (arg3.length == 1) {
				if (arg0.hasPermission("rosecpslimiter.use.reload")) {
					commands.add("reload");
				}
				if (arg0.hasPermission("rosecpslimiter.use.version")) {
					commands.add("version");
				}
				if (arg0.hasPermission("rosecpslimiter.use.permissions")) {
					commands.add("permissions");
				}
				if (arg0.hasPermission("rosecpslimiter.use.toggle")) {
					commands.add("toggle");
				}
				if (arg0.hasPermission("rosecpslimiter.use.help")) {
					commands.add("help");
				}
				StringUtil.copyPartialMatches(arg3[0], commands, completions);
			}
			Collections.sort(completions);
			return completions;
		}
		return null;
	}
}
