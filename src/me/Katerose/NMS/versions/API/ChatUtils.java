package me.Katerose.NMS.versions.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class ChatUtils {
	
	private static boolean placeholderAPI = false;
	
	public static void placeholderAPI(boolean api) {
		placeholderAPI = api;
	}
	
	public static boolean FastLogin = false;
	
	public static void FastLogin(boolean bol) {
		FastLogin = bol;
	}
	
    public static String colorCodes(String nonColoredText) {
        if (nonColoredText == null)
            return nonColoredText; 
        if (nonColoredText.isEmpty())
            return nonColoredText; 
        if (nonColoredText.length() <= 0)
            return nonColoredText; 
        if (nonColoredText == "")
            return nonColoredText; 
        if (nonColoredText == " ")
            return nonColoredText; 
        String coloredText = ChatColor.translateAlternateColorCodes('&', nonColoredText);
        return coloredText;
    }
    
    public static String replaceUcode(String string) {
	String newString = string;
    while (newString.contains("<ucode")) {
        String code = newString.split("<ucode")[1].split(">")[0];
		newString = newString.replaceAll("<ucode" + code + ">", 
		String.valueOf((char)Integer.parseInt(code, 16)));
    } 
    if (newString.contains("<a>"))
        newString = newString.replaceAll("<a>", "á"); 
	if (newString.contains("<e>"))
		newString = newString.replaceAll("<e>", "é"); 
	if (newString.contains("<i>"))
		newString = newString.replaceAll("<i>", "í"); 
	if (newString.contains("<o>"))
		newString = newString.replaceAll("<o>", "ó"); 
	if (newString.contains("<u>"))
        newString = newString.replaceAll("<u>", "ú");
    return newString;
    }
    
    public static String papicolor(String string, Player p) {
	    if (string == null)
		    return ""; 
		String newString = string;
		if (newString.contains("<player>"))
		      newString = newString.replaceAll("<player>", p.getName()); 
		
		if (placeholderAPI)
		    try {
		        newString = PlaceholderAPI.setPlaceholders(p, newString);
		    } catch (Exception exception) {}
		    newString = colorCodes(newString);
		    return newString;
    }
    
	public static List<String> replaceList(List<String> list, OfflinePlayer p) {
	    List<String> localList = new ArrayList<>();
		localList.addAll(list);
		for (int i = 0; i < localList.size(); i++)
		    localList.set(i, papicolor(localList.get(i), (Player) p)); 
		    return localList;
    }
	
	public static List<String> replaceList(List<String> paramList) {
        ArrayList<String> arrayList = new ArrayList<>();
		arrayList.addAll(paramList);
		for (byte b = 0; b < arrayList.size(); b++) {
		    String str = colorCodes(arrayList.get(b));
		    arrayList.set(b, str);
		} 
		return arrayList;
	}
}
