package com.punchline.javalib.states;

import com.badlogic.gdx.Screen;
import com.punchline.javalib.Game;

/**
 * Base class for Game
 * @author Natman64
 * @created Jul 30, 2013
 */
public abstract class GameScreen implements Screen {

	/**
	 * The game that contains this screen.
	 */
	protected Game game;
	
	/**
	 * Constructs a GameScreen.
	 * @param game The game containing the screen.
	 */
	public GameScreen(Game game) {
		this.game = game;
	}

}
