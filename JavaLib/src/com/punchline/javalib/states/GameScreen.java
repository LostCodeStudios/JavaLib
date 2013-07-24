package com.punchline.javalib.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Base class for all game screens.
 * @author Nathaniel
 * @created Jul 23, 2013
 */
public abstract class GameScreen implements Screen {

	/**
	 * This screen's parent game.
	 */
	protected Game game;
	
	/**
	 * Constructs a GameScreen.
	 * @param game This screen's parent Game.
	 */
	public GameScreen(Game game) {
		this.game = game;
	}

}
