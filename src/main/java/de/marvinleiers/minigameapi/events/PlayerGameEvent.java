package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.MinigameAPI;
import de.marvinleiers.minigameapi.game.Game;
import de.marvinleiers.minigameapi.game.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerGameEvent extends PlayerEvent
{
    private static final HandlerList handlers = new HandlerList();

    protected final Game game;
    private final GamePlayer gp;

    public PlayerGameEvent(Player who, Game game)
    {
        super(who);

        this.game = game;
        this.gp = MinigameAPI.gameplayers.get(player);
    }

    public GamePlayer getGamePlayer()
    {
        return gp;
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
