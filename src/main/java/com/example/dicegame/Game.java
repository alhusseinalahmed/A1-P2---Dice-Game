package com.example.dicegame;

import java.util.ArrayList;

public class Game {

  private final Scoreboard scoreboard = new Scoreboard();
  private ArrayList<Player> players = new ArrayList<>();

  public Game(ArrayList<Player> players) {
    if(players.size() < 2) throw new IllegalArgumentException("Game should have at least two players.");
    this.players = players;
  }

  public ArrayList<Player> getPlayers() {
    return this.players;
  }
}
