package de.marvinleiers.minigameapi;

import de.marvinleiers.minigameapi.game.Game;
import de.marvinleiers.minigameapi.game.Playable;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class MinigameAPI extends JavaPlugin implements Listener
{
    private static final HashMap<String, Game> games = Game.games;

    public static Game createGame(String name)
    {
        Game game;

        if (games.get(name) != null)
            game = games.get(name);
        else
            game = new Playable(name);

        return game;
    }
}
