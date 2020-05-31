package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.game.Game;
import org.bukkit.entity.Player;

public class PlayerGameJoinEvent extends PlayerGameEvent
{
    public PlayerGameJoinEvent(Player who, Game game)
    {
        super(who, game);
    }
}
