package com.example.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.apache.commons.text.WordUtils;

import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Hand class.
 *
 * @author Samraj Timmapuram
 * @since 1.0.0
 */
public class Hand implements Comparable<Hand> {
    private static final int CARDS_IN_HAND = 5;
    private static final int TO_STRING_LENGTH = 14;
    private static final ImmutableList<HandValue> HAND_VALUES = ImmutableList.copyOf(HandValue.values()).reverse();

    private final ImmutableSortedSet<Card> cards;
    private final ImmutableMultiset<Integer> rankHistogram;
    private final ImmutableMultiset<Integer> suitHistogram;
    private final boolean isStraight;


    Hand(Iterable<Card> cards) {
        assert Iterables.size(cards) == CARDS_IN_HAND;
        this.cards = ImmutableSortedSet.copyOf(cards);
        checkArgument(this.cards.size() == 5, cards);
        final Multiset<Rank> ranks = EnumMultiset.create(Rank.class);
        final Multiset<Suit> suits = EnumMultiset.create(Suit.class);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (final Card c : this.cards) {
            final Rank rank = c.getRank();
            min = Math.min(min, rank.getValue());
            max = Math.max(max, rank.getValue());
            ranks.add(rank);
            suits.add(c.getSuit());
        }
        rankHistogram = histogram(ranks);
        suitHistogram = histogram(suits);
        isStraight = max - min == CARDS_IN_HAND - 1 && rankHistogram.equals(ImmutableMultiset.of(1, 1, 1, 1, 1));
    }

    /**
     * Converting String input cards of the form "vs vs vs vs vs" to {@link Hand}
     * for example {@code "5H 5C 6S 7S KD"}
     */
    public static Hand forString(String cards) {
        checkArgument(cards.length() == TO_STRING_LENGTH);
        final List<Card> list = Lists.newArrayList();
        for (final String s : Splitter.on(' ').splitToList(cards)) list.add(Card.forString(s));
        return new Hand(list);
    }

    /**
     * Returns a positive number if {@code other} is stronger than {@code this},
     * a negative number if it's weaker,
     * and 0 if they're equally strong.
     */
    public int compareTo(Hand other) {
        final HandValue myHandValue = this.toHandValue();
        final HandValue otherHandValue = other.toHandValue();
        int result = otherHandValue.compareTo(myHandValue);
        if (result != 0) return result;

        assert myHandValue == otherHandValue;
        final List<Card> myReorderedCards = myHandValue.reorderedCards(this);
        final List<Card> otherReorderedCards = otherHandValue.reorderedCards(other);
        assert myReorderedCards.size() == otherReorderedCards.size();

        // Lexicographical comparison of card ranks.
        for (int i = myReorderedCards.size(); i-- > 0; ) {
            final Rank myRank = myReorderedCards.get(i).getRank();
            final Rank otherRank = otherReorderedCards.get(i).getRank();
            result = Integer.compare(otherRank.getValue(), myRank.getValue());
            if (result != 0) return result;
        }
        return 0;
    }

    /**
     * toString representation of resultant hand.
     *
     * @return hand String
     */
    @Override
    public String toString() {
        final char[] delimiters = {' ', '_'};
        String handValCamelCase = WordUtils.capitalizeFully(this.toHandValue().toString().toLowerCase().replace("_", " "), delimiters);
        return "<hand " + cards + ", '" + handValCamelCase + "'>";
    }

    private static <E> ImmutableMultiset<Integer> histogram(Multiset<E> multiset) {
        final List<Integer> result = Lists.newArrayList();
        for (final Multiset.Entry<E> e : multiset.entrySet()) result.add(e.getCount());
        return ImmutableMultiset.copyOf(result);
    }

    HandValue toHandValue() {
        for (final HandValue v : HAND_VALUES) {
            if (v.apply(this)) return v;
        }
        throw new RuntimeException("Invalid Hand Values: " + cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hand)) return false;
        Hand hand = (Hand) o;
        return isStraight() == hand.isStraight() &&
                Objects.equals(getCards(), hand.getCards()) &&
                Objects.equals(getRankHistogram(), hand.getRankHistogram()) &&
                Objects.equals(getSuitHistogram(), hand.getSuitHistogram());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCards(), getRankHistogram(), getSuitHistogram(), isStraight());
    }

    public ImmutableSortedSet<Card> getCards() {
        return cards;
    }

    public ImmutableMultiset<Integer> getRankHistogram() {
        return rankHistogram;
    }

    public ImmutableMultiset<Integer> getSuitHistogram() {
        return suitHistogram;
    }

    public boolean isStraight() {
        return isStraight;
    }
}
