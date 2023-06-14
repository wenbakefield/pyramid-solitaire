import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator;
import cs3500.pyramidsolitaire.model.hw04.PyramidSolitaireCreator.GameType;
import cs3500.pyramidsolitaire.model.hw04.TriPeaksPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

/**
 * Tests for the methods of {@link TriPeaksPyramidSolitaire}.
 */
public class TriPeaksPyramidSolitaireTest {
  private List<Card> deck = new ArrayList<>();
  private final List<Card> smallDeck = new ArrayList<>();
  private final PyramidSolitaireModel testModelA =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelB =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelC =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelD =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelE =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelF =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelG =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);
  private final PyramidSolitaireModel testModelZ =
          PyramidSolitaireCreator.create(GameType.TRIPEAKS);

  @Before
  public void init() {
    deck = testModelA.getDeck();
    testModelA.startGame(deck, false, 6, 8);
    testModelB.startGame(deck, false, 7, 0);
    testModelC.startGame(deck, false, 8, 4);
    testModelD.startGame(deck, false, 1, 6);
    testModelE.startGame(deck, false, 2, 1);
    testModelF.startGame(deck, false, 3, 5);
    testModelG.startGame(deck, false, 4, 10);
  }

  @Test
  public void testPyramidSolitaireTextualView() {
    assertEquals(
            "K♠\n" +
                    "Draw: Q♠, J♠, 10♠, 9♠, 8♠, 7♠",
            new PyramidSolitaireTextualView(testModelD).toString());
    assertEquals(
            "  K♠  Q♠  J♠\n" +
                    "10♠ 9♠  8♠  7♠\n" +
                    "Draw: 6♠",
            new PyramidSolitaireTextualView(testModelE).toString());
    assertEquals(
            "    K♠  Q♠  J♠\n" +
                    "  10♠ 9♠  8♠  7♠\n" +
                    "6♠  5♠  4♠  3♠  2♠\n" +
                    "Draw: A♠, K♦, Q♦, J♦, 10♦",
            new PyramidSolitaireTextualView(testModelF).toString());
    assertEquals(
            "      K♠      Q♠      J♠\n" +
                    "    10♠ 9♠  8♠  7♠  6♠  5♠\n" +
                    "  4♠  3♠  2♠  A♠  K♦  Q♦  J♦\n" +
                    "10♦ 9♦  8♦  7♦  6♦  5♦  4♦  3♦\n" +
                    "Draw: 2♦, A♦, K♣, Q♣, J♣, 10♣, 9♣, 8♣, 7♣, 6♣",
            new PyramidSolitaireTextualView(testModelG).toString());
    assertEquals(
            "          K♠          Q♠          J♠\n" +
                    "        10♠ 9♠      8♠  7♠      6♠  5♠\n" +
                    "      4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦\n" +
                    "    8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣\n" +
                    "K♥  Q♥  J♥  10♥ 9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥\n" +
                    "Draw: A♥, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠",
            new PyramidSolitaireTextualView(testModelA).toString());
    assertEquals(
            "            K♠          Q♠          J♠\n" +
                    "          10♠ 9♠      8♠  7♠      6♠  5♠\n" +
                    "        4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦\n" +
                    "      8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "    J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣\n" +
                    "  K♥  Q♥  J♥  10♥ 9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥\n" +
                    "A♥  K♠  Q♠  J♠  10♠ 9♠  8♠  7♠  6♠  5♠  4♠  3♠  2♠\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelB).toString());
    assertEquals(
            "              K♠              Q♠              J♠\n" +
                    "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
                    "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
                    "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
                    "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
                    "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
                    "  8♠  7♠  6♠  5♠  4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
                    "6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "Draw: 3♣, 2♣, A♣, K♥",
            new PyramidSolitaireTextualView(testModelC).toString());
  }

  @Test
  public void removeAll() {
    testModelC.remove(7, 6);
    assertEquals("              K♠              Q♠              J♠\n" +
            "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
            "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
            "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
            "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
            "  8♠  7♠  6♠  5♠  4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
            "6♦  5♦  4♦  3♦  2♦  A♦      Q♣  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "Draw: 3♣, 2♣, A♣, K♥", new PyramidSolitaireTextualView(testModelC).toString());
    testModelC.discardDraw(1);
    assertEquals("              K♠              Q♠              J♠\n" +
            "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
            "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
            "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
            "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
            "  8♠  7♠  6♠  5♠  4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
            "6♦  5♦  4♦  3♦  2♦  A♦      Q♣  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "Draw: 3♣, Q♥, A♣, K♥", new PyramidSolitaireTextualView(testModelC).toString());
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    testModelC.discardDraw(1);
    assertEquals("              K♠              Q♠              J♠\n" +
            "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
            "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
            "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
            "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
            "  8♠  7♠  6♠  5♠  4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
            "6♦  5♦  4♦  3♦  2♦  A♦      Q♣  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "Draw: 3♣,    A♣, K♥", new PyramidSolitaireTextualView(testModelC).toString());
    testModelC.discardDraw(0);
    testModelC.removeUsingDraw(2, 7, 7);
    assertEquals("              K♠              Q♠              J♠\n" +
            "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
            "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
            "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
            "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
            "  8♠  7♠  6♠  5♠  4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
            "6♦  5♦  4♦  3♦  2♦  A♦          J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "Draw:          K♥", new PyramidSolitaireTextualView(testModelC).toString());
    testModelC.discardDraw(3);
    assertEquals("              K♠              Q♠              J♠\n" +
            "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
            "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
            "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
            "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
            "  8♠  7♠  6♠  5♠  4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
            "6♦  5♦  4♦  3♦  2♦  A♦          J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "Draw: ", new PyramidSolitaireTextualView(testModelC).toString());
    testModelC.remove(7, 8, 6, 6);
    assertEquals("              K♠              Q♠              J♠\n" +
            "            10♠ 9♠          8♠  7♠          6♠  5♠\n" +
            "          4♠  3♠  2♠      A♠  K♦  Q♦      J♦  10♦ 9♦\n" +
            "        8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣  J♣  10♣\n" +
            "      9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "    9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥  A♥  K♠  Q♠  J♠  10♠ 9♠\n" +
            "  8♠  7♠  6♠  5♠  4♠  3♠      A♠  K♦  Q♦  J♦  10♦ 9♦  8♦  7♦\n" +
            "6♦  5♦  4♦  3♦  2♦  A♦              10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "Draw: ", new PyramidSolitaireTextualView(testModelC).toString());
  }

  @Test
  public void deckShuffle() {
    assertEquals("            K♠          Q♠          J♠\n" +
                    "          10♠ 9♠      8♠  7♠      6♠  5♠\n" +
                    "        4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦\n" +
                    "      8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "    J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣\n" +
                    "  K♥  Q♥  J♥  10♥ 9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥\n" +
                    "A♥  K♠  Q♠  J♠  10♠ 9♠  8♠  7♠  6♠  5♠  4♠  3♠  2♠\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelB).toString());
    testModelB.startGame(deck, true, 7, 0);
    assertNotEquals("            K♠          Q♠          J♠\n" +
                    "          10♠ 9♠      8♠  7♠      6♠  5♠\n" +
                    "        4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦\n" +
                    "      8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "    J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣\n" +
                    "  K♥  Q♥  J♥  10♥ 9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥\n" +
                    "A♥  K♠  Q♠  J♠  10♠ 9♠  8♠  7♠  6♠  5♠  4♠  3♠  2♠\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelB).toString());
  }

  @Test
  public void startGameMidGame() {
    assertEquals("            K♠          Q♠          J♠\n" +
                    "          10♠ 9♠      8♠  7♠      6♠  5♠\n" +
                    "        4♠  3♠  2♠  A♠  K♦  Q♦  J♦  10♦ 9♦\n" +
                    "      8♦  7♦  6♦  5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "    J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣  3♣  2♣  A♣\n" +
                    "  K♥  Q♥  J♥  10♥ 9♥  8♥  7♥  6♥  5♥  4♥  3♥  2♥\n" +
                    "A♥  K♠  Q♠  J♠  10♠ 9♠  8♠  7♠  6♠  5♠  4♠  3♠  2♠\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelB).toString());
    testModelB.startGame(deck, false, 5, 1);
    assertEquals("        K♠      Q♠      J♠\n" +
                    "      10♠ 9♠  8♠  7♠  6♠  5♠\n" +
                    "    4♠  3♠  2♠  A♠  K♦  Q♦  J♦\n" +
                    "  10♦ 9♦  8♦  7♦  6♦  5♦  4♦  3♦\n" +
                    "2♦  A♦  K♣  Q♣  J♣  10♣ 9♣  8♣  7♣\n" +
                    "Draw: 6♣",
            new PyramidSolitaireTextualView(testModelB).toString());
  }

  @Test
  public void getNumRows() {
    assertEquals(6, testModelA.getNumRows());
    assertEquals(7, testModelB.getNumRows());
    assertEquals(8, testModelC.getNumRows());
  }

  @Test
  public void getNumDraw() {
    assertEquals(8, testModelA.getNumDraw());
    assertEquals(0, testModelB.getNumDraw());
    assertEquals(4, testModelC.getNumDraw());
  }

  @Test
  public void getRowWidth() {
    assertEquals(7, testModelA.getRowWidth(0));
    assertEquals(9, testModelA.getRowWidth(2));
    assertEquals(13, testModelA.getRowWidth(6));
  }

  @Test
  public void gameIsOver() {
    assertEquals("    K♠  Q♠  J♠\n" +
                    "  10♠ 9♠  8♠  7♠\n" +
                    "6♠  5♠  4♠  3♠  2♠\n" +
                    "Draw: A♠, K♦, Q♦, J♦, 10♦",
            new PyramidSolitaireTextualView(testModelF).toString());
    assertFalse(testModelF.isGameOver());
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    assertFalse(testModelF.isGameOver());
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(0);
    testModelF.discardDraw(1);
    testModelF.discardDraw(2);
    testModelF.discardDraw(3);
    testModelF.discardDraw(4);
    assertTrue(testModelF.isGameOver());
    assertEquals("Game over. Score: 90",
            new PyramidSolitaireTextualView(testModelF).toString());
  }

  @Test
  public void gameIsOverWin() {
    assertEquals("K♠\n" +
                    "Draw: Q♠, J♠, 10♠, 9♠, 8♠, 7♠",
            new PyramidSolitaireTextualView(testModelD).toString());
    assertFalse(testModelD.isGameOver());
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(0);
    testModelD.discardDraw(1);
    testModelD.discardDraw(2);
    testModelD.discardDraw(3);
    testModelD.discardDraw(4);
    testModelD.discardDraw(5);
    assertFalse(testModelD.isGameOver());
    testModelD.remove(0, 0);
    assertTrue(testModelD.isGameOver());
    assertEquals("You win!",
            new PyramidSolitaireTextualView(testModelD).toString());
  }

  @Test
  public void getScore() {
    assertEquals(454, testModelB.getScore());
    assertEquals(363, testModelA.getScore());
    testModelA.remove(5, 0);
    assertEquals(350, testModelA.getScore());
  }

  @Test
  public void getCardAt() {
    assertEquals("K♠", testModelA.getCardAt(0, 0).toString());
    assertEquals("5♣", testModelA.getCardAt(4, 6).toString());
    assertEquals("K♣", testModelA.getCardAt(3, 8).toString());
  }

  @Test
  public void getDrawCards() {
    assertEquals("[A♥, K♠, Q♠, J♠, 10♠, 9♠, 8♠, 7♠]",
            testModelA.getDrawCards().toString());
    assertEquals("[]", testModelB.getDrawCards().toString());
    assertEquals("[3♣, 2♣, A♣, K♥]", testModelC.getDrawCards().toString());
  }

  @Test(expected = IllegalStateException.class)
  public void gameOverNotStarted() {
    testModelZ.isGameOver();
  }

  @Test(expected = IllegalStateException.class)
  public void drawCardsNotStarted() {
    testModelZ.getDrawCards();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardAtHighCard() {
    testModelA.getCardAt(6, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardAtLowCard() {
    testModelA.getCardAt(4, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardAtHighRow() {
    testModelA.getCardAt(9, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardAtLowRow() {
    testModelA.getCardAt(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void getCardGameNotStarted() {
    testModelZ.getCardAt(3, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void scoreGameNotStarted() {
    testModelZ.getScore();
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNumRowLow() {
    testModelD.startGame(deck, false, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNumRowHigh() {
    testModelE.startGame(deck, false, 10, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNumDrawHigh() {
    testModelD.startGame(deck, false, 7, 106);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNumDrawLow() {
    testModelE.startGame(deck, false, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalLittleDeck() {
    testModelD.startGame(smallDeck, false, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNullDeckBlankOutput() {
    testModelD.startGame(null, false, 5, 3);
    assertEquals(testModelD.toString(), "");
  }

  @Test(expected = IllegalStateException.class)
  public void invalidSingleRemoveNoGame() {
    testModelZ.remove(8, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveValue() {
    testModelA.remove(8, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveValueNull() {
    testModelA.remove(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveValueNull() {
    testModelA.remove(0, 1, 0, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveValueNull() {
    testModelA.removeUsingDraw(1, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveRowLow() {
    testModelA.remove(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveRowHigh() {
    testModelA.remove(10, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveCardLow() {
    testModelA.remove(5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveCardHigh() {
    testModelA.remove(5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveCovered() {
    testModelA.remove(4, 3);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidDoubleRemoveNoGame() {
    testModelZ.remove(8, 2, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveValue() {
    testModelA.remove(8, 2, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveRowLow() {
    testModelA.remove(4, 2, -1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveRowHigh() {
    testModelA.remove(14, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveCardLow() {
    testModelA.remove(4, 3, 5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveCardHigh() {
    testModelA.remove(8, 2, 5, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDoubleRemoveCovered() {
    testModelA.remove(4, 3, 7, 5);
  }

  @Test(expected = IllegalStateException.class)
  public void invalidDrawRemoveNoGame() {
    testModelZ.removeUsingDraw(8, 2, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveValue() {
    testModelA.removeUsingDraw(0, 8, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveDeckLow() {
    testModelA.removeUsingDraw(-1, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveDeckHigh() {
    testModelA.removeUsingDraw(5, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveRowLow() {
    testModelA.removeUsingDraw(3, -1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveRowHigh() {
    testModelA.removeUsingDraw(1, 10, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveCardLow() {
    testModelA.removeUsingDraw(2, 3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveCardHigh() {
    testModelA.removeUsingDraw(3, 2, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDrawRemoveCovered() {
    testModelA.removeUsingDraw(0, 3, 1);
  }

}