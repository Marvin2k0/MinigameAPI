package de.marvinleiers.minigameapi.game;

import org.bukkit.entity.Player;

import java.util.HashMap;

public interface Game
{
    /**
     *
     * HashMap that holds all existing games.
     */
    HashMap<String, Game> games = new HashMap<>();

    /**
     *
     * @param player The player to join the game.
     */
    void join(Player player);

    /**
     *
     * @param player The player to leave the game.
     */
    void leave(Player player);

    /**
     *
     * @return Returns game's name.
     */
    String getName();

    /**
     *
     * Starts the game. Gets called when 'min-players' is reached.
     */
    void start();

    /**
     *
     * Stops the game.
     */
    void stop();

    /**
     *
     * Resets the game, removes added entities and undoes block changes.
     */
    void reset();
}
