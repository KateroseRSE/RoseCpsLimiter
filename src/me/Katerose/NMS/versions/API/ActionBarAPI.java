package me.Katerose.NMS.versions.API;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarAPI {
	  public static Map<UUID, Integer> timers = new HashMap<>();
	  
	  private static class SendRun extends BukkitRunnable {
	    private int duration = 0;
	    
	    private Player player;
	    
	    private String message;
	    
	    public SendRun(int duration, Player player, String message) {
	      this.duration = duration;
	      this.player = player;
	      this.message = message;
	    }
	    
	    public void run() {
	      if (this.duration > 0) {
	        sendActionBar(this.player, ChatUtils.colorCodes(this.message));
	        this.duration -= 20;
	      } else {
	        cancel();
	        timers.remove(this.player.getUniqueId());
	      } 
	    }
	  }
	  
	  private static void cancelTimer(Player player) {
	    Integer timerID = timers.remove(player.getUniqueId());
	    if (timerID != null)
	      Bukkit.getScheduler().cancelTask(timerID.intValue()); 
	  }
	  
	  public static void sendActionBar(Player player, String message, int duration, Plugin plugin) {
	    if (timers.containsKey(player.getUniqueId()))
	      cancelTimer(player); 
	    timers.put(player.getUniqueId(), Integer.valueOf((new SendRun(duration, player, message)).runTaskTimer(plugin, 0L, 20L).getTaskId()));
	  }
	  
	  public static void sendActionBar(Player player, String message) {
	    if (!player.isOnline())
	      return; 
	    if (VersionUtils.Version.getVersion().esMenorIgual(VersionUtils.Version.v1_11_x)) {
	      sendPre_1_12(player, ChatUtils.colorCodes(message));
	    } else if (VersionUtils.Version.getVersion().esMenorIgual(VersionUtils.Version.v1_15_x)) {
	      sendPos_1_12_Pre_1_16(player, ChatUtils.colorCodes(message));
	    } else if (VersionUtils.Version.getVersion().esMenorIgual(VersionUtils.Version.v1_16_x)) {
	      sendPos_1_16_Pre_1_17(player, ChatUtils.colorCodes(message));
	    } else {
	      sendPos_1_17(player, ChatUtils.colorCodes(message));
	    } 
	  }
	  
	  private static void sendPos_1_17(Player player, String message) {
	    try {
	      Class<?> chatClass = Reflection.getClass("net.minecraft.network.chat.IChatBaseComponent");
	      Object ationmesage = Reflection.getClass("net.minecraft.network.chat.ChatComponentText").getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
	      Class<?> chatMessageTypeClass = Reflection.getClass("net.minecraft.network.chat.ChatMessageType");
	      Constructor<?> constructor = Reflection.getClass("net.minecraft.network.protocol.game.PacketPlayOutChat").getConstructor(new Class[] { chatClass, chatMessageTypeClass, Class.forName("java.util.UUID") });
	      Object packet = constructor.newInstance(new Object[] { ationmesage, chatMessageTypeClass.getField("c").get(null), player.getUniqueId() });
	      Reflection.sendPacketPos_1_17(player, packet);
	    } catch (Exception var11) {
	      var11.printStackTrace();
	    } 
	  }
	  
	  private static void sendPos_1_16_Pre_1_17(Player player, String message) {
	    try {
	      Class<?> chat = Reflection.getNMSClass("IChatBaseComponent");
	      Object ationmesage = Reflection.getNMSClass("ChatComponentText").getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
	      Class<?> chatMessageTypeClass = Reflection.getNMSClass("ChatMessageType");
	      Constructor<?> constructor = Reflection.getNMSClass("PacketPlayOutChat").getConstructor(new Class[] { chat, chatMessageTypeClass, Class.forName("java.util.UUID") });
	      Object packet = constructor.newInstance(new Object[] { ationmesage, Reflection.getNMSClass("ChatMessageType").getField("GAME_INFO").get(null), player.getUniqueId() });
	      Reflection.sendPacket(player, packet);
	    } catch (Exception var11) {
	      var11.printStackTrace();
	    } 
	  }
	  
	  private static void sendPos_1_12_Pre_1_16(Player player, String message) {
	    try {
	      Class<?> chat = Reflection.getNMSClass("IChatBaseComponent");
	      Object ationmesage = chat.getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + message + "\"}" });
	      Class<?> chatMessageTypeClass = Reflection.getNMSClass("ChatMessageType");
	      Constructor<?> constructor = Reflection.getNMSClass("PacketPlayOutChat").getConstructor(new Class[] { chat, chatMessageTypeClass });
	      Object packet = constructor.newInstance(new Object[] { ationmesage, Reflection.getNMSClass("ChatMessageType").getField("GAME_INFO").get(null) });
	      Reflection.sendPacket(player, packet);
	    } catch (Exception var11) {
	      var11.printStackTrace();
	    } 
	  }
	  
	  private static void sendPre_1_12(Player player, String message) {
	    try {
	      Class<?> chat = Reflection.getNMSClass("IChatBaseComponent");
	      Object ationmesage = chat.getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + message + "\"}" });
	      Constructor<?> constructor = Reflection.getNMSClass("PacketPlayOutChat").getConstructor(new Class[] { chat, byte.class });
	      Object packet = constructor.newInstance(new Object[] { ationmesage, Byte.valueOf((byte)2) });
	      Reflection.sendPacket(player, packet);
	    } catch (Exception var11) {
	      var11.printStackTrace();
	    } 
	  }
	}