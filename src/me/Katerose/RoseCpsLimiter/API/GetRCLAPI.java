package me.Katerose.RoseCpsLimiter.API;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;
import me.Katerose.RoseCpsLimiter.SettingsManager;

public class GetRCLAPI {
	
	public static Boolean isFreeze(Player player) {
		if (RoseCpsLimiter.getMain().isfreeze.contains(player.getUniqueId())) {
			return true;
		}
		return false;
	}
	
	public static Integer getLastCps(Player player) {
		if (RoseCpsLimiter.getMain().leftlastclick.get(player.getUniqueId()) == null) {
			return 0;
		}
		return RoseCpsLimiter.getMain().leftlastclick.get(player.getUniqueId());
	}
	
	//public static Integer getRightLastCps(Player player) {
		//if (RoseCpsLimiter.getRoseCpsLimiter.getPlugin(RoseCpsLimiter.class)().rightlastclick.get(player.getUniqueId()) == null) {
			//return 0;
		//}
		//return RoseCpsLimiter.getRoseCpsLimiter.getPlugin(RoseCpsLimiter.class)().rightlastclick.get(player.getUniqueId());
	//}
	
	public static Integer getCps(Player player) {
		if (RoseCpsLimiter.getMain().leftclicks.get(player.getUniqueId()) == null) {
			return 0;
		}
		return RoseCpsLimiter.getMain().leftclicks.get(player.getUniqueId());
	}
	
	//public static Integer getRightCps(Player player) {
		//if (RoseCpsLimiter.getRoseCpsLimiter.getPlugin(RoseCpsLimiter.class)().rightclicks.get(player.getUniqueId()) == null) {
			//return 0;
		//}
		//return RoseCpsLimiter.getRoseCpsLimiter.getPlugin(RoseCpsLimiter.class)().rightclicks.get(player.getUniqueId());
	//}
	
	public static FileConfiguration getConfig() {
		return SettingsManager.getConfig();
	}
}
