package io.steve.comp452;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartScreen implements Screen {

    Game game;
    Viewport viewport;
    Stage stage;
    Table antColony;

    TextButton oneAnt, twoAnts, threeAnts, fourAnts, fiveAnts, start;

    int numOfAnts;

    StartScreen(Game game){
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport);

        numOfAnts = 0;

        initButtons();

    }

    public void initButtons(){
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();

        oneAnt = new TextButton("1 Ant" , textButtonStyle);
        oneAnt.setName("1 Ant");
        oneAnt.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                numOfAnts = 1;
            }
        });
        twoAnts = new TextButton("2 Ants" , textButtonStyle);
        twoAnts.setName("2 Ants");
        twoAnts.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                numOfAnts = 2;
            }
        });
        threeAnts = new TextButton("3 Ants" , textButtonStyle);
        threeAnts.setName("3 Ants");
        threeAnts.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                numOfAnts = 3;
            }
        });
        fourAnts = new TextButton("4 Ants" , textButtonStyle);
        fourAnts.setName("4 Ants");
        fourAnts.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                numOfAnts = 4;
            }
        });
        fiveAnts = new TextButton("5 Ants" , textButtonStyle);
        fiveAnts.setName("5 Ants");
        fiveAnts.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                numOfAnts = 5;
            }
        });
        start = new TextButton("Start" , textButtonStyle);
        start.setName("start");
        start.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                startGame();
            }
        });

        antColony  = new Table();
        antColony.center();
        antColony.setFillParent(true);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label numOfAntsLabel = new Label("Select the number of ants and click start when ready", font);

        antColony.add(numOfAntsLabel).padBottom(50f);
        antColony.row();
        antColony.add(oneAnt).padBottom(25f);
        antColony.row();
        antColony.add(twoAnts).padBottom(25f);
        antColony.row();
        antColony.add(threeAnts).padBottom(25f);
        antColony.row();
        antColony.add(fourAnts).padBottom(25f);
        antColony.row();
        antColony.add(fiveAnts).padBottom(25f);
        antColony.row();
        antColony.add(start);

        stage.addActor(antColony);
        Gdx.input.setInputProcessor(stage);
    }

    public void startGame(){
        if(numOfAnts == 0)
            return;
        game.setScreen(new GameScreen(game, numOfAnts));
        dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
