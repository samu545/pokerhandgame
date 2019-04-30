package com.example.impl;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Card class.
 *
 * @author Samraj Timmapuram
 * @since 1.0.0
 */
public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    static Card forString(String s) {
        checkArgument(s.length() == 2);
        return new Card(Rank.forSymbol(s.charAt(0)), Suit.forSymbol(s.charAt(1)));
    }

    public int compareTo(Card o) {
        int result = rank.compareTo(o.rank);
        if (result == 0) result = suit.compareTo(o.suit);
        return result;
    }


    @Override
    public String toString() {
        return rank.getSymbol() + "" + suit.getSymbol();
    }

    public Card(Rank rankVal, Suit suitVal) {
        this.rank = rankVal;
        this.suit = suitVal;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

}
