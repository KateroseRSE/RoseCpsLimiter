package me.Katerose.NMS.versions.API;

import org.bukkit.Bukkit;

public class VersionUtils {
  public enum Version {
    v1_5(10500),
    v1_6(10600),
    v1_7(10700),
    v1_8_8(10808),
    v1_8_9(10809),
    v1_9(10900),
    v1_9_1(10901),
    v1_9_2(10902),
    v1_9_3(10903),
    v1_9_4(10904),
    v1_9_x(10910),
    v1_10(11000),
    v1_10_1(11001),
    v1_10_2(11002),
    v1_10_x(11010),
    v1_11(11100),
    v1_11_1(11101),
    v1_11_2(11102),
    v1_11_x(11110),
    v1_12(11200),
    v1_12_1(11201),
    v1_12_2(11202),
    v1_12_x(11210),
    v1_13(11300),
    v1_13_1(11301),
    v1_13_2(11302),
    v1_13_x(11310),
    v1_14(11400),
    v1_14_1(11401),
    v1_14_2(11402),
    v1_14_3(11403),
    v1_14_4(11404),
    v1_14_x(11410),
    v1_15(11500),
    v1_15_1(11501),
    v1_15_2(11502),
    v1_15_x(11510),
    v1_16(11600),
    v1_16_1(11601),
    v1_16_2(11602),
    v1_16_3(11603),
    v1_16_4(11604),
    v1_16_5(11605),
    v1_16_x(11610),
    v1_17(11700),
    v1_17_1(11701),
    v1_17_x(11710),
    v1_18(11800),
	v1_18_1(11801),
	v1_18_2(11802),
	v1_18_x(11810),
	v1_19(11900),
	v1_19_1(11901),
	v1_19_2(11902),
	v1_19_x(11910),
    vUnsupported(1000000);
    
    private int value;
    
    private boolean contains;
    
    private boolean equals;
    
    Version(int param1Int1) {
      this.value = param1Int1;
      this.contains = Bukkit.getBukkitVersion().split("-")[0].contains(toString());
      this.equals = Bukkit.getBukkitVersion().split("-")[0].equalsIgnoreCase(toString());
    }
    
    private int getValue() {
      return this.value;
    }
    
    public boolean Is() {
      return this.contains;
    }
    
    public boolean IsEquals() {
      return this.equals;
    }
    
    public String toString() {
      return name().replaceAll("_", ".").split("v")[1];
    }
    
    public boolean esMayorr(Version param1Version) {
      return (getValue() > param1Version.getValue());
    }
    
    public boolean esMayorIgual(Version param1Version) {
      return (getValue() >= param1Version.getValue());
    }
    
    public boolean esMenor(Version param1Version) {
      return (getValue() < param1Version.getValue());
    }
    
    public boolean esMenorIgual(Version param1Version) {
      return (getValue() <= param1Version.getValue());
    }
    
    public static Version getVersion() {
      Version version = vUnsupported;
      byte b;
      int i;
      Version[] arrayOfVersion;
      for (i = (arrayOfVersion = values()).length, b = 0; b < i; ) {
        Version version1 = arrayOfVersion[b];
        if (version1.IsEquals()) {
          version = version1;
          break;
        } 
        b++;
      } 
      return version;
    }
  }
  
  public static boolean mc1_7 = Bukkit.getBukkitVersion().split("-")[0].contains("1.7");
  
  public static boolean mc1_8 = Bukkit.getBukkitVersion().split("-")[0].contains("1.8");
  
  public static boolean mc1_9 = Bukkit.getBukkitVersion().split("-")[0].contains("1.9");
  
  public static boolean mc1_10 = Bukkit.getBukkitVersion().split("-")[0].contains("1.10");
  
  public static boolean mc1_11 = Bukkit.getBukkitVersion().split("-")[0].contains("1.11");
  
  public static boolean mc1_12 = Bukkit.getBukkitVersion().split("-")[0].contains("1.12");
  
  public static boolean mc1_13 = Bukkit.getBukkitVersion().split("-")[0].contains("1.13");
  
  public static boolean mc1_13_0 = Bukkit.getBukkitVersion().split("-")[0].equalsIgnoreCase("1.13");
  
  public static boolean mc1_13_1 = Bukkit.getBukkitVersion().split("-")[0].contains("1.13.1");
  
  public static boolean mc1_16_2 = Bukkit.getBukkitVersion().split("-")[0].contains("1.16.2");
  
  public static boolean mc1_16_3 = Bukkit.getBukkitVersion().split("-")[0].contains("1.16.3");
  
  public static boolean mc1_16_4 = Bukkit.getBukkitVersion().split("-")[0].contains("1.16.4");
  
  public static boolean mc1_16_5 = Bukkit.getBukkitVersion().split("-")[0].contains("1.16.5");
  
  public static boolean mc1_17 = Bukkit.getBukkitVersion().split("-")[0].contains("1.17");
  
  public static boolean mc1_17_1 = Bukkit.getBukkitVersion().split("-")[0].contains("1.17.1");
  
  public static boolean mc1_18 = Bukkit.getBukkitVersion().split("-")[0].contains("1.18");
  
  public static boolean mc1_18_1 = Bukkit.getBukkitVersion().split("-")[0].contains("1.18.1");
  
  public static boolean mc1_18_2 = Bukkit.getBukkitVersion().split("-")[0].contains("1.18.2");
  
  public static boolean mc1_19 = Bukkit.getBukkitVersion().split("-")[0].contains("1.19");
  
  public static boolean mc1_19_1 = Bukkit.getBukkitVersion().split("-")[0].contains("1.19.1");
  
  public static boolean mc1_19_2 = Bukkit.getBukkitVersion().split("-")[0].contains("1.19.2");
  
  public static boolean isPre1_13() {
	    if (mc1_8 || mc1_9 || mc1_10 || mc1_11 || mc1_12)
	      return true; 
	    return false;
  }
  public static boolean isNewVersion() {
	    if (mc1_16_2 ||
	    		mc1_16_3 ||
	    		mc1_16_3 ||
	    		mc1_16_4 ||
	    		mc1_16_5 ||
	    		mc1_17 ||
	    		mc1_17_1 ||
	    		mc1_18 ||
	    		mc1_18_1 ||
	    		mc1_18_2 ||
	    		mc1_19 ||
	    		mc1_19_1 ||
	    		mc1_19_2)
	      return true; 
	    return false;
  }
}
