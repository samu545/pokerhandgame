package com.example.impl;

import com.google.common.collect.ImmutableList;
import junit.framework.TestCase;

import static com.example.impl.Rank.EIGHT;
import static com.example.impl.Rank.NINE;
import static com.example.impl.Rank.TEN;
import static com.example.impl.Suit.CLUBS;
import static com.example.impl.Suit.SPADES;
import static com.example.impl.Suit.DIAMONDS;

/**
 * Unit testing of PokerHandGame.
 * @author Samraj Timmapuram
 */
public class PokerTest extends TestCase {

    public void testHandForString() {
        final ImmutableList<Card> cards =
                ImmutableList.of(TEN.of(SPADES),
                        TEN.of(DIAMONDS),
                        NINE.of(SPADES),
                        EIGHT.of(SPADES),
                        EIGHT.of(CLUBS));
        final Hand hand = new Hand(cards);
        assertEquals(hand, Hand.forString("TS TD 9S 8S 8C"));
    }

    public void testHandValue() {
        checkHandValue("2S JD 9H 4C QC", HandValue.HIGH_CARD);
        checkHandValue("2C 2D 2H 3C 3D", HandValue.FULL_HOUSE);
        checkHandValue("2C 3D 4H 5C 6D", HandValue.STRAIGHT);
        checkHandValue("2C 2D 4H 5C 6D", HandValue.PAIR);
        checkHandValue("2C 3C 4C 5C 6C", HandValue.STRAIGHT_FLUSH);
        checkHandValue("TS JS QS KS AS", HandValue.ROYAL_FLUSH);
    }

    public void testThreeOfAKind() {
        final Hand h = Hand.forString("2S 2D 2H 4C QC");
        assertFalse(HandValue.TWO_PAIRS.apply(h));
        assertTrue(HandValue.THREE_OF_A_KIND.apply(h));
        assertSame(HandValue.THREE_OF_A_KIND, h.toHandValue());
    }

    public void testTwoPairs() {
        final Hand h = Hand.forString("TS TD 9S 8S 8C");
        assertTrue(HandValue.TWO_PAIRS.apply(h));
        assertFalse(HandValue.THREE_OF_A_KIND.apply(h));
        assertSame(HandValue.TWO_PAIRS, h.toHandValue());
    }

    private void checkHandValue(String cards, HandValue expected) {
        assertEquals(expected, toHandValue(cards));
    }

    private HandValue toHandValue(String s) {
        return Hand.forString(s).toHandValue();
    }
}