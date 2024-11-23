package com.example.dicegame;

public class Player {

  private String name;
  private int score;

  public Player(String name) {
    setName(name);
    setScore(0);
  }
  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public int getScore() {
    return score;
  }

  private void setScore(int score) {
    this.score = score;
  }

  public void addScore(int score) {
    setScore(getScore() + score);
  }
}
