import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the methods of {@link BasicPyramidSolitaire}, {@link PyramidSolitaireTextualView} and
 * {@link Card}.
 */
public class BasicPyramidSolitaireTest {
  private List<Card> deck = new ArrayList<>();
  private List<Card> smallDeck = new ArrayList<>();
  private final BasicPyramidSolitaire baseModel = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelA = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelB = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelC = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelD = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelE = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelZ = new BasicPyramidSolitaire();
  private final BasicPyramidSolitaire testModelShuffle = new BasicPyramidSolitaire();

  @Before
  public void init() {
    deck = baseModel.getDeck();
    smallDeck = baseModel.getDeck();
    smallDeck.remove(0);
    smallDeck.remove(1);
    smallDeck.remove(2);
    List<Card> largeDeck = baseModel.getDeck();
    largeDeck.add(deck.get(0));
    largeDeck.add(deck.get(7));
    largeDeck.add(deck.get(3));
    largeDeck.add(deck.get(1));
    testModelA.startGame(deck, false, 9, 4);
    testModelB.startGame(deck, false, 3, 1);
    testModelC.startGame(deck, false, 5, 0);
    testModelZ.startGame(deck, false, 3, 3);
  }

  @Test
  public void testPyramidSolitaireTextualView() {
    assertEquals(
            "                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
                    "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥  8♥\n" +
                    "Draw: 7♥, 6♥, 5♥, 4♥",
            new PyramidSolitaireTextualView(testModelA).toString());
    assertEquals(
            "    K♠\n" +
                    "  Q♠  J♠\n" +
                    "10♠ 9♠  8♠\n" +
                    "Draw: 7♠",
            new PyramidSolitaireTextualView(testModelB).toString());
    assertEquals(
            "        K♠\n" +
                    "      Q♠  J♠\n" +
                    "    10♠ 9♠  8♠\n" +
                    "  7♠  6♠  5♠  4♠\n" +
                    "3♠  2♠  A♠  K♦  Q♦\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelC).toString());
    assertEquals("", new PyramidSolitaireTextualView(testModelD).toString());
  }

  @Test
  public void removeAll() {
    testModelA.removeUsingDraw(2, 8, 8);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "3♣  2♣  A♣  K♥  Q♥  J♥  10♥ 9♥\n" +
            "Draw: 7♥, 6♥, 3♥, 4♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.removeUsingDraw(3, 8, 7);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "3♣  2♣  A♣  K♥  Q♥  J♥  10♥\n" +
            "Draw: 7♥, 6♥, 3♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.removeUsingDraw(2, 8, 6);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "3♣  2♣  A♣  K♥  Q♥  J♥\n" +
            "Draw: 7♥, 6♥, A♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(8, 3);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "3♣  2♣  A♣      Q♥  J♥\n" +
            "Draw: 7♥, 6♥, A♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(8, 2, 8, 4);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "3♣  2♣              J♥\n" +
            "Draw: 7♥, 6♥, A♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(8, 1, 8, 5);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣  7♣  6♣  5♣  4♣\n" +
            "3♣\n" +
            "Draw: 7♥, 6♥, A♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(7, 4, 7, 5);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
            "3♣\n" +
            "Draw: 7♥, 6♥, A♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.discardDraw(0);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
            "3♣\n" +
            "Draw:    6♥, A♥, 2♥", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.discardDraw(3);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
            "3♣\n" +
            "Draw:    6♥, A♥,", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.discardDraw(1);
    assertEquals("                K♠\n" +
            "              Q♠  J♠\n" +
            "            10♠ 9♠  8♠\n" +
            "          7♠  6♠  5♠  4♠\n" +
            "        3♠  2♠  A♠  K♦  Q♦\n" +
            "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
            "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
            "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
            "3♣\n" +
            "Draw:       A♥,", new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.discardDraw(2);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣  10♣ 9♣  8♣          5♣  4♣\n" +
                    "3♣\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(8, 0, 7, 1);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣      9♣  8♣          5♣  4♣\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(7, 2, 7, 7);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣          8♣          5♣\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(7, 3, 7, 6);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦  K♣  Q♣\n" +
                    "  J♣\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(6, 5);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦  A♦      Q♣\n" +
                    "  J♣\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(6, 6, 6, 4);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦  2♦\n" +
                    "  J♣\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(6, 3, 7, 0);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦  7♦  6♦\n" +
                    "    5♦  4♦  3♦\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(5, 4, 5, 5);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦  8♦\n" +
                    "    5♦  4♦  3♦\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(5, 3, 6, 0);
    assertEquals("                K♠\n" +
                    "              Q♠  J♠\n" +
                    "            10♠ 9♠  8♠\n" +
                    "          7♠  6♠  5♠  4♠\n" +
                    "        3♠  2♠  A♠  K♦  Q♦\n" +
                    "      J♦  10♦ 9♦\n" +
                    "        4♦  3♦\n" +
                    "Draw: ",
            new PyramidSolitaireTextualView(testModelA).toString());
    testModelA.remove(4, 3);
    assertEquals("Game over. Score: 140",
            new PyramidSolitaireTextualView(testModelA).toString());
  }

  @Test
  public void deckShuffle() {
    assertEquals("    K♠\n" +
                    "  Q♠  J♠\n" +
                    "10♠ 9♠  8♠\n" +
                    "Draw: 7♠, 6♠, 5♠",
            new PyramidSolitaireTextualView(testModelZ).toString());
    testModelShuffle.startGame(deck, true, 3, 3);
    assertNotEquals(new PyramidSolitaireTextualView(testModelZ).toString(),
            new PyramidSolitaireTextualView(testModelShuffle).toString());
  }

  @Test
  public void startGameMidGame() {
    assertEquals("    K♠\n" +
                    "  Q♠  J♠\n" +
                    "10♠ 9♠  8♠\n" +
                    "Draw: 7♠",
            new PyramidSolitaireTextualView(testModelB).toString());
    testModelB.startGame(deck, false, 5, 1);
    assertEquals("        K♠\n" +
                    "      Q♠  J♠\n" +
                    "    10♠ 9♠  8♠\n" +
                    "  7♠  6♠  5♠  4♠\n" +
                    "3♠  2♠  A♠  K♦  Q♦\n" +
                    "Draw: J♦",
            new PyramidSolitaireTextualView(testModelB).toString());

  }

  @Test
  public void getNumRows() {
    assertEquals(9, testModelA.getNumRows());
    assertEquals(3, testModelB.getNumRows());
    assertEquals(5, testModelC.getNumRows());
  }

  @Test
  public void getNumDraw() {
    assertEquals(4, testModelA.getNumDraw());
    assertEquals(1, testModelB.getNumDraw());
    assertEquals(0, testModelC.getNumDraw());
  }

  @Test
  public void getRowWidth() {
    assertEquals(1, testModelA.getRowWidth(0));
    assertEquals(3, testModelA.getRowWidth(2));
    assertEquals(9, testModelA.getRowWidth(8));
  }

  @Test
  public void gameIsNotOverPyramidValid() {
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    testModelA.discardDraw(1);
    testModelA.discardDraw(2);
    testModelA.discardDraw(3);
    assertFalse(testModelA.isGameOver());
  }

  @Test
  public void gameIsNotOverDraw() {
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    testModelA.discardDraw(1);
    testModelA.discardDraw(2);
    assertFalse(testModelA.isGameOver());
  }

  @Test
  public void gameIsNotOverStock() {
    testModelA.discardDraw(0);
    testModelA.discardDraw(0);
    assertFalse(testModelA.isGameOver());
  }

  @Test
  public void gameIsOver() {
    testModelA.removeUsingDraw(2, 8, 8);
    testModelA.removeUsingDraw(3, 8, 7);
    testModelA.removeUsingDraw(2, 8, 6);
    testModelA.remove(8, 3);
    testModelA.remove(8, 2, 8, 4);
    testModelA.remove(8, 1, 8, 5);
    testModelA.remove(7, 4, 7, 5);
    testModelA.discardDraw(0);
    testModelA.discardDraw(3);
    testModelA.discardDraw(1);
    testModelA.discardDraw(2);
    testModelA.remove(8, 0, 7, 1);
    testModelA.remove(7, 2, 7, 7);
    testModelA.remove(7, 3, 7, 6);
    testModelA.remove(6, 5);
    testModelA.remove(6, 6, 6, 4);
    testModelA.remove(6, 3, 7, 0);
    testModelA.remove(5, 4, 5, 5);
    testModelA.remove(5, 3, 6, 0);
    testModelA.remove(4, 3);
    assertTrue(testModelA.isGameOver());
  }

  @Test
  public void getScore() {
    assertEquals(63, testModelB.getScore());
    assertEquals(336, testModelA.getScore());
    testModelA.removeUsingDraw(2, 8, 8);
    assertEquals(328, testModelA.getScore());
  }

  @Test
  public void getCardAt() {
    assertEquals("K♠", testModelA.getCardAt(0, 0).toString());
    assertEquals("10♥", testModelA.getCardAt(8, 6).toString());
    assertEquals("8♥", testModelA.getCardAt(8, 8).toString());
  }

  @Test
  public void getDrawCards() {
    assertEquals("[7♥, 6♥, 5♥, 4♥]", testModelA.getDrawCards().toString());
    assertEquals("[7♠]", testModelB.getDrawCards().toString());
    assertEquals("[]", testModelC.getDrawCards().toString());
  }

  @Test(expected = IllegalStateException.class)
  public void gameOverNotStarted() {
    testModelE.isGameOver();
  }

  @Test(expected = IllegalStateException.class)
  public void drawCardsNotStarted() {
    testModelE.getDrawCards();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCardAtHighCard() {
    testModelA.getCardAt(4, 5);
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
    testModelE.getCardAt(3, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void scoreGameNotStarted() {
    testModelE.getScore();
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
    testModelD.startGame(deck, false, 9, 8);
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
    testModelE.remove(8, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidSingleRemoveValue() {
    testModelA.remove(8, 2);
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
    testModelE.remove(8, 2, 4, 3);
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
    testModelE.removeUsingDraw(8, 2, 4);
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