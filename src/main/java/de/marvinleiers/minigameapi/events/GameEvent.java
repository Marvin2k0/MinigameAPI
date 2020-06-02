package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.MinigameAPI;
import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    private final Game game;

    public GameEvent(Game game)
    {
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
