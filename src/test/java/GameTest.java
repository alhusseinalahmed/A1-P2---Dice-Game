import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.example.dicegame.Game;
import com.example.dicegame.Player;
import com.example.dicegame.Dice;

public class GameTest {
  private Game game;
  private Dice mockDice; // Mocked Dice
  private Player player1;
  private Player player2;

  @BeforeEach
  void setUp() {
    // Create mock Dice object
    mockDice = Mockito.mock(Dice.class);

    // Create players
    ArrayList<Player> players = new ArrayList<>();
    player1 = new Player("John");
    player2 = new Player("Jack");
    players.add(player1);
    players.add(player2);

    game = new Game(players, mockDice);
  }

  @Test
  void gameShouldStartWithAtLeastTwoPlayers() {
    assertEquals(2, game.getPlayers().size(), "Game should have at least two players.");
  }

  @Test
  void gameShouldNotStartWithLessThanTwoPlayers() {
    Exception exception = null;
    try {
      ArrayList<Player> players = new ArrayList<>();
      new Game(players, mockDice);
    } catch (IllegalArgumentException e) {
      exception = e;
    }
    assertEquals("Game should have at least two players.", exception.getMessage(),
        "Exception message should indicate insufficient players.");
  }

  @Test
  void shouldUpdateScoreAfterRoll() {
    // Mock the Dice roll to return 5
    when(mockDice.roll()).thenReturn(5);

    int diceScore = game.rollDice(player1);

    // Verify that the dice score is added to the player's score
    assertEquals(5, diceScore, "The roll result should match the mocked dice value.");
    assertEquals(5, player1.getScore(), "Player's score should be updated after the roll.");

    verify(mockDice, times(1)).roll();
  }

  @Test
  void shouldEndGameWhenWinScoreIsReached() {
    game.setWinScore(10);
    game.updatePlayerScore(player1, 10);
    assertTrue(game.getGameOver(), "Game should end when a player reaches the win score.");
  }
}
