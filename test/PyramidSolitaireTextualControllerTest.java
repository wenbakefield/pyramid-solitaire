import org.junit.Before;
import org.junit.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;

/**
 * Tests for the methods of {@link PyramidSolitaireTextualController}.
 */
public class PyramidSolitaireTextualControllerTest {
  private List<Card> deck = new ArrayList<>();
  private final PyramidSolitaireModel testModelA =
          PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.BASIC);

  /**
   * Facilitates testing of the controller through a mock model.
   *
   * @param model        the model to be used in testing
   * @param interactions the input/output commands to be tested against the controller
   */
  private void testRun(BasicPyramidSolitaire model, Interaction... interactions) {
    StringBuilder userInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();
    for (Interaction interaction : interactions) {
      interaction.apply(userInput, expectedOutput);
    }
    StringReader input = new StringReader(userInput.toString());
    StringBuilder actualOutput = new StringBuilder();
    PyramidSolitaireTextualController controller =
            new PyramidSolitaireTextualController(input, actualOutput);
    controller.playGame(model, deck, false, 9, 4);
    assertEquals(expectedOutput.toString(), actualOutput.toString());
  }

  @Before
  public void init() {
    deck = testModelA.getDeck();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullInputConstructor() {
    new PyramidSolitaireTextualController(null, System.out);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullOutputConstructor() {
    new PyramidSolitaireTextualController(new InputStreamReader(System.in), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullIOConstructor() {
    new PyramidSolitaireTextualController(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Readable in = new StringReader("rm1 9 4");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(null, deck, false, 9, 4);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameIllegalRows() {
    Readable in = new StringReader("rm1 3 3");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(testModelA, deck, false, 0, 4);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGameIllegalCards() {
    Readable in = new StringReader("rm1 3 3");
    Appendable out = new StringBuilder();
    PyramidSolitaireTextualController controller = new PyramidSolitaireTextualController(in, out);
    controller.playGame(testModelA, deck, false, 4, 999);
  }

  @Test
  public void testUnknownMove() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Unknown move! Move commands are:\n" +
                    "1. Remove: rm1 row card\n" +
                    "2. Remove Pair: rm2 row1 card1 row2 card2\n" +
                    "3. Remove Using Draw: rmwd draw row card\n" +
                    "4. Discard Draw: dd draw\n" +
                    "5. Quit: q\n"));
  }

  @Test(expected = IllegalStateException.class)
  public void testIOErrorEmptyInput() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction(""),
            new PrintInteraction(""));
  }

  @Test(expected = IllegalStateException.class)
  public void testIOErrorWhitespaceInput() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction(" \n"),
            new PrintInteraction(""));
  }

  @Test
  public void testQuitLowercase() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("q 4 2"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testQuitUppercase() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("Q 4 2"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1QuitNested() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 4 q"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1QuitNestedRowNumberIsNotTheSameAsCardNumber() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 q 3"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM2QuitNested() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm2 ? Q"),
            new PrintInteraction("Unexpected input. Please try again.\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRMWDQuitNested() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rmwd q 3 2 5"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testDDQuitNested() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("dd Q"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1ValidQuit() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 9 4 q"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n"));
  }

  @Test
  public void testRM1InvalidQuit() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 -9 4 q"),
            new PrintInteraction("Invalid move. Play Again. The move is invalid!\n" +
                    "Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1InvalidEarlyQuit() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 -9 q"),
            new PrintInteraction("Game quit!\n" +
                    "State of game when quit:\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1Valid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 9 4"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n"));
  }

  @Test
  public void testRM2Valid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm2 9 1 9 7"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "    2♣  A♣  K♥  Q♥  J♥      9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n"));
  }

  @Test
  public void testRMWDValid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rmwd 4 9 8"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥     8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 3♥\n" +
                    "Score: 327\n"));
  }

  @Test
  public void testDDValid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("dd 4"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 3♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1Junk() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 ? 9 4"),
            new PrintInteraction("Unexpected input. Please try again.\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n"));
  }

  @Test
  public void testRM2Junk() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm2 9\n1 ? 9 hello\n! 7"),
            new PrintInteraction("Unexpected input. Please try again.\n" +
                    "Unexpected input. Please try again.\n" +
                    "Unexpected input. Please try again.\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "    2♣  A♣  K♥  Q♥  J♥      9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n"));
  }

  @Test
  public void testRMWDJunk() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rmwd 4     9  *#&$  ?> \n8"),
            new PrintInteraction("Unexpected input. Please try again.\n" +
                    "Unexpected input. Please try again.\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥     8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 3♥\n" +
                    "Score: 327\n"));
  }

  @Test
  public void testDDJunk() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("dd removeit! \n  4 dd 2"),
            new PrintInteraction("Unexpected input. Please try again.\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 3♥\n" +
                    "Score: 336\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 2♥, 5♥, 3♥\n" +
                    "Score: 336\n"));
  }

  @Test
  public void testRM1Retry() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("4 rm1 ? 9 4"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Unknown move! Move commands are:\n" +
                    "1. Remove: rm1 row card\n" +
                    "2. Remove Pair: rm2 row1 card1 row2 card2\n" +
                    "3. Remove Using Draw: rmwd draw row card\n" +
                    "4. Discard Draw: dd draw\n" +
                    "5. Quit: q\n" +
                    "Unexpected input. Please try again.\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Score: 323\n"));
  }

  @Test
  public void testRMWDRetry() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("4 rmwd ? 3 9 9"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥\n" +
                    "Unknown move! Move commands are:\n" +
                    "1. Remove: rm1 row card\n" +
                    "2. Remove Pair: rm2 row1 card1 row2 card2\n" +
                    "3. Remove Using Draw: rmwd draw row card\n" +
                    "4. Discard Draw: dd draw\n" +
                    "5. Quit: q\n" +
                    "Unexpected input. Please try again.\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 4♥\n" +
                    "Score: 328\n"));
  }

  @Test
  public void testRM1Invalid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 3 4"),
            new PrintInteraction("Invalid move. Play Again. The move is invalid!\n"));
  }

  @Test
  public void testRM1Negative() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm1 -1 -2"),
            new PrintInteraction("Invalid move. Play Again. The move is invalid!\n"));
  }

  @Test
  public void testRM2Invalid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rm2 9 3 9 6"),
            new PrintInteraction("Invalid move. Play Again. The move is invalid!\n"));
  }

  @Test
  public void testRMWDInvalid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rmwd 2 9 5"),
            new PrintInteraction("Invalid move. Play Again. The move is invalid!\n"));
  }

  @Test
  public void testDDInvalid() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("dd 6"),
            new PrintInteraction("Invalid move. Play Again. Draw index is invalid!\n"));
  }

  @Test
  public void testGameOverLose() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rmwd 3 9 9 rmwd 4 9 8 rmwd 3 9 7 rm1 9 4 rm2 9 3 9 5 " +
                    "rm2 9 2 9 6 rm2 8 5 8 6 dd 1 dd 4 dd 2 dd 3 rm2 9 1 8 2 rm2 8 3 8 8 " +
                    "rm2 8 4 8 7 rm1 7 6 rm2 7 7 7 5 rm2 7 4 8 1 rm2 6 5 6 6 rm2 6 4 7 1 rm1 5 4"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 4♥\n" +
                    "Score: 328\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 2♥\n" +
                    "Score: 319\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 309\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 296\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣              J♥\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 283\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 270\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw:    6♥, A♥, 2♥\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw:    6♥, A♥,\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw:       A♥,\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: \n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣      9♣  8♣          5♣  4♣\n" +
                    "Draw: \n" +
                    "Score: 244\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣          8♣          5♣\n" +
                    "Draw: \n" +
                    "Score: 231\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣\n" +
                    "Draw: \n" +
                    "Score: 218\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦      Q♣\n" +
                    "  J♣\n" +
                    "Draw: \n" +
                    "Score: 205\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦\n" +
                    "  J♣\n" +
                    "Draw: \n" +
                    "Score: 192\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦\n" +
                    "Draw: \n" +
                    "Score: 179\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦\n" +
                    "    5♦  4♦  3♦\n" +
                    "Draw: \n" +
                    "Score: 166\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦\n" +
                    "        4♦  3♦\n" +
                    "Draw: \n" +
                    "Score: 153\n" +
                    "Game over. Score: 140\n"));
  }

  @Test
  public void testGameOverLoseBogus() {
    testRun(new BasicPyramidSolitaire(),
            new InputInteraction("rmwd 3 9 9 rmwd 4 9 8 a ? rmwd 3 9 7 rm1 9 4 rm2 9 3 9 5 " +
                    "rm2 9 2 9 6 rm2 8 5 8 6 dd 1 dd 4 dd 2 dd 3 rm2 9 1 8 2 rm2 8 3 8 8 " +
                    "rm2 8 4 8 7 rm1 7 6 rm2 7 7 7 5 rm2 7 4 8 1 rm2 6 5 6 6 rm2 6 4 7 1 rm1 5 4"),
            new PrintInteraction("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 4♥\n" +
                    "Score: 328\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 2♥\n" +
                    "Score: 319\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 2♥\n" +
                    "Unknown move! Move commands are:\n" +
                    "1. Remove: rm1 row card\n" +
                    "2. Remove Pair: rm2 row1 card1 row2 card2\n" +
                    "3. Remove Using Draw: rmwd draw row card\n" +
                    "4. Discard Draw: dd draw\n" +
                    "5. Quit: q\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
                    "Draw: 7♥, 6♥, 3♥, 2♥\n" +
                    "Unknown move! Move commands are:\n" +
                    "1. Remove: rm1 row card\n" +
                    "2. Remove Pair: rm2 row1 card1 row2 card2\n" +
                    "3. Remove Using Draw: rmwd draw row card\n" +
                    "4. Discard Draw: dd draw\n" +
                    "5. Quit: q\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 309\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣      Q♥  J♥\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 296\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣              J♥\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 283\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 270\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: 7♥, 6♥, A♥, 2♥\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw:    6♥, A♥, 2♥\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw:    6♥, A♥,\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw:       A♥,\n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: \n" +
                    "Score: 257\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣      9♣  8♣          5♣  4♣\n" +
                    "Draw: \n" +
                    "Score: 244\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣          8♣          5♣\n" +
                    "Draw: \n" +
                    "Score: 231\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣\n" +
                    "Draw: \n" +
                    "Score: 218\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦      Q♣\n" +
                    "  J♣\n" +
                    "Draw: \n" +
                    "Score: 205\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦\n" +
                    "  J♣\n" +
                    "Draw: \n" +
                    "Score: 192\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦\n" +
                    "Draw: \n" +
                    "Score: 179\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦\n" +
                    "    5♦  4♦  3♦\n" +
                    "Draw: \n" +
                    "Score: 166\n" +
                    "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦\n" +
                    "        4♦  3♦\n" +
                    "Draw: \n" +
                    "Score: 153\n" +
                    "Game over. Score: 140\n"));
  }

  @Test
  public void testInputsGood() {
    Reader in = new StringReader(
            "rm1 ? 9 4 " +
                    "rm2 9 1 9 ? 7 " +
                    "rmwd 4 9 8 " +
                    "dd 4 " +
                    "q " +
                    "dd 4");
    StringBuilder dontCareOutput = new StringBuilder();
    PyramidSolitaireTextualController controller =
            new PyramidSolitaireTextualController(in, dontCareOutput);

    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel model = new ConfirmInputsPyramidSolitaireModel(log);

    controller.playGame(model, deck, false, 9, 4);
    assertEquals("shuffle = false, numRows = 9, numDraw = 4\n" +
            "row = 8, card = 3\n" +
            "row1 = 8, card1 = 0, row2 = 8, card2 = 6\n" +
            "drawIndex = 3, row = 8, card = 7\n" +
            "drawIndex = 3\n", log.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testInputsFail() {
    Reader in = new StringReader(
            "");
    StringBuilder dontCareOutput = new StringBuilder();
    PyramidSolitaireTextualController controller =
            new PyramidSolitaireTextualController(in, dontCareOutput);

    StringBuilder log = new StringBuilder();
    PyramidSolitaireModel model = new ConfirmInputsPyramidSolitaireModel(log);

    controller.playGame(model, deck, false, 9, 4);
  }
}
