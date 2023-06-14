package cs3500.pyramidsolitaire;

import java.io.InputStreamReader;
import java.util.Objects;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;

/**
 * Contains the main method for Pyramid Solitaire.
 */
public final class PyramidSolitaire {

  /**
   * Initiates the game from the command line.
   *
   * @param args commands to customize the game type and number of rows and draw cards
   */
  public static void main(String[] args) {
    PyramidSolitaireModel model = null;
    int numRows = 7;
    int numDraw = 3;
    if (args.length == 0) {
      model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.BASIC);
    } else {
      switch (args[0]) {
      case "basic":
        model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.BASIC);
        break;
      case "relaxed":
        model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.RELAXED);
        break;
      case "tripeaks":
        model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.TRIPEAKS);
        break;
      default:
        throw new IllegalArgumentException("Invalid game type!");
      }
    }
    if (args.length == 3) {
      numRows = Integer.parseInt(args[1]);
      numDraw = Integer.parseInt(args[2]);
    }
    Readable rd = new InputStreamReader(System.in);
    Appendable ap = System.out;
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(rd, ap);

    try {
      controller.playGame(
              model, Objects.requireNonNull(model).getDeck(), true, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }
}