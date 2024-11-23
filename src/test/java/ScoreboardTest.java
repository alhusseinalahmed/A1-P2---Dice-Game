import com.example.dicegame.Scoreboard;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
