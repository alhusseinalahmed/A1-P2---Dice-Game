package com.example.dicegame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DiceGameApp {

  public void startGame(Scanner scanner, Dice dice) {
    System.out.println("Welcome to this dice game, let's play!");
    System.out.println("Type p to play or q to quit.");

    String option = scanner.nextLine();
    try {
      switch (option) {
        case "p":
          System.out.println("Enter the number of players: (2-4)");
          int numPlayers = scanner.nextInt();
          scanner.nextLine(); // Consume newline

          if (numPlayers < 2 || numPlayers > 4) {
            throw new InputMismatchException("Number of players must be between 2 and 4.");
          }

          ArrayList<Player> players = new ArrayList<>();
          for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter player " + (i + 1) + " name: ");
            String name = scanner.nextLine();
            players.add(new Player(name));
          }

          Game game = new Game(players, dice);
          System.out.println("Game started with " + numPlayers + " players.");

          while (!game.getGameOver()) {
            for (Player player : game.getPlayers()) {
              System.out.println("It's " + player.getName() + "'s turn.");
              System.out.println("Type r to roll the dice or q to quit.");
              String playerOption = scanner.nextLine();
              if ("q".equals(playerOption)) {
                game.setGameOver(true);
                break;
              } else if ("r".equals(playerOption)) {
                int diceScore = game.rollDice(player);
                System.out.println("You rolled a " + diceScore + ".");
                System.out.println(game.getScoreboard().toString());
              } else {
                throw new InputMismatchException("Invalid input.");
              }
            }
          }

          Player winner = game.getScoreboard().getWinner();
          System.out.println("The winner is " + winner.getName() + " with " + winner.getScore() + " points.");
          break;

        case "q":
          System.out.println("Goodbye!");
          break;

        default:
          throw new InputMismatchException("Invalid input.");
      }
    } catch (InputMismatchException e) {
      System.out.println(e.getMessage());
    }
  }

}
