package com.example.dicegame;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
  private final Map<Player, Integer> scores = new HashMap<>();

  public int getScore(Player name) {
    return scores.getOrDefault(name, 0);
  }

  public void updateScore(Player player, int score) {
    scores.put(player, score);
  }

  public Map<Player, Integer> getAllScores() {
    return new HashMap<>(scores);
  }

  public String toString() {
    String result = "Scoreboard: \n";
    for(Map.Entry<Player, Integer> entry : scores.entrySet()) {
      result += entry.getKey().getName() + ": " + entry.getValue() + "\n";
    }
    return result;
  }
  
  public Player getWinner() {
    int maxScore = 0;
    Player winner = null;
    for(Map.Entry<Player, Integer> entry : scores.entrySet()) {
      if (entry.getValue() > maxScore) {
        maxScore = entry.getValue();
        winner = entry.getKey();
      }
    }
    return winner;
  }

}
