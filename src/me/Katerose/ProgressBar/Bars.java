package me.Katerose.ProgressBar;

import net.md_5.bungee.api.ChatColor;

public interface Bars{
	
	public String getProgressBar(int totalBars, int completeAmount, char symbol, ChatColor incompleteColor, ChatColor completeColor);
	public String getProgressBar(int totalBars, short completedPercentage, char symbol, ChatColor incompleteColor, ChatColor completeColor);
	public int percentage(int max, short percentage);
	public int percentage(int max, int part);
}
