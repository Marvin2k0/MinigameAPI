package de.marvinleiers.minigameapi;

import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.plugin.java.JavaPlugin;

public class MinigameMain extends JavaPlugin
{
    public static void disable()
    {
        for (Game game : MinigameAPI.getGames())
            game.reset(false);
    }
}
