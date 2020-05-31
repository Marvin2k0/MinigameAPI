package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerGameEvent extends PlayerEvent
{
    private static final HandlerList handlers = new HandlerList();

    protected final Game game;

    public PlayerGameEvent(Player who, Game game)
    {
        super(who);

        this.game = game;
    }

    public Game getGame()
    {
        return game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
