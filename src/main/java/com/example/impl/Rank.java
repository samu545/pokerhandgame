package com.example.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Rank Enum.
 *
 * @author Samraj Timmapuram
 * @since 1.0.0
 */
public enum Rank {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
    TEN(10, 'T'), JACK(11, 'J'), QUEEN(12, 'Q'), KING(13, 'K'), ACE(14, 'A');

    private final int value;
    private final char symbol;

    Rank(int value) {
        this(value, (char) ('0' + value));
        checkArgument(value < 10);
    }

    Rank(int rankVal, char symbolVal) {
        value = rankVal;
        symbol = symbolVal;
    }

    static Rank forSymbol(char symbol) {
        return checkNotNull(FOR_SYMBOL[symbol], symbol);
    }

    Card of(Suit suit) {
        return new Card(this, suit);
    }

    private static final Rank[] FOR_SYMBOL = new Rank[128]; // ASCII sized

    static {
        for (final Rank r : Rank.values()) FOR_SYMBOL[r.symbol] = r;
    }


    public int getValue() {
        return value;
    }

    public char getSymbol() {
        return symbol;
    }

}
