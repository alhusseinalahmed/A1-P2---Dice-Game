import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.dicegame.App;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTest {

  @Test
  void testMainMethod() {
    // simulated user input
    String simulatedInput = "p\n2\nJohn\nJack\nr\nq" + //
        "";

    // Save original System.in and System.out to restore later
    InputStream originalSystemIn = System.in;
    PrintStream originalSystemOut = System.out;

    try {
      // Redirect System.in to the simulated input
      System.setIn(new java.io.ByteArrayInputStream(simulatedInput.getBytes()));

      // Redirect System.out to capture the output
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));

      // Call the main method
      App.main(new String[] {});

      String output = outputStream.toString();
      assertTrue(output.contains("Welcome to this dice game, let's play!"));
      assertTrue(output.contains("Enter the number of players"));
      assertTrue(output.contains("Game started"));
      assertTrue(output.contains("The winner is"));
    } finally {
      // Restore original System.in and System.out
      System.setIn(originalSystemIn);
      System.setOut(originalSystemOut);
    }
  }
}
