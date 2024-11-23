import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

public class PlayerTest {

  @BeforeEach
  void setUp() {
    Player player = new Player("John");
  }
  @Test
  void playerShouldStartWithZeroScore(){
    assertEquals(0, player.getScore(), "Player should start with zero score");
  }  
}
