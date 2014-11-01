package ru.vsu.csf.starkina.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.vsu.csf.starkina.SlotMachine;

public class MainMenuScreen extends AbstractGameScreen {

    public static final float MARGIN_BOTTOM = 150;
    public static final float MARGIN = 70;

    Sprite newGameBtn;
    Sprite exitBtn;

    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        newGameBtn = new Sprite(new Texture(Gdx.files.internal("graphics/newGame.png")));
        newGameBtn.setPosition(SlotMachine.WIDTH / 2 - newGameBtn.getWidth() / 2, MARGIN_BOTTOM + MARGIN);
        exitBtn = new Sprite(new Texture(Gdx.files.internal("graphics/exit.png")));
        exitBtn.setPosition(SlotMachine.WIDTH / 2 - exitBtn.getWidth() / 2, MARGIN_BOTTOM );

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = SlotMachine.HEIGHT - screenY;

                if (newGameBtn.getBoundingRectangle().contains(screenX, screenY)) {
                    game.setScreen(new GameScreen(game));
                    return  true;
                }

                if (exitBtn.getBoundingRectangle().contains(screenX, screenY)) {
                    Gdx.app.exit();
                }

                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();
        newGameBtn.draw(batch);
        exitBtn.draw(batch);
        batch.end();
    }
}
