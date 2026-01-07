package nl.hackModule;

import nl.hackModule.listeners.ChatListener;
import nl.hackModule.listeners.InteractListener;
import nl.hackModule.listeners.MoveListener;
import nl.hackModule.managers.HackManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HackModule extends JavaPlugin {

    private static HackModule instance;
    private HackManager hackManager;

    @Override
    public void onEnable() {
        instance = this;
        hackManager = new HackManager();

        Bukkit.getPluginManager().registerEvents(new InteractListener(hackManager), this);
        Bukkit.getPluginManager().registerEvents(new MoveListener(hackManager), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(hackManager), this);
    }

    public static HackModule getInstance() { return instance; }
}