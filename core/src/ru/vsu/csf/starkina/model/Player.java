package ru.vsu.csf.starkina.model;

public class Player {

    private int coins;
    private int score;

    public Player() {
        this.score = 0;
        this.coins = 400;
    }

    public int getCoins() {
        return coins;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void subCoins() {
        this.coins -= 50;
    }

    public void addCoins(int p) {
        this.coins += p;
    }
}