package com.example.dicegame;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    DiceGameApp app = new DiceGameApp();
    app.startGame(new Scanner(System.in));
  }
}