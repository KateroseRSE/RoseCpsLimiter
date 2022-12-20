package me.Katerose.RoseCpsLimiter;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import net.md_5.bungee.api.ChatColor;

public class SettingsManager {
    
	static SettingsManager instance = new SettingsManager();
    
    public static SettingsManager getInstance() {
        return instance;
    }
	
	Plugin p;
    
    static FileConfiguration config;
    static FileConfiguration data;
    static File cfile;
    static File dfile;
    
    public static void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "config.yml");
	        
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }
        
        if (!cfile.exists()){
            try{
                cfile.createNewFile();
                p.saveResource("config.yml", true);

            }catch (IOException e){
            	Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml!");
            }
        }
        config = YamlConfiguration.loadConfiguration(cfile);
        
        dfile = new File(p.getDataFolder(), "data.yml");
        if (!dfile.exists()){
            try{
                dfile.createNewFile();
                p.saveResource("data.yml", true);

            }catch (IOException e){
            	Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
            }
        }
        data = YamlConfiguration.loadConfiguration(dfile);

    }
    public static void saveData() {
    	try {
    	    data.save(dfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
        }

    }
    
    public static void saveConfig() {
    	try {
    	    config.save(cfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }
    
    public static FileConfiguration getConfig() {
        return config;
    }
    
    public static void reloadConfig() {
    	config = YamlConfiguration.loadConfiguration(cfile);
    }
    
    public static FileConfiguration getData() {
        return data;
    }
    
    public static void reloadData() {
    	data = YamlConfiguration.loadConfiguration(dfile);
    }
    
    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }
}
