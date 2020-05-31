package de.marvinleiers.minigameapi.events;

import de.marvinleiers.minigameapi.game.Game;

public class GameStartEvent extends GameEvent
{
    public GameStartEvent(Game game)
    {
        super(game);
    }
}
