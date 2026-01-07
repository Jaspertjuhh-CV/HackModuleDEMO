package nl.hackModule.listeners;

import nl.hackModule.HackModule;
import nl.hackModule.managers.CodeMinigame;
import nl.hackModule.managers.HackManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final HackManager manager;

    public ChatListener(HackManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if (!CodeMinigame.hasCode(p)) return;

        e.setCancelled(true);

        Bukkit.getScheduler().runTask(HackModule.getInstance(), () -> {
            if (CodeMinigame.check(p, e.getMessage())) {
                p.sendMessage("§2Code correct! Hacken wordt vervolgd...");
                manager.getSession(p).resume();
            } else {
                p.sendMessage("§4Foute code! Hacken mislukt...");
                manager.getSession(p).stop();
            }
        });
    }
}