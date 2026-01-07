package nl.hackModule.managers;

import nl.hackModule.HackModule;
import nl.hackModule.util.TitleUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

public class HackSession {

    private final Player player;
    private final HackManager manager;

    private int elapsedSeconds = 0;
    private boolean paused = false;
    private boolean ended = false;

    private BukkitTask progressTask;

    public HackSession(Player player, HackManager manager) {
        this.player = player;
        this.manager = manager;
    }

    public void start() {

        resetBar();
        TitleUtil.sendHackTitle(player);

        progressTask = Bukkit.getScheduler().runTaskTimer(
                HackModule.getInstance(),
                () -> {

                    if (!player.isOnline()) { stop(); return; }
                    if (paused) { return; }

                    elapsedSeconds++;

                    double progress = elapsedSeconds / 30.0;

                    player.setExp((float) progress);
                    player.setLevel((int) (progress * 100));

                    if (elapsedSeconds == 10 || elapsedSeconds == 20) { pause();}
                    if (elapsedSeconds >= 30) { finish(); }
                },
                20L,
                20L
        );
    }

    public void pause() {
        paused = true;
        TitleUtil.clearTitle(player);
        CodeMinigame.start(player);
    }

    public void resume() {
        paused = false;
        TitleUtil.sendHackTitle(player);
    }

    public void stop() {
        if (ended) return;
        ended = true;

        cancelProgressTask();
        resetBar();
        TitleUtil.clearTitle(player);
        manager.stopHack(player);

        player.getInventory().removeItem(new ItemStack(Material.GOLDEN_HOE, 1));
    }

    private void finish() {
        stop();
        player.sendMessage("§aHack succesvol!");
        player.getInventory().addItem(new ItemStack(Material.DIAMOND)); //REWARD (huidig)
    }

    //UTIL

    private void cancelProgressTask() {
        if (progressTask != null) {
            progressTask.cancel();
            progressTask = null;
        }
    }

    private void resetBar() {
        player.setExp(0f);
        player.setLevel(0);
    }
}