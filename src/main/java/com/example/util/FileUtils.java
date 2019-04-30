package com.example.util;

import com.example.impl.Hand;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class.
 *
 * @author Samraj Timmapuram
 */
public class FileUtils {

    public static List<Hand> getHandsFromFile() {
        // sample hand file
        final String name = "Poker.txt";
        /* use this to try all 1999 combinations
        final String name = "PokerFull.txt";
        */
        URL uri = Thread.currentThread().getContextClassLoader().getResource(name);
        // reading file and converting to List of hands
        try {
            return Files.readLines(new File(uri.toURI()), Charsets.UTF_8, new LineProcessor<List<Hand>>() {
                private final List<Hand> hands = new ArrayList<>();

                @Override
                public boolean processLine(String line) {
                    Hand hand = Hand.forString(line);
                    if (hand != null) {
                        hands.add(hand);
                    }
                    return true;
                }

                @Override
                public List<Hand> getResult() {
                    return hands;
                }
            });
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}
