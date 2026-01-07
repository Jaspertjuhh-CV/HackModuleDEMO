package nl.hackModule.managers;

import nl.hackModule.data.CodeData;
import nl.hackModule.util.WordUtil;
import org.bukkit.entity.Player;
import java.util.*;

public class CodeMinigame {

    private static final Map<UUID, String> activeCodes = new HashMap<>();

    public static void start(Player player) {
        String word = CodeData.getRandomWord();
        String scrambled = WordUtil.scramble(word);

        activeCodes.put(player.getUniqueId(), word.toLowerCase());

        player.sendMessage("§eOntcijfer de code:");
        player.sendMessage("§6" + scrambled);
        player.sendMessage("§7Typ het juiste woord in de chat.");
    }

    public static boolean hasCode(Player player)
    {
        return activeCodes.containsKey(player.getUniqueId());
    }

    public static boolean check(Player player, String input) {
        String correct = activeCodes.get(player.getUniqueId());
        if (correct.equalsIgnoreCase(input)) {
            activeCodes.remove(player.getUniqueId());
            return true;
        }
        return false;
    }
}