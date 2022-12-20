package me.Katerose.NMS.versions.API;

import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reflection {
    public static Class<?> getNMSClass(String name) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    return getClass("net.minecraft.server." + version + "." + name);
    }
		  
    public static Class<?> getNMSClassArray(String name) {
	    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    return getClass("[Lnet.minecraft.server." + version + "." + name + ";");
    }
   
    public static Class<?> getCraftClass(String name) {
	    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	    return getClass("org.bukkit.craftbukkit." + version + "." + name);
    }
		  
    public static Class<?> getBukkitClass(String name) {
	    return getClass("org.bukkit." + name);
    }
		  
    public static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
		    Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
		    playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
		      e.printStackTrace();
		    } 
		  }
		  
		  public static void sendPacketPos_1_17(Player player, Object packet) {
		    try {
		      Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
		      Object playerConnection = handle.getClass().getField("b").get(handle);
		      Object networkManager = playerConnection.getClass().getField("a").get(playerConnection);
		      networkManager.getClass().getMethod("sendPacket", new Class[] { getClass("net.minecraft.network.protocol.Packet") }).invoke(networkManager, new Object[] { packet });
		    } catch (Exception e) {
		      e.printStackTrace();
		    } 
		  }
		  
		  public static Object getStaticField(Class<?> c, String name) {
		    Object retorno = null;
		    Field f = null;
		    try {
		      f = c.getDeclaredField(name);
		    } catch (NoSuchFieldException e) {
		      e.printStackTrace();
		    } catch (SecurityException e) {
		      e.printStackTrace();
		    } 
		    if (f == null)
		      throw new IllegalArgumentException("Error while getting the field '" + name + "'"); 
		    f.setAccessible(true);
		    try {
		      Field modifiersField = Field.class.getDeclaredField("modifiers");
		      modifiersField.setAccessible(true);
		      modifiersField.setInt(f, modifiersField.getInt(f) - 16);
		    } catch (SecurityException e) {
		      System.out.println("A security manager may be preventing you from setting this field.");
		      e.printStackTrace();
		    } catch (IllegalAccessException|IllegalArgumentException|NoSuchFieldException e) {
		      e.printStackTrace();
		    } 
		    try {
		      retorno = f.get(null);
		    } catch (IllegalArgumentException|IllegalAccessException e) {
		      e.printStackTrace();
		    } 
		    return retorno;
		  }
		  
		  public static Object getFieldValue(Object obj, String name) {
		    try {
		      Field field = obj.getClass().getDeclaredField(name);
		      field.setAccessible(true);
		      return field.get(obj);
		    } catch (Exception exception) {
		      return null;
		    } 
		  }
		  
		  public static void setFieldValue(Object obj, String name, Object value) {
		    try {
		      Field field = obj.getClass().getDeclaredField(name);
		      field.setAccessible(true);
		      field.set(obj, value);
		    } catch (Exception exception) {}
		  }
		  
		  public static void setStaticField(Class<?> c, String name, Object set) {
		    Field f = null;
		    try {
		      f = c.getDeclaredField(name);
		    } catch (NoSuchFieldException e) {
		      e.printStackTrace();
		    } catch (SecurityException e) {
		      e.printStackTrace();
		    } 
		    if (f == null)
		      throw new IllegalArgumentException("Error while getting the field '" + name + "'"); 
		    f.setAccessible(true);
		    try {
		      Field modifiersField = Field.class.getDeclaredField("modifiers");
		      modifiersField.setAccessible(true);
		      modifiersField.setInt(f, modifiersField.getInt(f) - 16);
		    } catch (SecurityException e) {
		      System.out.println("A security manager may be preventing you from setting this field.");
		      e.printStackTrace();
		    } catch (IllegalAccessException|IllegalArgumentException|NoSuchFieldException e) {
		      e.printStackTrace();
		    } 
		    try {
		      f.set(null, set);
		    } catch (IllegalArgumentException|IllegalAccessException e) {
		      e.printStackTrace();
		    } 
		  }
		  
		  @SuppressWarnings({ "rawtypes", "unchecked" })
		public static Enum<?> getEnum(String enumFullName) {
		    String[] x = enumFullName.split("\\.(?=[^\\.]+$)");
		    if (x.length == 2) {
		      String enumClassName = x[0];
		      String enumName = x[1];
		      Class<Enum> cl = (Class)getClass(enumClassName);
		      return Enum.valueOf((Class)cl, enumName);
		    } 
		    return null;
		  }
		  
		  public static Class<?> getClass(String name) {
		    try {
		      return Class.forName(name);
		    } catch (ClassNotFoundException e) {
		      e.printStackTrace();
		      return null;
		    } 
		  }
		}