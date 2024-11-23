
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.example.dicegame.Dice;

public class DiceTest {
  @Test
  void diceRollShouldReturnNumberBetween1And6() {
    Dice dice = new Dice();
    int roll = dice.roll();
    assertTrue(roll >= 1 && roll <= 6, "Dice roll should be between 1 and 6");
  }
}