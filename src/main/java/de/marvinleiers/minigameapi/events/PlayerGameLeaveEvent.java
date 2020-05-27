package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.entity.Player;

public class PlayerGameLeaveEvent extends GameEvent
{
    public PlayerGameLeaveEvent(Player who, Game game)
    {
        super(who, game);
    }
}
