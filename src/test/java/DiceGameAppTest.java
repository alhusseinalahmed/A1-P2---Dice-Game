import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Scanner;
import com.example.dicegame.DiceGameApp;
import static org.mockito.Mockito.*;

public class DiceGameAppTest {

    @Test
    void testGameFlow() {
        // Mock the Scanner
        Scanner mockScanner = Mockito.mock(Scanner.class);

        // Define inputs for the game
        when(mockScanner.nextLine())
            .thenReturn("p") // Start the game
            .thenReturn("2") // Number of players
            .thenReturn("John") // Player 1 name
            .thenReturn("Jack") // Player 2 name
            .thenReturn("r") // Player 1 rolls
            .thenReturn("q"); // Player 2 quits

        when(mockScanner.nextInt()).thenReturn(2); // Number of players

        DiceGameApp app = new DiceGameApp();

        app.startGame(mockScanner);

        // Verify interactions
        verify(mockScanner, times(6)).nextLine();
        verify(mockScanner, times(1)).nextInt();
    }

    @Test
    void testQuitImmediately() {
        Scanner mockScanner = Mockito.mock(Scanner.class);

        when(mockScanner.nextLine()).thenReturn("q");

        DiceGameApp app = new DiceGameApp();

        app.startGame(mockScanner);

        // Verify that the input was read once
        verify(mockScanner, times(1)).nextLine();
        verify(mockScanner, never()).nextInt();
    }
}
