package de.marvinleiers.minigameapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Text
{
    static FileConfiguration config;
    static Plugin plugin;

    public static String get(String path)
    {
        return path.equalsIgnoreCase("prefix") ? get(path, false) : get(path, true);
    }

    public static String get(String path, boolean prefix)
    {
        return ChatColor.translateAlternateColorCodes('&', prefix ? config.getString("prefix") + " " + config.getString(path) : config.getString(path));
    }

    public static void setUp(Plugin plugin)
    {
        Text.plugin = plugin;
        Text.config = plugin.getConfig();

        config.options().copyDefaults(true);
        config.addDefault("prefix" , "&7[&9Game&7]");
        config.addDefault("noplayer", "&cOnly players can do that!");
        config.addDefault("countdown", "&7Game starts in &9%seconds% &7seconds");
        config.addDefault("minplayers", 1);
        config.addDefault("maxplayers", 8);

        saveConfig();
    }

    private static void saveConfig()
    {
        plugin.saveConfig();
    }
}
