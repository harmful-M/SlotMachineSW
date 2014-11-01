package ru.vsu.csf.starkina;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.vsu.csf.starkina.screens.GameScreen;
import ru.vsu.csf.starkina.screens.MainMenuScreen;

public class SlotMachine extends Game {

    public static final int WIDTH = 830;
    public static final int HEIGHT = 524;

	@Override
	public void create () {
        setScreen(new GameScreen(this));
	}
}
