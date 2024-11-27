package com.example.dicegame;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
  private final Map<String, Integer> scores = new HashMap<>();

  public int getScore(String name) {
    return scores.getOrDefault(name, 0);
  }

  public void updateScore(String name, int score) {
    scores.put(name, score);
  }

  public Map<String, Integer> getAllScores() {
    return new HashMap<>(scores);
  }

  public String toString() {
    String result = "Scoreboard: \n";
    for(Map.Entry<String, Integer> entry : scores.entrySet()) {
      result += entry.getKey() + ": " + entry.getValue() + "\n";
    }
    return result;
  }

}
