import com.example.dicegame.Scoreboard;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScoreboardTest {
  private Scoreboard scoreboard;

  @BeforeEach
  void setUp() {
    scoreboard = new Scoreboard();
  }

  @Test
  void shouldInitializeWithZeroScores() {
    assertEquals(0, scoreboard.getScore("Player 1"), "Player 1's score should be 0 initially.");
  }

  @Test
  void shouldUpdatePlayerScore() {
    scoreboard.updateScore("Player 1", 5);
    assertEquals(5, scoreboard.getScore("Player 1"), "Player 1's score should be 5 after updating.");
  }

  @Test
  void shouldDisplayAllScores() {
    Scoreboard scoreboard = new Scoreboard();
    scoreboard.updateScore("Player 1", 10);
    scoreboard.updateScore("Player 2", 15);

    Map<String, Integer> allScores = scoreboard.getAllScores();

    assertEquals(10, allScores.get("Player 1"), "Player 1's score should be 10.");
    assertEquals(15, allScores.get("Player 2"), "Player 2's score should be 15.");
  }
}
