

#Coding Exercise

Write a Java class for a 'Hand' in the game of Poker.

Card 'Values' are given as a list:
VALUES = ['2','3','4','5','6','7','8','9','T', 'J', 'Q', 'K', 'A']

Where T = 10, J = 'Jack', Q = 'Queen', K = 'King' and A is 'Ace'

The possible 'Suits' are
SUITS = ['C', 'D', 'H', 'S']
Where C = 'Clubs', D = 'Diamonds', H = 'Hearts' and S = 'Spades'.

A Hand contains 5 cards and should be immutable and initializable from a string like so:

three_of_a_kind = Hand.fromString('4D 4C 4H 7H 8D')
one_pair = Hand.fromString('4D 3D 3D 7H AD')

make sure the Hand is only initializable like that.
A Hand should be able to determine its 'Value' according to the rules of poker.

Possible values are given as (in order, from low to high value):
VALUES = [
HIGH_CARD, 
ONE_PAIR, 
TWO_PAIR, 
THREE_OF_A_KIND, 
STRAIGHT,
FLUSH, 
FULL_HOUSE, 
FOUR_OF_A_KIND, 
STRAIGHT_FLUSH, 
ROYAL_FLUSH
]

A Hand should implement a toString() method that gives a string representation of a

Hand like so:
<hand [TS, JS, QS, KS, AS], 'Royal Flush'>
<hand [5S, 6S, 7S, 8S, 9S], 'Straight Flush'>
<hand [7S, TC, TH, TS, TD], 'Four of a Kind'>
<hand [5H, 5C, QD, QC, QS], 'Full House'>
<hand [2D, 3D, 7D, QD, AD], 'Flush'>
<hand [4D, 5D, 6D, 7H, 8D], 'Straight'> 
etc, etc


You should be able to sort a list of Hands and compare Hands in such a way that it is
consistent with the values in VALUES.
