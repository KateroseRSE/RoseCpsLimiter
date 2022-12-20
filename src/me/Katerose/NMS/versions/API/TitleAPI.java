package me.Katerose.NMS.versions.API;

import java.lang.reflect.Constructor;
import org.bukkit.entity.Player;

public class TitleAPI {
  public static void sendTitles(Player player, String title) {
    send(player, Integer.valueOf(20), Integer.valueOf(50), Integer.valueOf(10), ChatUtils.colorCodes(title), " ");
  }
  
  public static void sendTitles(Player player, String title, String subtitle) {
    send(player, Integer.valueOf(20), Integer.valueOf(50), Integer.valueOf(10), ChatUtils.colorCodes(title), ChatUtils.colorCodes(subtitle));
  }
  
  public static void sendTitles(Player player, Integer stay, String title, String subtitle) {
    send(player, Integer.valueOf(20), Integer.valueOf((stay.intValue() <= 0) ? 50 : stay.intValue()), Integer.valueOf(10), ChatUtils.colorCodes(title), ChatUtils.colorCodes(subtitle));
  }
  
  public static void sendTitles(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
    if (VersionUtils.Version.getVersion().esMayorIgual(VersionUtils.Version.v1_17)) {
      player.sendTitle(title, subtitle, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
    } else {
      send(player, Integer.valueOf((fadeIn.intValue() <= 0) ? 20 : fadeIn.intValue()), Integer.valueOf((stay.intValue() <= 0) ? 50 : stay.intValue()), Integer.valueOf((fadeOut.intValue() <= 0) ? 10 : fadeOut.intValue()), ChatUtils.colorCodes(title), ChatUtils.colorCodes(subtitle));
    } 
  }
  
  private static void send(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
    try {
      Object chat = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
      Constructor<?> Constructor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"), int.class, int.class, int.class });
      Object timePacket = Constructor.newInstance(new Object[] { Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null), chat, fadeIn, stay, fadeOut });
      Constructor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent") });
      Object titlePacket = Constructor.newInstance(new Object[] { Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chat });
      chat = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
      Object subtitlePacket = Constructor.newInstance(new Object[] { Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chat });
      Reflection.sendPacket(player, timePacket);
      Reflection.sendPacket(player, titlePacket);
      Reflection.sendPacket(player, subtitlePacket);
    } catch (Exception var11) {
      var11.printStackTrace();
    } 
  }
}
