package com.punchline.javalib.states;

import com.badlogic.gdx.Screen;
import com.punchline.javalib.Game;

/**
 * Base class for GameScreens.
 * 
 * @author Natman64
 * @created Jul 30, 2013
 */
public abstract class GameScreen implements Screen {

	// region Fields

	private ScreenManager manager;

	/**
	 * The game that contains this screen.
	 */
	protected Game game;

	// endregion

	// region Initialization

	/**
	 * Constructs a GameScreen.
	 * 
	 * @param game
	 *            The game containing the screen.
	 */
	public GameScreen(Game game) {
		this.game = game;

		manager = game.getScreenManager();
	}

	// endregion

	// region Methods

	/**
	 * Exits this screen, if it is the ScreenManager's active screen.
	 */
	public void exit() {
		if (this == game.getScreenManager().getActiveScreen())
			manager.closeActiveScreen();
	}

	// endregion

}
