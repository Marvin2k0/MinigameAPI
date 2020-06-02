package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.MinigameAPI;
import de.marvinleiers.minigameapi.game.Game;
import de.marvinleiers.minigameapi.game.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameEventHandler implements Listener
{
    @EventHandler
    public void onPlayerInLobbyItemInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        if (!event.hasItem())
            return;

        if (MinigameAPI.inGame(player))
        {
            GamePlayer gp = MinigameAPI.gameplayers.get(player);
            Game game = gp.getGame();

            event.setCancelled(true);

            if (game.hasStarted())
            {
                Bukkit.getPluginManager().callEvent(new PlayerInGameItemInteractEvent(player, game, event.getItem(), event.getAction()));
            }
            else
            {
                Bukkit.getPluginManager().callEvent(new PlayerInLobbyItemInteractEvent(player, game, event.getItem(), event.getAction()));
            }
        }
    }

    @EventHandler
    public void onPlayerInLobbyInventoryClickEvent(InventoryClickEvent event)
    {
        if (!(event.getWhoClicked() instanceof Player))
            return;

        Player player = (Player) event.getWhoClicked();

        if (!MinigameAPI.inGame(player))
            return;

        if ((event.getCurrentItem() == null) || (event.getInventory() == null))
            return;

        GamePlayer gp = MinigameAPI.gameplayers.get(player);
        Game game = gp.getGame();

        if (game.hasStarted())
        {
            PlayerInGameInventoryClickEvent invEvent = new PlayerInGameInventoryClickEvent(player, game, event.getClickedInventory(), event.getCurrentItem());

            Bukkit.getPluginManager().callEvent(invEvent);
            event.setCancelled(invEvent.isCancelled());
        }
        else
        {
            PlayerInLobbyInventoryClickEvent invEvent = new PlayerInLobbyInventoryClickEvent(player, game, event.getClickedInventory(), event.getCurrentItem());

            Bukkit.getPluginManager().callEvent(invEvent);
            event.setCancelled(invEvent.isCancelled());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        if (!MinigameAPI.inGame(player))
            return;

        Game game = MinigameAPI.gameplayers.get(player).getGame();

        Bukkit.getPluginManager().callEvent(new PlayerGameLeaveEvent(player, game));
    }
}
