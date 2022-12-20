package me.Katerose.RoseCpsLimiter.Handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.entity.Player;

import me.Katerose.RoseCpsLimiter.SettingsManager;

public class Data {
	
	public static void warnDataFile(final Player player, final Boolean hasExceedingLimit, int cps) {
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss");
		final String world = "World-Name:" + player.getWorld().getName();
		final String exceedinglimit = "Exceeding-Limit:" + hasExceedingLimit;
		final String isOp = "Admin:" + player.isOp();
		final String dateformat = "[" + format.format(now) + "] ";
		final String lastcps = "Cps:" + String.valueOf(cps);
		final String limit = "Limit:" + String.valueOf(SettingsManager.getConfig().getInt("Settings.Limit"));
		final String freezeSec = "Freeze-Second:" + String.valueOf(SettingsManager.getConfig().getInt("Settings.Freeze-Second"));
		
		if (SettingsManager.getData().getString("PlayerData." + player.getUniqueId().toString() + ".Name") == null) {
			SettingsManager.getData().set("PlayerData." + player.getUniqueId().toString() + ".Name", player.getName());
		}
		
		List<String> list = SettingsManager.getData().getStringList("PlayerData." + player.getUniqueId().toString() + ".Detection-List");
		
		if (hasExceedingLimit.booleanValue()) {
		      list.add(String.valueOf(dateformat) + world + "|" + lastcps + "|" + limit + "|" + freezeSec + "|" + exceedinglimit + "|" + isOp);
		      SettingsManager.getData().set("PlayerData." + player.getUniqueId().toString() + ".Detection-List", list);
		      SettingsManager.saveData();
		} else {
		      list.add(String.valueOf(dateformat) + world + "|" + lastcps + "|" + limit + "|" + freezeSec + "|" + exceedinglimit  + "|" + isOp);
		      SettingsManager.getData().set("PlayerData." + player.getUniqueId().toString() + ".Detection-List", list);
		      SettingsManager.saveData();
		} 
	}
}
