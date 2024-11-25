package com.example.dicegame;

import java.util.ArrayList;

public class Game {

  private final Scoreboard scoreboard = new Scoreboard();
  private ArrayList<Player> players = new ArrayList<>();
  private Dice dice = new Dice();

  private int winScore = 100;
  private boolean isGameOver;

  public Game(ArrayList<Player> players) {
    if (players.size() < 2) {
      throw new IllegalArgumentException("Game should have at least two players.");
    }
    this.players = players;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }

  public int rollDice(Player player) {
    int diceScore = dice.roll();
    updatePlayerScore(player, diceScore);
    return diceScore;
  }

  public void updatePlayerScore(Player player, int diceScore) {
    player.addScore(diceScore);
    scoreboard.updateScore(player.getName(), player.getScore());
    if (player.getScore() >= winScore) {
      setGameOver(true);
    }
  }

  private void setGameOver(boolean isGameOver) {
    this.isGameOver = isGameOver;
  }

  public boolean getGameOver() {
    return isGameOver;
  }

  public void setWinScore(int winScore) {
    this.winScore = winScore;
  }
}
