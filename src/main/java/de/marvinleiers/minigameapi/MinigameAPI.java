package de.marvinleiers.minigameapi;

import de.marvinleiers.minigameapi.events.GameEventHandler;
import de.marvinleiers.minigameapi.game.Game;
import de.marvinleiers.minigameapi.game.GamePlayer;
import de.marvinleiers.minigameapi.game.Playable;
import de.marvinleiers.minigameapi.listeners.SignListener;
import de.marvinleiers.minigameapi.utils.Text;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class MinigameAPI
{
    private static final HashMap<String, Game> games = Game.games;
    public static HashMap<Player, GamePlayer> gameplayers = new HashMap<>();
    private static MinigameAPI api;
    public static JavaPlugin plugin;

    private final FileConfiguration config;

    private MinigameAPI(JavaPlugin plugin)
    {
        Text.setUp(plugin);

        MinigameAPI.plugin = plugin;
        this.config = plugin.getConfig();

        plugin.getServer().getPluginManager().registerEvents(new SignListener(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new GameEventHandler(), plugin);

        loadGames();
    }

    private void loadGames()
    {
        if (!config.isSet("games"))
            return;

        Map<String, Object> section = Objects.requireNonNull(config.getConfigurationSection("games")).getValues(false);

        for (Map.Entry<String, Object> entry : section.entrySet())
        {
            Game game = createGame(entry.getKey());

            System.out.println("[Game] Loaded game " + game.getName());
        }
    }

    public void setLobbyItems(Inventory items)
    {
        Playable.lobbyItems = items.getContents();
    }

    public static boolean inGame(Player player)
    {
        return gameplayers.containsKey(player);
    }

    public static MinigameAPI getAPI(JavaPlugin plugin)
    {
        if (api == null)
            api = new MinigameAPI(plugin);

        return api;
    }

    public static Collection<Game> getGames()
    {
        return games.values();
    }

    public static Game getGameFromName(String name)
    {
        return games.get(name);
    }

    public static boolean exists(String name)
    {
        return games.containsKey(name);
    }

    public Game createGame(String name)
    {
        Game game;

        if (games.get(name) != null)
            game = games.get(name);
        else
            game = new Playable(name);

        return game;
    }
}
