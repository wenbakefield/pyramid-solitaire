package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardSuits;
import cs3500.pyramidsolitaire.model.hw02.CardValues;

/**
 * Represents a TriPeaks implementation of Pyramid Solitaire.
 * The rules of this implementation are unchanged, but the model now builds a pyramid with
 * three peaks, and each of the three pyramids intersect at half the total height of the pyramid.
 */
public class TriPeaksPyramidSolitaire extends AbstractPyramidSolitaireModel {

  /**
   * The default constructor used for TriPeaks pyramid solitaire.
   */
  TriPeaksPyramidSolitaire() {
    this.isGameStarted = false;
  }

  /**
   * Initiates the game of TriPeaks Solitaire.
   *
   * @param deck          the deck to be dealt
   * @param shouldShuffle if {@code false}, use the order as given by {@code deck}, otherwise
   *                      shuffle the cards
   * @param numRows       number of rows in the pyramid
   * @param numDraw       number of open piles
   * @throws IllegalArgumentException on an illegal deck, pyramid size, or draw size
   */
  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw) {
    this.isGameStarted = true;
    if (numRows > 8 || numRows < 1) {
      throw new IllegalArgumentException("Number of rows is illegal!");
    }
    if (numDraw > 103 || numDraw < 0) {
      throw new IllegalArgumentException("Number of draws is illegal!");
    }
    if (deck == null) {
      throw new IllegalArgumentException("Empty deck is illegal!");
    }
    validDeck(deck);
    if (shouldShuffle) {
      shuffleDeck(deck);
    }
    this.buildPyramid(deck, numRows, numDraw);
  }

  /**
   * Return a valid and complete deck of cards for a game of Tripeaks Pyramid Solitaire.
   * There is no restriction imposed on the ordering of these cards in the deck.
   * The validity of the deck is determined by the rules of the game.
   * The deck contains two decks worth of cards.
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    ArrayList<Card> deck = new ArrayList<>();
    int numDecks = 2;
    while (numDecks > 0) {
      for (CardSuits suit : CardSuits.values()) {
        for (CardValues value : CardValues.values()) {
          deck.add(0, new Card(value, suit));
        }
      }
      numDecks--;
    }
    return deck;
  }

  /**
   * Builds a pyramid for a game of TriPeaks Pyramid Solitaire.
   *
   * @param deck    the deck to be used in the pyramid
   * @param numRows number of rows in the pyramid
   * @param numDraw number of draw cards available at a time
   */
  @Override
  protected void buildPyramid(List<Card> deck, int numRows, int numDraw) {
    int numCards;
    int initialNumCards;
    if (numRows % 2 == 0) {
      numCards = numRows * 2;
      initialNumCards = numRows;
    } else {
      numCards = (numRows * 2) + 1;
      initialNumCards = numRows - 1;
    }
    this.pyramid = new Card[numRows][numCards];
    int deckIndex = 0;
    for (int i = 0; i < numRows; i++) {
      int numToFill = i + 1;
      for (int j = 0; j <= i + initialNumCards; j++) {
        if (i > (numRows / 2) - 2) {
          pyramid[i][j] = deck.get(deckIndex);
          deckIndex++;
        } else if (numToFill > 0) {
          pyramid[i][j] = deck.get(deckIndex);
          deckIndex++;
          numToFill--;
        } else {
          pyramid[i][j] = null;
          if ((j + 1) % (numRows / 2) == 0) {
            numToFill = i + 1;
          }
        }
      }
    }
    this.buildStock(deck, deckIndex);
    this.buildDraw(numDraw);
  }

  /**
   * Returns the number of cards dealt into the given row in a game of TriPeaks Solitaire.
   *
   * @param row the desired row (zero-indexed)
   * @return the width of that row
   * @throws IllegalArgumentException if the row is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public int getRowWidth(int row) throws IllegalStateException, IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else if (row > getNumRows()) {
      throw new IllegalArgumentException("Row is invalid!");
    } else {
      return pyramid.length + row + 1;
    }
  }
}
