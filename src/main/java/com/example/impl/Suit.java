package com.example.impl;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Suit Enum.
 *
 * @author Samraj Timmapuram
 * @since 1.0.0
 */
public enum Suit {
    SPADES('S'),
    HEARTS('H'),
    CLUBS('C'),
    DIAMONDS('D'),
    ;

    Suit(char symbol) {
        this.symbol = symbol;
    }

    static Suit forSymbol(char symbol) {
        return checkNotNull(FOR_SYMBOL[symbol], symbol);
    }

    private static final Suit[] FOR_SYMBOL = new Suit[128]; // ASCII sized

    static {
        for (final Suit s : Suit.values()) FOR_SYMBOL[s.symbol] = s;
    }

    private final char symbol;

    public char getSymbol() {
        return symbol;
    }
}
