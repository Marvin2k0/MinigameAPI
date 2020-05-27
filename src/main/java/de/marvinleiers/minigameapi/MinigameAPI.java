package de.marvinleiers.minigameapi;

import de.marvinleiers.minigameapi.game.Game;
import de.marvinleiers.minigameapi.game.Playable;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class MinigameAPI extends JavaPlugin
{
    private HashMap<String, Game> games;

    @Override
    public void onEnable()
    {
        games = Game.games;
    }

    @Override
    public void onDisable()
    {

    }

    public Game createGame(String name)
    {
        Game game;

        if (games.get(name) != null)
            game = games.get(name);
        else
            game = new Playable(name);

        return game;
    }
}
