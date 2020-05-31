package de.marvinleiers.minigameapi.game;

import de.marvinleiers.minigameapi.MinigameAPI;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GamePlayer
{
    private final Game game;
    private final Player player;
    private final Location location;
    private final ItemStack[] inventoryContents;
    private final GameMode gameMode;

    public GamePlayer(Game game, Player player)
    {
        this.game = game;
        this.player = player;
        this.location = player.getLocation();
        this.inventoryContents = player.getInventory().getContents();
        this.gameMode = player.getGameMode();

        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);

        MinigameAPI.gameplayers.put(player, this);
    }

    public void leave()
    {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setContents(inventoryContents);
        player.teleport(location);
        player.setGameMode(gameMode);
    }

    public void sendMessage(String msg)
    {
        player.sendMessage(msg);
    }

    public String getName()
    {
        return player.getName();
    }

    public Location getLocation()
    {
        return location;
    }

    public ItemStack[] getInventoryContents()
    {
        return inventoryContents;
    }

    public GameMode getGameMode()
    {
        return gameMode;
    }

    public Game getGame()
    {
        return game;
    }

    public Player getPlayer()
    {
        return player;
    }
}
