package nl.hackModule.util;

import org.bukkit.entity.Player;

public final class TitleUtil {

    public static void sendHackTitle(Player player) {
        sendTitle(
                player,
                "§eAan het hacken...",
                "§7Wacht op een doorbraak.",
                5,
                300,
                5
        );
    }

    public static void sendTitle(Player player,
                                 String title,
                                 String subtitle,
                                 int fadeIn,
                                 int stay,
                                 int fadeOut) {

        if (player == null) return;
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }

    public static void clearTitle(Player player) {
        if (player == null) return;
        player.resetTitle();
    }
}