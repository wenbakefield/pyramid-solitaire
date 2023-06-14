import java.util.List;
import java.util.Objects;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Methods for the mock input testing of {@link PyramidSolitaireTextualController}.
 */
public class ConfirmInputsPyramidSolitaireModel implements PyramidSolitaireModel {
  private final StringBuilder log;

  ConfirmInputsPyramidSolitaireModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public List getDeck() {
    return null;
  }

  @Override
  public void startGame(List deck, boolean shuffle, int numRows, int numDraw) {
    log.append(String.format(
            "shuffle = %b, numRows = %d, numDraw = %d\n", shuffle, numRows, numDraw));
  }

  @Override
  public void remove(int row1, int card1, int row2, int card2) throws IllegalStateException {
    log.append(String.format(
            "row1 = %d, card1 = %d, row2 = %d, card2 = %d\n", row1, card1, row2, card2));
  }

  @Override
  public void remove(int row, int card) throws IllegalStateException {
    log.append(String.format("row = %d, card = %d\n", row, card));
  }

  @Override
  public void removeUsingDraw(int drawIndex, int row, int card) throws IllegalStateException {
    log.append(String.format("drawIndex = %d, row = %d, card = %d\n", drawIndex, row, card));
  }

  @Override
  public void discardDraw(int drawIndex) throws IllegalStateException {
    log.append(String.format("drawIndex = %d\n", drawIndex));
  }

  @Override
  public int getNumRows() {
    return 0;
  }

  @Override
  public int getNumDraw() {
    return 0;
  }

  @Override
  public int getRowWidth(int row) {
    log.append(String.format("row = %d\n", row));
    return 0;
  }

  @Override
  public boolean isGameOver() throws IllegalStateException {
    return false;
  }

  @Override
  public int getScore() throws IllegalStateException {
    return 0;
  }

  @Override
  public Object getCardAt(int row, int card) throws IllegalStateException {
    log.append(String.format("row = %d, card = %d\n", row, card));
    return null;
  }

  @Override
  public List getDrawCards() throws IllegalStateException {
    return null;
  }
}