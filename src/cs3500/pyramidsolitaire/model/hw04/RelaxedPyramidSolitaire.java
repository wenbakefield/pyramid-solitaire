package cs3500.pyramidsolitaire.model.hw04;

/**
 * Represents a Relaxed implementation of Pyramid Solitaire.
 * Cards can be removed pairwise if one card is not covered, the other is half covered by
 * the first card, and both cards sum to a value of 13.
 */
public class RelaxedPyramidSolitaire extends AbstractPyramidSolitaireModel {

  /**
   * The default constructor used for relaxed pyramid solitaire.
   */
  RelaxedPyramidSolitaire() {
    this.isGameStarted = false;
  }

  /**
   * Execute a pairwise move on the pyramid, using the specified card positions.
   * In this relaxed implementation, cards can be removed if one card is not covered,
   * the other is half covered by the first card, and both cards sum to a value of 13.
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
            && (getValue(pyramid[row2][card2]) + getValue(pyramid[row1][card1])) == 13
            && ((isNotCovered(row1, card1) && isNotCovered(row2, card2))
            || (isHalfCoveredBy(row1, card1, row2, card2)
            || (isHalfCoveredBy(row2, card2, row1, card1))))) {
      pyramid[row1][card1] = null;
      pyramid[row2][card2] = null;
    } else {
      throw new IllegalArgumentException("The move is invalid!");
    }
  }

  /**
   * Signifies if a card at a given location is only covered by another card.
   *
   * @param targetRow  row of the covered card
   * @param targetCard column of the covered card
   * @param coverRow   row of the card that is covering the other
   * @param coverCard  column of the card that is covering the other
   * @return a boolean signifying if the target is half covered by the other card
   */
  private boolean isHalfCoveredBy(int targetRow, int targetCard, int coverRow, int coverCard) {
    return isNotCovered(coverRow, coverCard)
            && ((pyramid[targetRow + 1][targetCard] != null
            && pyramid[targetRow + 1][targetCard].equals(pyramid[coverRow][coverCard])
            && pyramid[targetRow + 1][targetCard + 1] == null)
            || (pyramid[targetRow + 1][targetCard + 1] != null
            && pyramid[targetRow + 1][targetCard + 1].equals(pyramid[coverRow][coverCard])
            && pyramid[targetRow + 1][targetCard] == null));
  }
}