package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInGameInventoryClickEvent extends PlayerGameEvent implements Cancellable
{
    private Inventory inventory;
    private ItemStack clickedItem;
    private boolean cancelled;

    public PlayerInGameInventoryClickEvent(Player who, Game game, Inventory inventory, ItemStack clickedItem)
    {
        super(who, game);

        this.inventory = inventory;
        this.clickedItem = clickedItem;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public ItemStack getClickedItem()
    {
        return clickedItem;
    }

    @Override
    public boolean isCancelled()
    {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel)
    {
        this.cancelled = cancel;
    }
}
