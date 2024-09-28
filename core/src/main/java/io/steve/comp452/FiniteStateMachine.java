package io.steve.comp452;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class FiniteStateMachine extends Game {

    StartScreen startScreen;

    public FiniteStateMachine() {
        super();
    }

    @Override
    public void create() {
        startScreen = new StartScreen(this);
        setScreen(startScreen);
    }

    @Override
    public void dispose() {
        super.dispose();
        startScreen.dispose();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        startScreen.resize(width, height);
    }
}
