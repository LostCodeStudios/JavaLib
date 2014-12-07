package com.lostcode.javalib;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lostcode.javalib.states.ScreenManager;
import com.lostcode.javalib.utils.Display;
import com.lostcode.javalib.utils.SoundManager;

/**
 * Base class for JavaLib games.
 * 
 * @author Natman64
 * @created Jul 23, 2013
 */
public abstract class Game implements ApplicationListener {

	// region Game Specifications

	/**
	 * The title that will appear on the game's active window.
	 */
	protected String title = "JavaLib Game";

	/**
	 * Whether the game should take up the entire monitor.
	 */
	protected boolean fullScreen = false;

	/**
	 * The texture that will be drawn as the game's cursor. The sprite must take
	 * up the entire texture. If null, the default system mouse will be drawn.
	 * 
	 * @deprecated The horrible cursor system has been removed for the time
	 *             being because it sucked.
	 */
	@Deprecated
	protected Texture cursorTexture;

	/**
	 * The game window's width, in pixels.
	 */
	protected int width = 480;

	/**
	 * The game window's height, in pixels.
	 */
	protected int height = 800;

	/**
	 * Whether the game is designed to be played in a phone's landscape mode.
	 */
	protected boolean landscapeMode = false;

	/**
	 * The red component of the game's background color.
	 */
	protected float backgroundRed = 0f;

	/**
	 * The green component of the game's background color.
	 */
	protected float backgroundGreen = 0f;

	/**
	 * The blue component of the game's background color.
	 */
	protected float backgroundBlue = 0f;

	// endregion

	// region Fields

	private ScreenManager screenManager;

	/**
	 * The game's {@link SpriteBatch}.
	 */
	protected SpriteBatch spriteBatch;

	/**
	 * The game's {@link InputMultiplexer}.
	 */
	protected InputMultiplexer input;

	// endregion

	// region Initialization

	/**
	 * Initializes the game's window.
	 */
	@Override
	public void create() {

		Gdx.graphics.setTitle(title);

		int w = landscapeMode ? height : width;
		int h = landscapeMode ? width : height;

		Gdx.graphics.setDisplayMode(w, h, fullScreen);
		Display.init(w, h); // Initialize the display helper

		input = new InputMultiplexer();
		Gdx.input.setInputProcessor(input);
		Gdx.input.setCatchBackKey(true);

		spriteBatch = new SpriteBatch();

		loadSounds();

		screenManager = new ScreenManager();
		screenManager.create();

	}

	@Override
	public void resize(int width, int height) {
		Gdx.gl20.glViewport(0, 0, width, height);

		screenManager.resize(width, height);
	}

	/**
	 * Loads all game sounds into the SoundManager's memory.
	 */
	protected abstract void loadSounds();

	// endregion

	// region Disposal

	/**
	 * Disposes all of this game's assets, including those that are still in the
	 * SoundManager's memory.
	 */
	@Override
	public void dispose() {
		screenManager.dispose();
		SoundManager.dispose();
		spriteBatch.dispose();
	}

	// endregion

	// region Accessors

	/**
	 * @return The game's {@link ScreenManager}.
	 */
	public ScreenManager getScreenManager() {
		return screenManager;
	}

	/**
	 * @return The game's {@link InputMultiplexer}.
	 */
	public InputMultiplexer getInput() {
		return input;
	}

	// endregion

	// region Rendering

	/**
	 * Renders a solid background, and the current screen above it.
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(backgroundRed, backgroundGreen, backgroundBlue, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		screenManager.render();
	}

	@Override
	public void pause() {
		screenManager.pause();
	}

	@Override
	public void resume() {
		screenManager.resume();
	}

	// endregion

}
