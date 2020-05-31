package de.marvinleiers.minigameapi.game;

import de.marvinleiers.minigameapi.MinigameAPI;
import de.marvinleiers.minigameapi.events.GameStartEvent;
import de.marvinleiers.minigameapi.events.PlayerGameJoinEvent;
import de.marvinleiers.minigameapi.events.PlayerGameLeaveEvent;
import de.marvinleiers.minigameapi.utils.CountdownTimer;
import de.marvinleiers.minigameapi.utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class Playable implements Game
{
    private static final int MIN_PLAYERS = Integer.parseInt(Text.get("minplayers", false));
    private static final int MAX_PLAYERS = Integer.parseInt(Text.get("maxplayers", false));

    public static ItemStack[] lobbyItems;
    public static ItemStack[] gameItems;

    private final Set<GamePlayer> gameplayers;
    private final Set<Player> players;
    private final String name;
    private boolean hasStarted;

    public Playable(String name)
    {
        this.gameplayers = new HashSet<>();
        this.players = new HashSet<>();
        this.name = name;
        this.hasStarted = false;

        games.put(name, this);
    }

    public void join(Player player)
    {
        if (players.contains(player) || MinigameAPI.gameplayers.containsKey(player))
            return;

        GamePlayer gp = new GamePlayer(this, player);
        players.add(player);

        player.getInventory().setContents(getLobbyItems());
        player.updateInventory();

        Bukkit.getPluginManager().callEvent(new PlayerGameJoinEvent(player, this));

        if (getPlayers().size() >= MIN_PLAYERS && !hasStarted())
            start();
    }

    public void leave(Player player)
    {
        players.remove(player);
        GamePlayer gp = MinigameAPI.gameplayers.get(player);
        gp.leave();

        MinigameAPI.gameplayers.remove(player);
        Bukkit.getPluginManager().callEvent(new PlayerGameLeaveEvent(player, this));
    }

    public void start()
    {
        new CountdownTimer(MinigameAPI.plugin, 30,
                () -> {},
                this::startGame,
                (t) -> printSeconds(t.getSecondsLeft())).scheduleTimer();

        hasStarted = true;
    }

    private void startGame()
    {
        //TODO: abort event , abort reason
        Bukkit.getPluginManager().callEvent(new GameStartEvent(this));
    }

    public ItemStack[] getLobbyItems()
    {
        return lobbyItems.clone();
    }

    private void printSeconds(int seconds)
    {
        if (seconds <= 5)
            sendMessage(Text.get("countdown").replace("%seconds%", seconds + ""));
        else if (seconds % 5 == 0)
            sendMessage(Text.get("countdown").replace("%seconds%", seconds + ""));
    }

    public void stop()
    {
        this.hasStarted = false;

        reset();
    }

    public void reset()
    {

    }

    public void sendMessage(String msg)
    {
        for (Player player : players)
            player.sendMessage(msg);
    }

    public boolean hasStarted()
    {
        return this.hasStarted;
    }

    public Set<GamePlayer> getGamePlayers()
    {
        return gameplayers;
    }

    public Set<Player> getPlayers()
    {
        return players;
    }

    public String getName()
    {
        return name;
    }
}
