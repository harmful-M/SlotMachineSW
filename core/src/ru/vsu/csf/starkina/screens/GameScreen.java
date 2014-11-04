package ru.vsu.csf.starkina.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ru.vsu.csf.starkina.SlotMachine;
import ru.vsu.csf.starkina.model.machine.FigureName;

import java.util.ArrayList;
import java.util.HashMap;

public class GameScreen extends AbstractGameScreen {
    ru.vsu.csf.starkina.model.Game game;
    GameScreenState state;

    HashMap<FigureName, TextureRegion> textures;
    TextureRegion bg;
    TextureRegion darkCell;
    TextureRegion theEnd;
    TextureRegion combinations;
    boolean isShowingCombinations;

    final Rectangle leverRectangle = new Rectangle(725, 140, 60, 320);
    final Rectangle newGameBtnRect = new Rectangle(110, 25, 90, 35);
    final Rectangle screenRect = new Rectangle(90, 70, 615, 315-70);
    final Rectangle combinationRect =  new Rectangle(577, 29, 106, 34);

    final BitmapFont font = new BitmapFont() {{ setColor(Color.YELLOW); }};

    final ArrayList<Vector2> screenCellCoords = new ArrayList<Vector2>() {{
        add(new Vector2(131, 149));
        add(new Vector2(281, 149));
        add(new Vector2(431, 149));
    }};

    final Vector2 incomeLabelPosition = new Vector2(637, 294);
    final Vector2 coinsLabelPosition  = new Vector2(637, 274);

    final float CELL_SIZE = 90;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        state = GameScreenState.POWERED_OFF;
        isShowingCombinations = false;

        game = ru.vsu.csf.starkina.model.Game.getInstance();

        textures = new HashMap<FigureName, TextureRegion>();

        textures.put(FigureName.VADER, new TextureRegion(new Texture(Gdx.files.internal("graphics/vady.jpg"))));
        textures.put(FigureName.R2D2,  new TextureRegion(new Texture(Gdx.files.internal("graphics/r2d2.jpg"))));
        textures.put(FigureName.FETT, new TextureRegion(new Texture(Gdx.files.internal("graphics/mr_fett.jpg"))));
        textures.put(FigureName.STORM_TROOPER, new TextureRegion(new Texture(Gdx.files.internal("graphics/fighter.jpg"))));

        bg = new TextureRegion(new Texture(Gdx.files.internal("graphics/bg.jpg")));
        darkCell = new TextureRegion(new Texture(Gdx.files.internal("graphics/darkCell.png")));
        theEnd = new TextureRegion(new Texture(Gdx.files.internal("graphics/end.jpg")));
        combinations = new TextureRegion(new Texture(Gdx.files.internal("graphics/combination.jpg")));

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                screenY = SlotMachine.HEIGHT - screenY;

                if (newGameBtnRect.contains(screenX, screenY)) {
                    game.startNewGame();
                    state = GameScreenState.IN_GAME;
                    isShowingCombinations = false;
                }

                if (combinationRect.contains(screenX, screenY)) {
                    isShowingCombinations = !isShowingCombinations;
                }

                if (state == GameScreenState.IN_GAME && leverRectangle.contains(screenX, screenY)) {
                    game.roll();

                    if (!game.isInGame()) {
                        state = GameScreenState.THE_END;
                    }
                }
                return true;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                screenY = SlotMachine.HEIGHT - screenY;

                Gdx.app.log("1", screenX + "; " + screenY);

                return  true;
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        batch.setColor(1,1,1, 1);
        batch.draw(bg, 0, 0, SlotMachine.WIDTH, SlotMachine.HEIGHT);

        switch (state) {
            case POWERED_OFF:
                batch.setColor(1, 1, 1, 0.7f);
                batch.draw(darkCell, screenRect.x, screenRect.y, screenRect.width, screenRect.height);
                break;
            case IN_GAME:
                for (int i = 0; i < game.getMachine().getScreen().length; i++) {
                    TextureRegion t = textures.get(game.getMachine().getScreen()[i]);
                    batch.draw(t, screenCellCoords.get(i).x, screenCellCoords.get(i).y, CELL_SIZE, CELL_SIZE);
                }
                break;
            case THE_END:
                batch.draw(theEnd, screenRect.x, screenRect.y, screenRect.width, screenRect.height);
                break;
        }

        batch.setColor(1,1,1,1);

        if (isShowingCombinations) {
            batch.draw(combinations, screenRect.x, screenRect.y, screenRect.width, screenRect.height);
        }


        if (game.isInGame() && !isShowingCombinations) {
            font.draw(batch, String.valueOf(game.getMachine().getProfit()), incomeLabelPosition.x, incomeLabelPosition.y);
            font.draw(batch, String.valueOf(game.getPlayer().getCoins()), coinsLabelPosition.x, coinsLabelPosition.y);
        }
        batch.end();
    }
}
