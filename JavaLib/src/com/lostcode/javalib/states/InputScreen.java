package com.lostcode.javalib.states;

import com.badlogic.gdx.InputProcessor;
import com.lostcode.javalib.Game;

/**
 * Base class for GameScreens that process input.
 * 
 * @author Natman64
 * @created Jul 24, 2013
 */
public abstract class InputScreen extends GameScreen implements InputProcessor {

	/**
	 * Constructs an InputScreen and adds it to the game's input multiplexor.
	 * 
	 * @param game
	 *            The game.
	 */
	public InputScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		game.getInput().addProcessor(this);
	}

	@Override
	public void hide() {
		game.getInput().removeProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
