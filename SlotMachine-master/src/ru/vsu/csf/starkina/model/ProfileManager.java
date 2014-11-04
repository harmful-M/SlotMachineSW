package ru.vsu.csf.starkina.model;

import java.util.ArrayList;

public class ProfileManager {

    //region Singleton
    private ProfileManager() {

    }

    private static ProfileManager instance;

    public static ProfileManager getInstance() {
        if (instance == null)
            instance = new ProfileManager();

        return instance;
    }
    //endregion

    private ArrayList<Player> players;

    /**
     * Метод для загрузки игроков из файла в массив игроков.
     * @param path Путь к файлу
     */
    public void loadUsersFromFile(String path) {

    }

    private void setCurrentPlayer(int index) {
        Game.getInstance().setCurrentPlayer(players.get(index));
    }

    public void deletePlayer(int index) {

    }

    public void saveUserProgress() {

    }
}
