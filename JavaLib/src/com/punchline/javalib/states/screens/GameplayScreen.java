package com.punchline.javalib.states.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.punchline.javalib.BaseGame;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.states.GameScreen;

/**
 * The screen where actual gameplay takes place.
 * @author Nathaniel
 * @created Jul 24, 2013
 */
public abstract class GameplayScreen extends GameScreen {

	/**
	 * The EntityWorld that runs gameplay.
	 */
	protected EntityWorld world;
	
	/**
	 * The camera that draws the EntityWorld.
	 */
	protected OrthographicCamera camera;
	
	/**
	 * Constructs a GameplayScreen.
	 * @param game The game that owns this screen.
	 */
	public GameplayScreen(BaseGame game) {
		super(game);
		
		camera = new OrthographicCamera();
		resizeCamera();
		
		initializeWorld();
	}

	/**
	 * Initializes the EntityWorld.
	 */
	protected abstract void initializeWorld();
	
	private void resizeCamera() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera.setToOrtho(false, w, h);
	}
	
	@Override
	public void render(float delta) {
		world.process();
	}

	@Override
	public void resize(int width, int height) {
		resizeCamera();
	}

	@Override
	public void show() { }

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		world.dispose();
	}

}
