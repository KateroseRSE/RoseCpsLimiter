package me.Katerose.ProgressBar;

import org.bukkit.craftbukkit.libs.joptsimple.internal.Strings;
/*
 * 
 *  Running 1.8-1.13
 * 
 */
import net.md_5.bungee.api.ChatColor;

public class Bar_18_113 implements Bars{
	
	/**
     * Generate a new progress bar.
     * This method use also his partner: {@see ProgressBar#getProgressBar(int, int, char, ChatColor, ChatColor);}.
     * @param totalBars The amount of bars in the string
     * @param completedPercentage The completed percentage of the bars amount
     * @param symbol The single bar symbol
     * @param incompleteColor The incomplete color zone of the bar
     * @param completeColor The complete color zone of the bar
     * @return The bar string
     */
    public String getProgressBar(int totalBars, short completedPercentage, char symbol, ChatColor incompleteColor, ChatColor completeColor) {
        int current = percentage(totalBars, completedPercentage);
        return getProgressBar(totalBars, current, symbol, incompleteColor, completeColor);
    }

    /**
     * Generate a new progress bar
     * @param totalBars The amount of bars in the string
     * @param completeAmount The completed amount of the bars
     * @param symbol The single bar symbol
     * @param incompleteColor The incomplete color zone of the bar
     * @param completeColor The complete color zone of the bar
     * @return The bar string
     */
    public String getProgressBar(int totalBars, int completeAmount, char symbol, ChatColor incompleteColor, ChatColor completeColor) {
        return incompleteColor+Strings.repeat(symbol, completeAmount)+completeColor+Strings.repeat(symbol, totalBars-completeAmount);
    }


    /**
     * This algorithm calculates the part of the total with respect to the percentage.
     * @param max The total part
     * @param percentage The percentage part respect {@param max}.
     * @return A total amount part
     */
    public int percentage(int max, short percentage) {
        return Math.round((max*percentage)/100);
    }/**
     * This algorithm calculates the percentage of a part of the total.
     * @param max The total part
     * @param part The part that will become a percentage
     * @return The percentage that represent the part of the total.
     */
    public int percentage(int max, int part) {
        return Math.round((part*100)/max);
    }
}
