package cs3500.pyramidsolitaire.controller;

import java.io.IOException;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import java.util.Scanner;

/**
 * Contains all the methods needed to control the game via textual input.
 * The user can input commands to a Readable, including:
 * - rm1 row card (to remove)
 * - rm2 row1 card1 row2 card2 (to pairwise remove)
 * - rmwd drawIndex row card (to remove with draw)
 * - dd drawIndex (to discard draw)
 * - q/Q (to quit)
 * The state of the model is then appended to an Appendable.
 */
public class PyramidSolitaireTextualController implements PyramidSolitaireController {
  private Readable in;
  private Appendable out;

  /**
   * Default constructor that takes in a Readable input and Appendable output.
   *
   * @param rd the Readable input
   * @param ap the Appendable output
   * @throws IllegalArgumentException if input or output is null
   */
  public PyramidSolitaireTextualController(Readable rd, Appendable ap)
          throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("IOs cannot be null!");
    } else {
      this.in = rd;
      this.out = ap;
    }
  }

  /**
   * The primary method for beginning and playing a game.
   *
   * @param model   The game of solitaire to be played
   * @param deck    The deck of cards to be used
   * @param shuffle Whether to shuffle the deck or not
   * @param numRows How many rows should be in the pyramid
   * @param numDraw How many draw cards should be visible
   */
  @Override
  public <K> void playGame(PyramidSolitaireModel<K> model, List<K> deck,
                           boolean shuffle, int numRows, int numDraw) {
    if (model == null) {
      throw new IllegalArgumentException("Model must not be null!");
    }
    try {
      model.startGame(deck, shuffle, numRows, numDraw);
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException(e.getMessage());
    }

    Scanner scan = new Scanner(this.in);
    String cmd;
    int arg1;
    int arg2;
    int arg3;
    int arg4;
    while (scan.hasNext()) {
      cmd = scan.next();
      switch (cmd) {
        case "rm1":
          arg1 = Integer.parseInt(getNextValid(scan));
          arg2 = Integer.parseInt(getNextValid(scan));
          if (arg1 == -1 || arg2 == -1) {
            appendQuit(model);
            return;
          }
          try {
            model.remove(arg1 - 1, arg2 - 1);
            appendModel(model);
            if (model.isGameOver()) {
              scan.close();
              return;
            }
            this.appendString("Score: " + model.getScore() + "\n");
          } catch (IllegalArgumentException e) {
            this.appendString("Invalid move. Play Again. " + e.getMessage() + "\n");
          }
          break;
        case "rm2":
          arg1 = Integer.parseInt(getNextValid(scan));
          arg2 = Integer.parseInt(getNextValid(scan));
          arg3 = Integer.parseInt(getNextValid(scan));
          arg4 = Integer.parseInt(getNextValid(scan));
          if (arg1 == -1 || arg2 == -1 || arg3 == -1 || arg4 == -1) {
            appendQuit(model);
            return;
          }
          try {
            model.remove(arg1 - 1, arg2 - 1, arg3 - 1, arg4 - 1);
            appendModel(model);
            if (model.isGameOver()) {
              scan.close();
              return;
            }
            this.appendString("Score: " + model.getScore() + "\n");
          } catch (IllegalArgumentException e) {
            this.appendString("Invalid move. Play Again. " + e.getMessage() + "\n");
          }
          break;
        case "rmwd":
          arg1 = Integer.parseInt(getNextValid(scan));
          arg2 = Integer.parseInt(getNextValid(scan));
          arg3 = Integer.parseInt(getNextValid(scan));
          if (arg1 == -1 || arg2 == -1 || arg3 == -1) {
            appendQuit(model);
            return;
          }
          try {
            model.removeUsingDraw(arg1 - 1, arg2 - 1, arg3 - 1);
            appendModel(model);
            if (model.isGameOver()) {
              scan.close();
              return;
            }
            this.appendString("Score: " + model.getScore() + "\n");
          } catch (IllegalArgumentException e) {
            this.appendString("Invalid move. Play Again. " + e.getMessage() + "\n");
          }
          break;
        case "dd":
          arg1 = Integer.parseInt(getNextValid(scan));
          if (arg1 == -1) {
            appendQuit(model);
            return;
          }
          try {
            model.discardDraw(arg1 - 1);
            appendModel(model);
            if (model.isGameOver()) {
              scan.close();
              return;
            }
            this.appendString("Score: " + model.getScore() + "\n");
          } catch (IllegalArgumentException e) {
            this.appendString("Invalid move. Play Again. " + e.getMessage() + "\n");
          }
          break;
        case "q":
        case "Q":
          this.appendQuit(model);
          return;
        default:
          appendModel(model);
          appendString("Unknown move! Move commands are:\n" +
                  "1. Remove: rm1 row card\n2. Remove Pair: rm2 row1 card1 row2 card2\n" +
                  "3. Remove Using Draw: rmwd draw row card\n4. Discard Draw: dd draw\n" +
                  "5. Quit: q\n");
          break;
      }
    }
    if (this.out.toString().equals("")) {
      throw new IllegalStateException("Controller cannot receive input or transmit output!");
    }
  }

  /**
   * Appends the given model to the output.
   *
   * @param model the model being appended
   */
  private <K> void appendModel(PyramidSolitaireModel<K> model) {
    try {
      new PyramidSolitaireTextualView(model, this.out).render();
      appendString("\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Cannot append the given model!");
    }
  }

  /**
   * Appends the given string to the output.
   *
   * @param str the string being appended
   */
  private void appendString(String str) {
    try {
      this.out.append(str);
    } catch (IOException ioe) {
      throw new IllegalStateException("Cannot append the given string!");
    }
  }

  /**
   * Appends the quit message to the output.
   *
   * @param model the model being appended
   */
  private <K> void appendQuit(PyramidSolitaireModel<K> model) {
    this.appendString("Game quit!\n"
            + "State of game when quit:\n");
    this.appendModel(model);
    this.appendString("Score: " + model.getScore() + "\n");
  }

  /**
   * Retrieves the first integer or q/Q from the given scanner. If the token is q/Q, a -1 is
   * returned. All other tokens are ignored.
   *
   * @param scan the scanner to be scanned through
   * @return the next valid token from the scanner
   */
  private String getNextValid(Scanner scan) {
    String output = "";
    while (scan.hasNext() && output.equals("")) {
      if (scan.hasNextInt()) {
        output = scan.next();
        if (output.equals("-1")) {
          output = "-2";
        }
      } else if (scan.hasNext("q") || scan.hasNext("Q")) {
        output = "-1";
      } else {
        this.appendString("Unexpected input. Please try again.\n");
        scan.next();
      }
    }
    return output;
  }
}