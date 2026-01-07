package nl.hackModule.data;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum CodeData {

    HACKEN,
    WACHTWOORD,
    INTERNET,
    FIREWALL,
    COMMAND,
    DATABASE;

    private static final List<CodeData> VALUES = List.of(values());

    public static String getRandomWord() {
        return VALUES.get(ThreadLocalRandom.current().nextInt(VALUES.size())).name().toLowerCase();
    }
}