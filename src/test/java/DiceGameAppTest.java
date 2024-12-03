import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;
import com.example.dicegame.DiceGameApp;
import static org.mockito.Mockito.*;
import com.example.dicegame.Dice;

import static org.junit.jupiter.api.Assertions.*;

public class DiceGameAppTest {

  @Test
  void testGameFlow() {
    // Mock the Scanner
    Scanner mockScanner = Mockito.mock(Scanner.class);
    Dice mockDice = Mockito.mock(Dice.class);

    // Define inputs for the game
    when(mockScanner.nextLine())
        .thenReturn("p") // Start the game
        .thenReturn("2") // Number of players
        .thenReturn("John") // Player 1 name
        .thenReturn("Jack") // Player 2 name
        .thenReturn("r") // Player 1 rolls
        .thenReturn("q"); // Player 2 quits

    when(mockScanner.nextInt()).thenReturn(2); // Number of players
    when(mockDice.roll()).thenReturn(5);

    DiceGameApp app = new DiceGameApp();

    app.startGame(mockScanner, mockDice);

    // Verify interactions
    verify(mockScanner, times(6)).nextLine();
    verify(mockScanner, times(1)).nextInt();
  }

  @Test
  void testQuitImmediately() {
    Scanner mockScanner = Mockito.mock(Scanner.class);

    when(mockScanner.nextLine()).thenReturn("q");

    DiceGameApp app = new DiceGameApp();

    app.startGame(mockScanner, new Dice());

    // Verify that the input was read once
    verify(mockScanner, times(1)).nextLine();
    verify(mockScanner, never()).nextInt();
  }

  @Test
  void testUnexpectedOption() {
    Scanner mockScanner = Mockito.mock(Scanner.class);
    when(mockScanner.nextLine()).thenReturn("x"); // Unexpected input

    DiceGameApp app = new DiceGameApp();
    app.startGame(mockScanner, new Dice());

    // Verify interactions
    verify(mockScanner, times(1)).nextLine();
  }

  @Test
  void testIllegalPlayerCount() {
    Scanner mockScanner = Mockito.mock(Scanner.class);
    when(mockScanner.nextLine()).thenReturn("p");
    when(mockScanner.nextInt()).thenReturn(5); // Invalid player count

    DiceGameApp app = new DiceGameApp();
    app.startGame(mockScanner, new Dice());

    // Verify that invalid player count message was printed
    verify(mockScanner, times(1)).nextInt();
    verify(mockScanner, times(2)).nextLine(); // 1 for play, 1 for consume new line
  }

  @Test
  void testWinnerDetermination() {
    // Mock Scanner and Dice
    Scanner mockScanner = Mockito.mock(Scanner.class);
    Dice mockDice = Mockito.mock(Dice.class);

    // Define inputs
    when(mockScanner.nextLine())
        .thenReturn("p") // Start the game
        .thenReturn("John") // Player 1 name
        .thenReturn("Jane") // Player 2 name
        .thenReturn("") // "Empty scanner.nextline()"
        .thenReturn("r") // Player 1 rolls
        .thenReturn("r") // Player 2 rolls again
        .thenReturn("r") // Player 1 rolls again
        .thenReturn("q"); // End game

    when(mockScanner.nextInt()).thenReturn(2); // Number of players

    // Mock dice rolls
    when(mockDice.roll())
        .thenReturn(5) // Player 1 rolls 5
        .thenReturn(3) // Player 2 rolls 3
        .thenReturn(6); // Player 1 rolls 6 again

    // Create and start the app
    DiceGameApp app = new DiceGameApp();
    app.startGame(mockScanner, mockDice);

    // Verify interactions
    verify(mockScanner, atLeast(7)).nextLine(); // 7 inputs processed
    verify(mockDice, times(3)).roll(); // 3 dice rolls: Player 1 -> Player 2 -> Player 1
  }
}
