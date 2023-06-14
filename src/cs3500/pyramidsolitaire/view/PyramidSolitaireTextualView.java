package cs3500.pyramidsolitaire.view;

import java.io.IOException;

import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Contains all methods needed to render a text-based image of the game.
 */
public class PyramidSolitaireTextualView implements PyramidSolitaireView {
  private final PyramidSolitaireModel<?> model;
  private Appendable ap;

  /**
   * Default constructor that takes in a model.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model) {
    this.model = model;
  }

  /**
   * Constructor that takes in a model and an appendable.
   */
  public PyramidSolitaireTextualView(PyramidSolitaireModel<?> model, Appendable ap) {
    this.model = model;
    this.ap = ap;
  }

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   *
   * @throws IOException if the rendering fails for some reason
   */
  @Override
  public void render() throws IOException {
    this.ap.append(new PyramidSolitaireTextualView(model).toString());
  }

  /**
   * Produces a text-based visualization of the game.
   *
   * @return the String representing the state of the game
   */
  @Override
  public String toString() {
    if (model.getNumDraw() == -1) {
      return "";
    } else if (model.getScore() == 0) {
      return "You win!";
    } else if (model.isGameOver()) {
      return "Game over. Score: " + model.getScore();
    } else {
      StringBuilder output = new StringBuilder();
      for (int i = 0; i < model.getNumRows(); i++) {
        output = new StringBuilder(padLeft(output.toString(), i));
        for (int j = 0; j < model.getRowWidth(i); j++) {
          if (j > 0) {
            if (model.getCardAt(i, j) == null) {
              output.append("  ");
              if (model.getCardAt(i, j - 1) == null) {
                output.append("  ");
              } else if (model.getCardAt(i, j - 1).toString().length() > 2) {
                output.append(" ");
              } else {
                output.append("  ");
              }
            } else {
              if (model.getCardAt(i, j - 1) == null) {
                output.append("  ").append(model.getCardAt(i, j).toString());
              } else if (model.getCardAt(i, j - 1).toString().length() > 2) {
                output.append(" ").append(model.getCardAt(i, j).toString());
              } else {
                output.append("  ").append(model.getCardAt(i, j).toString());
              }
            }
          } else {
            if (model.getCardAt(i, j) == null) {
              output.append("  ");
            } else {
              output.append(model.getCardAt(i, j).toString());
            }
          }
        }
        output = new StringBuilder(output.toString().stripTrailing()).append("\n");
      }
      return output + drawToString();
    }
  }

  /**
   * Produces a text-based representation of the draw cards.
   *
   * @return the String containing the cards in the draw pile
   */
  private String drawToString() {
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < model.getNumDraw(); i++) {
      if (i < model.getNumDraw() - 1) {
        if (model.getDrawCards().get(i) == null) {
          output.append("   ");
        } else {
          output.append(model.getDrawCards().get(i).toString()).append(", ");
        }
      } else {
        if (model.getDrawCards().get(i) == null) {
          output.append("  ");
        } else {
          output.append(model.getDrawCards().get(i).toString());
        }
      }
    }
    return "Draw: " + output.toString().stripTrailing();
  }

  /**
   * Pads each row in the pyramid a specified amount to the right.
   *
   * @param s the string being padded
   * @param i the amount of spaces to add
   * @return the padded String
   */
  private String padLeft(String s, int i) {
    String maxSpacing = "                                               ";
    return s + maxSpacing.substring(2, -2 * i + (model.getNumRows()) * 2);
  }
}