package nl.hackModule.listeners;

import nl.hackModule.managers.HackManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {

    private final HackManager manager;

    public MoveListener(HackManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getTo() == null) return;

        Player p = e.getPlayer();
        if (!manager.isHacking(p)) return;

        if (e.getFrom().distance(e.getTo()) > 0.1) {
            p.sendMessage("§4Je kan niet bewegen tijdens het hacken!");
            manager.stopHack(p);
        }
    }
}