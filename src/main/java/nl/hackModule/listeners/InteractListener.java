package nl.hackModule.listeners;

import nl.hackModule.managers.HackManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    private final HackManager manager;

    public InteractListener(HackManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;
        if (e.getItem() == null) return;

        if (e.getItem().getType() == Material.GOLDEN_HOE &&
                e.getClickedBlock().getType() == Material.GOLD_BLOCK) {

            Player player = e.getPlayer();
            Location loc = e.getClickedBlock().getLocation();

            if (manager.isHacking(player)) return;

            if (manager.isOnCooldown(loc)) {
                player.sendMessage("§cDit systeem is onlangs al gehacked!"); return;
            }

            manager.startHack(player ,loc);
        }
    }
}