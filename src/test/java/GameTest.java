import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.dicegame.Game;
import com.example.dicegame.Player;

public class GameTest {
  private Game game;
  @BeforeEach
  void setUp() {
    ArrayList<Player> players = new ArrayList<>();
    players.add(new Player("John"));
    players.add(new Player("Jack"));

    game = new Game(players);
  }

  @Test
  void gameShouldStartWithAtLeastTwoPlayers() {
    assertEquals(2, game.getPlayers().size(), "Game should have two players initially.");
  }
}
