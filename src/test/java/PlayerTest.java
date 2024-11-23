import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.dicegame.Player;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTest {

  private Player player;

  @BeforeEach
  void setUp() {
    player = new Player("John");
  }
  @Test
  void playerShouldStartWithZeroScore(){
    assertEquals(0, player.getScore(), "Player should start with zero score");
  }  
}
