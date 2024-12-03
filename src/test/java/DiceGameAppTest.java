import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
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
    when(mockScanner.nextLine()).thenThrow(new NoSuchElementException()); // Unexpected input

    DiceGameApp app = new DiceGameApp();

    // Verify interactions
    assertThrows(Exception.class, () -> app.startGame(mockScanner, new Dice()));
  }

  @Test
  void testIllegalPlayerCount() {
    Scanner mockScanner = Mockito.mock(Scanner.class);
    when(mockScanner.nextLine()).thenReturn("p");
    when(mockScanner.nextInt()).thenReturn(5); // Invalid player count

    DiceGameApp app = new DiceGameApp();
    app.startGame(mockScanner, new Dice());
    verify(mockScanner, times(2)).nextLine(); // Ensure the input was read
    verify(mockScanner, times(1)).nextInt(); // Ensure the invalid player count was requested
  }

  @Test
  void testInvalidInputOnRoll() {
    Scanner mockScanner = Mockito.mock(Scanner.class);
    Dice mockDice = Mockito.mock(Dice.class);

    when(mockScanner.nextLine())
        .thenReturn("p") // Start the game
        .thenReturn("2") // Number of players
        .thenReturn("John") // Player 1 name
        .thenReturn("Jack") // Player 2 name
        .thenReturn("z"); // Invalid input for player roll

    when(mockScanner.nextInt()).thenReturn(2); // Number of players

    DiceGameApp app = new DiceGameApp();

    app.startGame(mockScanner, mockDice);

    // Verify interactions
    verify(mockScanner, atLeast(5)).nextLine(); // Ensure at least 5 inputs processed
    verify(mockScanner, times(1)).nextInt(); // Ensure player count input was read
  }

  void testInvalidMenuInput() {
    Scanner mockScanner = Mockito.mock(Scanner.class);
    when(mockScanner.nextLine()).thenThrow(new InputMismatchException()); // Invalid input for menu

    DiceGameApp app = new DiceGameApp();

    app.startGame(mockScanner, new Dice());

    // Verify interactions
    verify(mockScanner, times(1)).nextLine(); // Ensure one input was read
  }

  void testInvalidSwitchOption() {
    Scanner mockScanner = Mockito.mock(Scanner.class);

    when(mockScanner.nextLine()).thenReturn("invalid"); // Invalid input during menu selection

    DiceGameApp app = new DiceGameApp();

    // Redirect System.out to capture output
    var outStream = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(outStream));

    app.startGame(mockScanner, new Dice());

    // Verify the correct error message was printed
    String output = outStream.toString();
    assertTrue(output.contains("Invalid input."), "Should print 'Invalid input.' for invalid menu option");

    // Verify Scanner interaction
    verify(mockScanner, times(1)).nextLine(); 

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
