package cs3500.pyramidsolitaire.model.hw04;

import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;

/**
 * Factory class for creating different types of models of the game.
 */
public class PyramidSolitaireCreator {
  /**
   * Represents the types of games for pyramid solitaire.
   */
  public enum GameType {
    BASIC, RELAXED, TRIPEAKS
  }

  /**
   * Creates a model of a specified game type.
   * @param game the type of game
   * @return an instance of a PyramidSolitaireModel
   * @throws IllegalArgumentException if an illegal game type is given
   */
  public static PyramidSolitaireModel create(GameType game) throws IllegalArgumentException {
    switch (game) {
      case BASIC:
        return new BasicPyramidSolitaire();
      case RELAXED:
        return new RelaxedPyramidSolitaire();
      case TRIPEAKS:
        return new TriPeaksPyramidSolitaire();
      default:
        throw new IllegalArgumentException("Illegal game type!");
    }
  }
}