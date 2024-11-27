import com.example.dicegame.Player;
import com.example.dicegame.Scoreboard;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreboardTest {
  private Scoreboard scoreboard;
  private Player player1;
  private Player player2;

  @BeforeEach
  void setUp() {
    scoreboard = new Scoreboard();
    player1 = new Player("John");
    player2 = new Player("Jack");

  }

  @Test
  void shouldInitializeWithZeroScores() {
    assertEquals(0, scoreboard.getScore(player1), "Player 1's score should be 0 initially.");
  }

  @Test
  void shouldUpdatePlayerScore() {
    scoreboard.updateScore(player1, 5);
    assertEquals(5, scoreboard.getScore(player1), "Player 1's score should be 5 after updating.");
  }

  @Test
  void shouldDisplayAllScores() {
    scoreboard.updateScore(player1, 10);
    scoreboard.updateScore(player2, 15);

    Map<Player, Integer> allScores = scoreboard.getAllScores();

    assertEquals(10, allScores.get(player1), "Player 1's score should be 10.");
    assertEquals(15, allScores.get(player2), "Player 2's score should be 15.");
  }

  @Test
  void shouldReturnStringOfAllScores() {
    scoreboard.updateScore(player1, 10);
    scoreboard.updateScore(player2, 15);

    String expected = "Scoreboard: \nJohn: 10\nJack: 15\n";
    assertEquals(expected, scoreboard.toString(), "All scores should be displayed as a string.");
  }
}
