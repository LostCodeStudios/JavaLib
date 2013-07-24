package com.punchline.javalib;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.punchline.javalib.utils.SoundManager;

/**
 * Base class for JavaLib games.
 * @author Nathaniel
 * @created Jul 23, 2013
 */
public abstract class BaseGame extends Game {

	/**
	 * The title that will appear on the game's active window.
	 */
	protected String title = "JavaLib Game";
	
	/**
	 * Whether the game should take up the entire monitor.
	 */
	protected boolean fullScreen = false;
	
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
	
	/**
	 * Initializes the game's window.
	 */
	@Override
	public void create() {
		
		Gdx.graphics.setTitle(title);
		
		int w = landscapeMode ? height : width;
		int h = landscapeMode ? width : height;
		
		Gdx.graphics.setDisplayMode(w, h, fullScreen);
		
		loadSounds();
		
	}
	
	/**
	 * Loads all game sounds into the SoundManager's memory.
	 */
	protected abstract void loadSounds();
	
	/**
	 * Disposes all of this game's assets, including those that are still in the SoundManager's memory.
	 */
	@Override
	public void dispose() {
		super.dispose();
		
		getScreen().dispose();
		SoundManager.dispose();
	}
	
	/**
	 * Renders a solid background, and the current screen above it.
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(backgroundRed, backgroundGreen, backgroundBlue, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	
	/**
	 * {@inheritDoc}
	 * Disposes of the previous screen.
	 */
	@Override
	public void setScreen(Screen screen) {
		Screen oldScreen = getScreen();
		super.setScreen(screen);
		if (oldScreen != null) oldScreen.dispose();
	}
	
}
