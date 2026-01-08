package nl.hackModule.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import java.util.*;

public class HackManager {

    private final Map<UUID, HackSession> activeHacks = new HashMap<>();
    private final Map<Location, Long> cooldowns = new HashMap<>();

    public void startHack(Player player, Location blockLocation) {
        HackSession session = new HackSession(player, this);
        activeHacks.put(player.getUniqueId(), session);
        setCooldown(blockLocation);
        session.start();
    }

    public void stopHack(Player player) {
        HackSession session = activeHacks.remove(player.getUniqueId());
        if (session != null) {
            session.stop();
        }
    }

    public HackSession getSession(Player player)
    {
        return activeHacks.get(player.getUniqueId());
    }

    public boolean isHacking(Player player) {
        return activeHacks.containsKey(player.getUniqueId());
    }

    //COOLDOWN SYSTEM

    public boolean isOnCooldown(Location loc) {
        Long end = cooldowns.get(loc);
        if (end == null) return false;

        if (System.currentTimeMillis() >= end) {
            cooldowns.remove(loc);
            return false;
        }
        return true;
    }

    public void setCooldown(Location loc) {
        cooldowns.put(loc, System.currentTimeMillis() + 5 * 60 * 1000L); //5m
    }
}