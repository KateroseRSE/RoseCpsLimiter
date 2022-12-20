package me.Katerose.RoseCpsLimiter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Katerose.ProgressBar.Bar_113_119;
import me.Katerose.ProgressBar.Bar_18_113;
import me.Katerose.ProgressBar.Bars;
import me.Katerose.ProgressBar.Creator;
import me.Katerose.RoseCpsLimiter.Clicker.FreezeListeners;
import me.Katerose.RoseCpsLimiter.Clicker.Left;
import me.Katerose.RoseCpsLimiter.PlaceHolder.RegisterPlaceholders;
import me.Katerose.Update.Checker;

public class RoseCpsLimiter extends JavaPlugin
{
	
	private static RoseCpsLimiter main;
	
	public void opSender(Player player, String command) {
		if(player == null || command == null || command.isEmpty()) return;
		
		if(player.isOp()) 
		{
			Bukkit.dispatchCommand(player, command);
			return;
		}
		
		player.setOp(true);
		Bukkit.dispatchCommand(player, command);
		player.setOp(false);
	}
	
	private Bars bar;
	
	@Override
	public void onEnable() 
	{
		Bukkit.getPluginManager().registerEvents(new Left(), this);
		Bukkit.getPluginManager().registerEvents(new FreezeListeners(), this);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() 
        {
            @Override
            public void run() {
            	for (Player p : Bukkit.getOnlinePlayers()) {
            		if (p != null) {
            			if (!leftclicks.containsKey(p.getUniqueId())) {
    						leftclicks2.put(p.getUniqueId(), 0);
    					}
            			Creator.getTA(p);
            		}
            	}
            }
        }, 20L , 10L);
		SettingsManager.setup(this);
		getConfig().options().copyDefaults(true);
		SettingsManager.reloadConfig();
		main = this;
		if (setupbar()) {
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §d{!} §eProgressBar active!");
		}else {
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §d{!} §eProgressBar de-active!");
		}
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			new RegisterPlaceholders().register();
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §3PAPI: §e%rosecpslimiter_cps%");
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §3PAPI: §e%rosecpslimiter_lastcps%");
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §3PAPI: §e%rosecpslimiter_isfreeze%");
		}
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] =-=-=-=-=-=-=-=-=-=-=-=-=-=");
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §cAuthor: §5Katerose");
		if (SettingsManager.getConfig().getInt("Settings.Limit") != 0) {
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §cCPS Limit: §e" + getConfig().getInt("Settings.Limit"));
		}else {
			Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §cCPS Limit: §4_notset_");
		}
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §cVersion: §e" + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §cWorking version: §a" + Bukkit.getServer().getBukkitVersion());
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] =-=-=-=-=-=-=-=-=-=-=-=-=-=");
		Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §dColors are powered by §5IridiumColorAPI.");
		Checker.watch(this);
		getCommand("rosecpslimiter").setExecutor(new Commands());
		
	}
	
	private boolean setupbar() {

        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }

        Bukkit.getConsoleSender().sendMessage("§8[§cRoseCpsLimiter§8] §eYour server is running version §d" + version);

        if (version.equals("v1_8_R1")) {bar = new Bar_18_113();}
        else if (version.equals("v1_8_R2")) {bar = new Bar_18_113();}
        else if (version.equals("v1_8_R3")) {bar = new Bar_18_113();}
        else if (version.equals("v1_9_R1")) {bar = new Bar_18_113();}
        else if (version.equals("v1_9_R2")) {bar = new Bar_18_113();}
        else if (version.equals("v1_10_R1")) {bar = new Bar_18_113();}
        else if (version.equals("v1_11_R1")) {bar = new Bar_18_113();}
        else if (version.equals("v1_12_R1")) {bar = new Bar_18_113();}
        else if (version.equals("v1_13_R1")) {bar = new Bar_113_119();}
        else if (version.equals("v1_13_R2")) {bar = new Bar_113_119();}
        else if (version.equals("v1_14_R1")) {bar = new Bar_113_119();}
        else if (version.equals("v1_15_R1")) {bar = new Bar_113_119();}
        else if (version.equals("v1_16_R1")) {bar = new Bar_113_119();}
        else if (version.equals("v1_16_R2")) {bar = new Bar_113_119();}
        else if (version.equals("v1_16_R3")) {bar = new Bar_113_119();}
        else if (version.equals("v1_17_R1")) {bar = new Bar_113_119();}
        else if (version.equals("v1_18_R1")) {bar = new Bar_113_119();}
        else if (version.equals("v1_18_R2")) {bar = new Bar_113_119();}
        else if (version.equals("v1_19_R1")) {bar = new Bar_113_119();}
        
        return bar != null;
    }
	
	public static RoseCpsLimiter getMain() {
		return main;
	}
	
	public Bars sendBar(){return bar;}
    
	public       Map<UUID, Integer> leftclicks        = new HashMap<UUID, Integer>();
	public       Map<UUID, Integer> leftclicks2       = new HashMap<UUID, Integer>();
	public       Map<UUID, Integer> leftlastclick     = new HashMap<UUID, Integer>();
	public       ArrayList<UUID> isfreeze             = new ArrayList<UUID>();
}
