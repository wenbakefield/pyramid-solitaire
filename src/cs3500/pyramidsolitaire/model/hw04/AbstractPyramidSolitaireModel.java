package cs3500.pyramidsolitaire.model.hw04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.CardSuits;
import cs3500.pyramidsolitaire.model.hw02.CardValues;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Represents the abstract implementation of the pyramid solitaire model.
 */
public abstract class AbstractPyramidSolitaireModel implements PyramidSolitaireModel<Card> {
  Card[][] pyramid;
  private ArrayList<Card> stock;
  private ArrayList<Card> draw;
  protected boolean isGameStarted;

  /**
   * Initiates the game.
   *
   * @param deck          the deck to be dealt
   * @param shouldShuffle if {@code false}, use the order as given by {@code deck}, otherwise
   *                      shuffle the cards
   * @param numRows       number of rows in the pyramid
   * @param numDraw       number of open piles
   * @throws IllegalArgumentException on an illegal deck
   */
  @Override
  public void startGame(List<Card> deck, boolean shouldShuffle, int numRows, int numDraw)
          throws IllegalArgumentException {
    this.isGameStarted = true;
    if (numRows > 9 || numRows < 1) {
      throw new IllegalArgumentException("Number of rows is illegal!");
    }
    if (numDraw > 51 || numDraw < 0) {
      throw new IllegalArgumentException("Number of draws is illegal!");
    }
    if (deck == null) {
      throw new IllegalArgumentException("Empty deck is illegal!");
    }
    this.validDeck(deck);
    if (shouldShuffle) {
      this.shuffleDeck(deck);
    }
    this.buildPyramid(deck, numRows, numDraw);
  }

  /**
   * Return a valid and complete deck of cards for a game of Pyramid Solitaire. There is no
   * restriction imposed on the ordering of these cards in the deck. The validity of the deck is
   * determined by the rules of the game.
   *
   * @return the deck of cards as a list
   */
  @Override
  public List<Card> getDeck() {
    ArrayList<Card> deck = new ArrayList<>();
    for (CardSuits suit : CardSuits.values()) {
      for (CardValues value : CardValues.values()) {
        deck.add(0, new Card(value, suit));
      }
    }
    return deck;
  }

  /**
   * Throws an exception if the deck does not contain 52 unique cards.
   *
   * @param deck the deck of cards to be verified
   * @throws IllegalArgumentException on an invalid deck
   */
  void validDeck(List<Card> deck) {
    Set<Card> deckHashSet = new HashSet<>(deck);
    if (deckHashSet.size() != 52) {
      throw new IllegalArgumentException("The deck is invalid!");
    }
  }

  /**
   * Shuffles a deck of cards.
   *
   * @param deck the deck to be shuffled
   */
  void shuffleDeck(List<Card> deck) {
    Collections.shuffle(deck);
  }

  /**
   * Builds a pyramid for a game of pyramid solitaire.
   *
   * @param deck    the deck to be used in the pyramid
   * @param numRows number of rows in the pyramid
   * @param numDraw number of draw cards available at a time
   */
  void buildPyramid(List<Card> deck, int numRows, int numDraw) {
    this.pyramid = new Card[numRows][numRows];
    int deckIndex = 0;
    for (int i = 0; i < pyramid.length; i++) {
      for (int j = 0; j < i + 1; j++) {
        pyramid[i][j] = deck.get(deckIndex);
        deckIndex++;
      }
    }
    this.buildStock(deck, deckIndex);
    this.buildDraw(numDraw);
  }

  /**
   * Builds the card stock pile for a game of pyramid solitaire.
   *
   * @param deck      the deck to be used for the stock
   * @param deckIndex the index in the deck to start dealing from
   */
  void buildStock(List<Card> deck, int deckIndex) {
    this.stock = new ArrayList<>();
    for (int i = 0; i < deck.size() - deckIndex; i++) {
      stock.add(i, deck.get(i + deckIndex));
    }
  }

  /**
   * Builds the draw pile for a game of pyramid solitaire.
   *
   * @param numDraw the number of cards to be dealt to the draw pile
   */
  void buildDraw(int numDraw) {
    this.draw = new ArrayList<>();
    try {
      for (int i = 0; i < numDraw; i++) {
        draw.add(i, stock.remove(0));
      }
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Number of draw cards is illegal!");
    }
  }

  /**
   * Execute a pairwise move on the pyramid, using the specified card positions.
   *
   * @param row1  row of first card position, numbered from 0 from the top of the pyramid
   * @param card1 card of first card position, numbered from 0 from left
   * @param row2  row of second card position
   * @param card2 card of second card position
   * @throws IllegalStateException if the game has not yet been started
   */
  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException,
          IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else if ((isInPyramid(row1, card1))
            && (isInPyramid(row2, card2))
            && (getValue(pyramid[row2][card2])
            + getValue(pyramid[row1][card1])) == 13
            && (isNotCovered(row1, card1))
            && (isNotCovered(row2, card2))) {
      pyramid[row1][card1] = null;
      pyramid[row2][card2] = null;
    } else {
      throw new IllegalArgumentException("The move is invalid!");
    }
  }

  /**
   * Execute a single-card move on the pyramid, using the specified card position.
   *
   * @param row  row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void remove(int row, int card) throws IllegalStateException, IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else if ((isInPyramid(row, card))
            && getValue(pyramid[row][card]) == 13
            && (isNotCovered(row, card))) {
      pyramid[row][card] = null;
    } else {
      throw new IllegalArgumentException("The move is invalid!");
    }
  }

  /**
   * Execute a pairwise move, using the specified card from the draw pile and the specified card
   * from the pyramid.
   *
   * @param row  row of the desired card position, numbered from 0 from the top of the pyramid
   * @param card card of the desired card position, numbered from 0 from left
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException,
          IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else if (drawIndex < getNumDraw()
            && drawIndex >= 0
            && draw.get(drawIndex) != null
            && (isInPyramid(row, card))
            && (getValue(draw.get(drawIndex)) + getValue(pyramid[row][card]) == 13)
            && (isNotCovered(row, card))) {
      if (stock.isEmpty()) {
        draw.remove(drawIndex);
        draw.add(drawIndex, null);
      } else {
        draw.remove(drawIndex);
        draw.add(drawIndex, stock.remove(0));
      }
      pyramid[row][card] = null;
    } else {
      throw new IllegalArgumentException("The move is invalid!");
    }
  }

  /**
   * Signifies if the specified card is not covered by any cards.
   *
   * @param row  the row of the card
   * @param card the column of the card
   * @return a boolean signifying if the card is covered
   */
  boolean isNotCovered(int row, int card) {
    return (row == pyramid.length - 1
            || pyramid[row + 1][card] == null && pyramid[row + 1][card + 1] == null);
  }

  /**
   * Signifies if the specified card is in the pyramid.
   * @param row the row of the card
   * @param card the column of the card
   * @return a boolean signifying if the card is in the pyramid
   */
  boolean isInPyramid(int row, int card) {
    return row < getNumRows() && row >= 0 && card < getRowWidth(row) && card >= 0
            && pyramid[row][card] != null;
  }

  /**
   * Discards an individual card from the draw pile.
   *
   * @param drawIndex the card to be discarded
   * @throws IllegalArgumentException if the index is invalid or no card is present there.
   * @throws IllegalStateException    if the game has not yet been started
   */
  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException, IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else if (drawIndex < getNumDraw() && draw.get(drawIndex) != null) {
      if (stock.isEmpty()) {
        draw.remove(drawIndex);
        draw.add(drawIndex, null);
      } else {
        draw.remove(drawIndex);
        draw.add(drawIndex, stock.remove(0));
      }
    } else {
      throw new IllegalArgumentException("Draw index is invalid!");
    }
  }

  /**
   * Returns the number of rows originally in the pyramid, or -1 if the game hasn't been started.
   *
   * @return the height of the pyramid, or -1
   */
  @Override
  public int getNumRows() {
    if (isGameStarted && pyramid != null) {
      int numRows;
      numRows = pyramid.length;
      return numRows;
    } else {
      return -1;
    }
  }

  /**
   * Returns the maximum number of visible cards in the draw pile, or -1 if the game hasn't been
   * started.
   *
   * @return the number of visible cards in the draw pile, or -1
   */
  @Override
  public int getNumDraw() {
    if (isGameStarted && draw != null) {
      int numDraw;
      numDraw = draw.size();
      return numDraw;
    } else {
      return -1;
    }
  }

  /**
   * Returns the number of cards dealt into the given row.
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
      return row + 1;
    }
  }

  /**
   * Signal if the game is over or not.
   *
   * @return true if game is over, false otherwise
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public boolean isGameOver() throws IllegalStateException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else {
      return stock.isEmpty() && isDrawNull(this.draw) && !isMoveLeft() || getScore() == 0;
    }
  }

  /**
   * Signal if the draw pile is all null.
   *
   * @param draw the draw pile to check
   * @return true if the draw pile is all null
   */
  private boolean isDrawNull(Iterable<Card> draw) {
    for (Card c : draw) {
      if (c != null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Signal if the pyramid contains a valid move.
   *
   * @return true if there is a valid move left
   */
  private boolean isMoveLeft() {
    ArrayList<Integer> moveValues = new ArrayList<>();
    for (int i = 0; i < getNumRows(); i++) {
      for (int j = 0; j < getRowWidth(i); j++) {
        if (pyramid[i][j] != null && (i == getNumRows() - 1
                || (pyramid[i + 1][j] == null
                && pyramid[i + 1][j + 1] == null))) {
          moveValues.add(getValue(pyramid[i][j]));
        }
      }
    }
    return moveValues.contains(13)
            || (moveValues.contains(12) && moveValues.contains(1))
            || (moveValues.contains(11) && moveValues.contains(2))
            || (moveValues.contains(10) && moveValues.contains(3))
            || (moveValues.contains(9) && moveValues.contains(4))
            || (moveValues.contains(8) && moveValues.contains(5))
            || (moveValues.contains(7) && moveValues.contains(6));
  }

  /**
   * Return the current score, which is the sum of the values of the cards remaining in the
   * pyramid.
   *
   * @return the score
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public int getScore() throws IllegalStateException {
    if (isGameStarted) {
      int score = 0;
      for (int i = 0; i < getNumRows(); i++) {
        for (int j = 0; j < getRowWidth(i); j++) {
          if (pyramid[i][j] != null) {
            score = score + getValue(pyramid[i][j]);
          }
        }
      }
      return score;
    } else {
      throw new IllegalStateException("Game has not started!");
    }
  }

  /**
   * Converts a card into its numerical value.
   *
   * @return the card's value as an integer
   */
  int getValue(Card card) {
    String cardText = card.toString();
    cardText = cardText.substring(0, cardText.length() - 1);
    switch (cardText) {
      case "A":
        return 1;
      case "2":
        return 2;
      case "3":
        return 3;
      case "4":
        return 4;
      case "5":
        return 5;
      case "6":
        return 6;
      case "7":
        return 7;
      case "8":
        return 8;
      case "9":
        return 9;
      case "10":
        return 10;
      case "J":
        return 11;
      case "Q":
        return 12;
      case "K":
        return 13;
      default:
        return 0;
    }
  }

  /**
   * Returns the card at the specified coordinates.
   *
   * @param row  row of the desired card
   * @param card column of the desired card
   * @return the card at the given position, or <code>null</code> if no card is there
   * @throws IllegalArgumentException if the coordinates are invalid
   * @throws IllegalStateException    if the game hasn't been started yet
   */
  @Override
  public Card getCardAt(int row, int card) throws IllegalStateException, IllegalArgumentException {
    if (!isGameStarted) {
      throw new IllegalStateException("Game has not started!");
    } else if (row < getNumRows() && row >= 0 && card < getRowWidth(row) && card >= 0) {
      Card cardCopy;
      cardCopy = pyramid[row][card];
      return cardCopy;
    } else {
      throw new IllegalArgumentException("Coordinates are invalid!");
    }
  }

  /**
   * Returns the currently available draw cards. There should be at most {@link
   * PyramidSolitaireModel#getNumDraw} cards (the number specified when the game started) -- there
   * may be fewer, if cards have been removed.
   *
   * @return the ordered list of available draw cards
   * @throws IllegalStateException if the game hasn't been started yet
   */
  @Override
  public List<Card> getDrawCards() throws IllegalStateException {
    if (isGameStarted) {
      List<Card> drawCopy;
      drawCopy = draw;
      return drawCopy;
    } else {
      throw new IllegalStateException("Game has not started!");
    }
  }
}
