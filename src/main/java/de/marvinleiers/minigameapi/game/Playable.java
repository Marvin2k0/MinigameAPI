package de.marvinleiers.minigameapi.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Playable implements Game
{
    private final ArrayList<Player> players;
    private final String name;

    public Playable(String name)
    {
        this.players = new ArrayList<>();
        this.name = name;

        games.put(name, this);
    }

    public final void join(Player player)
    {
        if (players.contains(player))
            return;

        players.add(player);
    }

    public final void leave(Player player)
    {
        players.remove(player);
    }

    public final void start()
    {
        
    }

    public final void stop()
    {

    }

    public final void reset()
    {

    }

    public final ArrayList<Player> getPlayers()
    {
        return players;
    }

    public final String getName()
    {
        return name;
    }
}
