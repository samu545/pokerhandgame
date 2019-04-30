package com.example.impl;

import com.example.util.FileUtils;

import java.util.Collections;
import java.util.List;

/**
 * Poker Play Main class.
 *
 * @author Samraj Timmapuram
 * @since 1.0.0
 */
public class Poker {
    public static void main(String[] args) {
        System.out.println("======================ACTUAL ORDER======================");
        List<Hand> hands = FileUtils.getHandsFromFile();
        hands.stream().forEach(System.out::println);

        Collections.sort(hands);
        System.out.println("===================SORTED HAND ORDER====================");
        hands.stream().forEach(System.out::println);
        System.out.println("========================================================");
    }
}
