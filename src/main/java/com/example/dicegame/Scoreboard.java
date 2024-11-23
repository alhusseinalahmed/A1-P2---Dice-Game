package com.example.dicegame;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
  private final Map<String, Integer> scores = new HashMap<>();

  public int getScore(String name) {
    return scores.getOrDefault(name, 0);
  }
  
}
