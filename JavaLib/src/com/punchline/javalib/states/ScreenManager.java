package com.punchline.javalib.states;

import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

/**
 * Manages the game's GameScreens, and disposes of them when they are no longer
 * needed by the game.
 * @author Natman64
 *
 */
public final class ScreenManager implements ApplicationListener {
	
	//region Fields/Disposal
	
	private Stack<GameScreen> screens = new Stack<GameScreen>();
	
	@Override
	public void dispose() {		
		 for (GameScreen screen : screens) {
			screen.dispose();
		}
	}
	
	//endregion
	
	//region Accessors
	
	/**
	 * @return The ScreenManager's active screen.
	 */
	public GameScreen getActiveScreen() {
		if (screens.size() > 0) return screens.peek();
		else return null;
	}
	
	//endregion
	
	//region Screen Management
	
	/**
	 * Adds a screen to the ScreenManager. This screen will become the active screen.
	 * @param screen The screen to add.
	 */
	public void addScreen(GameScreen screen) {
		GameScreen oldScreen = getActiveScreen();
		if (oldScreen != null) oldScreen.hide();
		
		screens.add(screen);
		
		screen.show();
		screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	/**
	 * Closes the active screen. The next screen from the stack will become the
	 * new active screen.
	 */
	public void closeActiveScreen() {
		GameScreen active = screens.pop();
		active.hide();
		active.dispose();
		
		if (screens.size() > 0) {
			active = screens.peek();
			active.show();
		}
	}
	
	//endregion

	//region ApplicationListener Events
	
	@Override
	public void create() { }

	@Override
	public void resize(int width, int height) { 
		getActiveScreen().resize(width, height);
	}

	@Override
	public void render() {
		getActiveScreen().render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {
		getActiveScreen().pause();
	}

	@Override
	public void resume() {
		getActiveScreen().resume();
	}
	
	//endregion
	
}
