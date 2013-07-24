package com.punchline.javalib.states;

import com.badlogic.gdx.Screen;
import com.punchline.javalib.BaseGame;

/**
 * Base class for all game screens.
 * @author Nathaniel
 * @created Jul 23, 2013
 */
public abstract class GameScreen implements Screen {

	/**
	 * This screen's parent game.
	 */
	protected BaseGame game;
	
	/**
	 * Constructs a GameScreen.
	 * @param game This screen's parent Game.
	 */
	public GameScreen(BaseGame game) {
		this.game = game;
	}

}
