package nl.hackModule.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordUtil {

    public static String scramble(String word) {
        List<Character> chars = word.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(chars);
        return chars.stream().map(String::valueOf).collect(Collectors.joining());
    }
}