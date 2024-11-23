import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.dicegame.Game;
import com.example.dicegame.Player;

public class GameTest {
  private Game game;
  Player player1;
  Player player2; 
  @BeforeEach
  void setUp() {
    ArrayList<Player> players = new ArrayList<>();
    player1 = new Player("John");
    player2 = new Player("Jack");
    players.add(player1);
    players.add(player2);

    game = new Game(players);
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
      new Game(players);
    } catch (IllegalArgumentException e) {
      exception = e;
    }
    assertEquals("Game should have at least two players.", exception.getMessage(),
        "Exception message should indicate insufficient players.");
  }
  @Test
  void shouldUpdateScoreAfterRoll() {
    int diceScore = game.rollDice(player1);

    assertEquals(player1.getScore(), diceScore);

  }

}
