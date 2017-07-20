package com.chaoticcognitions.aenigma.models.plugboards;

import java.security.InvalidParameterException;

/**
 * Created by Dan on 2015-07-28.
 */
public class Plugboard {
    private static final char NULLCHAR = '\0';
    private int numPairs = 0;
    private char[] plugSettings = new char[26];

    public void addPlugSettings(char char1, char char2) {
        if (char1 < 'A' || char1 > 'Z' || char2 < 'A' || char2 > 'Z' || char1 == char2)
            throw new InvalidParameterException("Invalid characters for plugboard pair.");
        else if (plugSettings[char1 - 'A'] != NULLCHAR || plugSettings[char2 - 'A'] != NULLCHAR)
            // At least one of the two characters are paired already.
            return;

        plugSettings[char1 - 'A'] = char2;
        plugSettings[char2 - 'A'] = char1;
        numPairs++;
    }

    public char encode(char inputChar) {
        return (plugSettings[inputChar - 'A'] == NULLCHAR ? inputChar :
                plugSettings[inputChar - 'A']);
    }
}
