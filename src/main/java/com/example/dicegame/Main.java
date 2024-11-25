package com.example.dicegame;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to this dice game, let's play!");
        System.out.println("Type p to play or q to quit.");
        String option = scanner.nextLine();
        try
        {
          switch (option) {
            case "p":
              System.out.println("Enter the number of players: (2-4)");
              int numPlayers = scanner.nextInt();
              scanner.nextLine();
              ArrayList<Player> players = new ArrayList<>();
              for(int i = 0; i < numPlayers; i++) {
                System.out.println("Enter player " + (i + 1) + " name: ");
                String name = scanner.nextLine();
                players.add(new Player(name));
              }

              try
              {
                Game game = new Game(players, new Dice());
                System.out.println("Game started with " + numPlayers + " players.");

                while(!game.getGameOver())
                {
                  for(Player player : game.getPlayers())
                  {
                    System.out.println("It's " + player.getName() + "'s turn.");
                    System.out.println("Type r to roll the dice or q to quit.");
                    String playerOption = scanner.nextLine();
                    switch (playerOption) {
                      case "r":
                        int diceScore = game.rollDice(player);
                        System.out.println("You rolled a " + diceScore + ".");
                        for(Player otherPlayer : game.getPlayers())
                        {
                          System.out.println(otherPlayer.getName() + " has " + otherPlayer.getScore() + " points.");
                        }
                        break;
                      case "q":
                        break;
                      default:
                        System.out.println("Invalid input.");
                    }
                  }
                }

                Player winner = game.getPlayers().get(0);
                for(Player player : game.getPlayers())
                {
                  if(player.getScore() > winner.getScore())
                  {
                    winner = player;
                  }
                }
                System.out.println("The winner is " + winner.getName() + " with " + winner.getScore() + " points.");
              }
              catch (IllegalArgumentException e)
              {
                System.out.println(e.getMessage());
              }


              break;
            
            case "q":
            default:
              break;
          }
        }
        catch (Exception e)
        {
            System.out.println("Invalid input.");
        }
        scanner.close();
    }
}