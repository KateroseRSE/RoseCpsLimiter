package me.Katerose.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.bukkit.Bukkit;

import me.Katerose.RoseCpsLimiter.RoseCpsLimiter;

public class Checker {
	
	/**
     * URL used to get the latest version.
     */
    public static final String URL = "https://api.spigotmc.org/legacy/update.php?resource=90725";

    /**
     * Recheck delay in minutes.
     */
    private static final long RECHECK_DELAY = 12 * 60 * 60 * 20L;

    /**
     * Starts the updater (and it's repeating task repeatedly checking for a new update).
     *
     * @param plugin the plugin instance
     */
    public static void watch(RoseCpsLimiter plugin) {
        // Version
        String version = plugin.getDescription().getVersion();

        // Schedule
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            // The latest version
            String latest;
            // Read
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(URL).openStream()))) {
                latest = reader.readLine();
            } catch (IOException ignored) {
                return;
            }

            // New version available
            if (Integer.parseInt(latest.replace(".", "").replace("v", "")) > Integer.parseInt(version.replace(".", "").replace("v", "")))
                plugin.getLogger().warning("New version " + latest.replace("v", "") + " is available! You are using version " + version + ".");
        }, 0L, RECHECK_DELAY);
    }
}
