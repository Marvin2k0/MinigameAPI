package de.marvinleiers.minigameapi.game;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Set;

public interface Game
{
    /**
     *
     * HashMap that holds all existing games.
     */
    HashMap<String, Game> games = new HashMap<>();

    /**
     *
     * @return All living players in game.
     */
    Set<GamePlayer> getGamePlayers();

    /**
     *
     * @return All players in game.
     */
    Set<Player> getPlayers();

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
     * @return Returns true if the game has started.
     */
    boolean hasStarted();

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

    /**
     *
     * Returns the items a player receives once joined.
     *
     * @return The items
     */
    ItemStack[] getLobbyItems();

    /**
     * Send message to all players inside this game.
     *
     * @param msg The message to send.
     */
    void sendMessage(String msg);
}
