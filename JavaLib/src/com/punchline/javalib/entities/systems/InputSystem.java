package com.punchline.javalib.entities.systems;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntitySystem;

/**
 * Base class for any {@link EntitySystem} that can take user input.
 * @author Nathaniel
 * @created Jul 24, 2013
 */
public abstract class InputSystem extends EntitySystem implements InputProcessor {

	private InputMultiplexer input;
	
	//region Initialization/Disposal
	
	/**
	 * Constructs an InputSystem.
	 * @param input The game's {@link InputMultiplexer}.
	 */
	public InputSystem(InputMultiplexer input) {
		this.input = input;
		input.addProcessor(this);
	}
	
	/**
	 * Tells the game's {@link InputMultiplexer} to stop processing this
	 * system's input.
	 */
	@Override
	public void dispose() {
		input.removeProcessor(this);
	}
	
	//endregion
	
	@Override
	protected void process(Entity e) {
		//Empty so all InputSystems don't have to define it
	}

	//region Input Events
	
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

	//endregion
	
}
