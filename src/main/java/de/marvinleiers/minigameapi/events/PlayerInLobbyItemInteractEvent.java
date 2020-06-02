package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class PlayerInLobbyItemInteractEvent extends PlayerGameEvent
{
    private final ItemStack item;
    private final Action action;

    public PlayerInLobbyItemInteractEvent(Player who, Game game, ItemStack item, Action action)
    {
        super(who, game);

        this.item = item;
        this.action = action;
    }

    public ItemStack getItem()
    {
        return item;
    }

    public Action getAction()
    {
        return action;
    }
}
