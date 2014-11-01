package ru.vsu.csf.starkina.model;


import ru.vsu.csf.starkina.model.machine.Machine;

public class Game {
    //region Singleton
    private Game() {
        machine = new Machine();
        inGame = false;
    }

    private static Game instance;

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();

        return instance;
    }
    //endregion

    private Player currentPlayer;
    private Machine machine;

    private boolean inGame;

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public Machine getMachine() {
        return machine;
    }

    public void roll() {
        currentPlayer.subCoins();
        machine.roll();

        int p = machine.getProfit();

        if (p > 0) {
            currentPlayer.addScore(p);
            currentPlayer.addCoins(p);


        }
        else if (currentPlayer.getCoins() <= 0) {
            inGame = false;
        }
    }

    public void startNewGame() {
        currentPlayer = new Player();
        inGame = true;
    }

    public Player getPlayer() {
        return currentPlayer;
    }
}
