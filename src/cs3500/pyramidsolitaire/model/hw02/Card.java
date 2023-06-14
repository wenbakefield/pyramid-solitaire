package cs3500.pyramidsolitaire.model.hw02;

import java.util.Objects;

/**
 * Represents a playing card.
 */
public class Card {
  private final CardValues value;
  private final CardSuits suit;

  public Card(CardValues value, CardSuits suit) {
    this.value = value;
    this.suit = suit;
  }

  /**
   * Produces a text-based representation of a playing card.
   *
   * @return the String containing the playing card data
   */
  @Override
  public String toString() {
    String value;
    if (this.value == CardValues.ACE) {
      value = "A";
    } else if (this.value == CardValues.TWO) {
      value = "2";
    } else if (this.value == CardValues.THREE) {
      value = "3";
    } else if (this.value == CardValues.FOUR) {
      value = "4";
    } else if (this.value == CardValues.FIVE) {
      value = "5";
    } else if (this.value == CardValues.SIX) {
      value = "6";
    } else if (this.value == CardValues.SEVEN) {
      value = "7";
    } else if (this.value == CardValues.EIGHT) {
      value = "8";
    } else if (this.value == CardValues.NINE) {
      value = "9";
    } else if (this.value == CardValues.TEN) {
      value = "10";
    } else if (this.value == CardValues.JACK) {
      value = "J";
    } else if (this.value == CardValues.QUEEN) {
      value = "Q";
    } else if (this.value == CardValues.KING) {
      value = "K";
    } else {
      value = "";
    }
    String suit;
    if (this.suit == CardSuits.DIAMONDS) {
      suit = "♦";
    } else if (this.suit == CardSuits.CLUBS) {
      suit = "♣";
    } else if (this.suit == CardSuits.HEARTS) {
      suit = "♥";
    } else if (this.suit == CardSuits.SPADES) {
      suit = "♠";
    } else {
      suit = "";
    }
    return value + suit;
  }

  /**
   * Checks if two cards are the same.
   *
   * @param o the object for this card to be compared to
   * @return true if the cards are the same
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Card)) {
      return false;
    }
    Card c = (Card) o;
    return value.equals(c.value) && suit.equals(c.suit);
  }

  /**
   * Generates a hash for a card.
   *
   * @return the hash of the card
   */
  @Override
  public int hashCode() {
    return Objects.hash(value, suit);
  }
}