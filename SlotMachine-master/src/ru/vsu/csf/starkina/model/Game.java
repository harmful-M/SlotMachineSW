package ru.vsu.csf.starkina.model;

import ru.vsu.csf.starkina.model.machine.Machine;

public class Game {

    //region Singleton
    private Game() {
        machine = new Machine();
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

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String click(int x, int y) {
        if (x > 1 && y > 1) { // проверка, попадает ли указатель мыши в рычажок

            int sum = machine.roll();
            if (sum > 0) {
                return machine.getScreen()[0] + "; " +
                        machine.getScreen()[1] + "; " +
                        machine.getScreen()[2] + "; " + "\nВы выиграли " + sum + " монет!";
            }
            return machine.getScreen()[0] + "; " +
                    machine.getScreen()[1] + "; " +
                    machine.getScreen()[2] + "; " + "\nНеудача.";
        }
        return "нриполпт";
    }
}
