package com.punchline.javalib;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.punchline.javalib.states.screens.generic.MainMenuScreen;
import com.punchline.javalib.states.screens.generic.SettingsScreen;
import com.punchline.javalib.states.screens.generic.SplashScreen;
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
	 * The texture that will be drawn as the game's cursor. If null, the default system mouse will be drawn.
	 */
	protected Texture cursorTexture;
	private boolean useCursor = false;
	
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
	 * The game's SpriteBatch.
	 */
	protected SpriteBatch spriteBatch;
	
	/**
	 * The game's {@link com.badlogic.gdx.InputMultiplexer InputMultiplexer}.
	 */
	protected InputMultiplexer input;
	
	/**
	 * The game's opening splash screen.
	 */
	protected SplashScreen splash;
	
	/**
	 * The game's main menu.
	 */
	protected MainMenuScreen mainMenu;
	
	/**
	 * The game's settings screen.
	 */
	protected SettingsScreen settings;
	
	/**
	 * Initializes the game's window.
	 */
	@Override
	public void create() {
		
		Gdx.graphics.setTitle(title);
		
		int w = landscapeMode ? height : width;
		int h = landscapeMode ? width : height;
		
		Gdx.graphics.setDisplayMode(w, h, fullScreen);
		
		if (cursorTexture != null) {
			useCursor = true;
			Gdx.input.setCursorCatched(true);
		}
		
		input = new InputMultiplexer();
		Gdx.input.setInputProcessor(input);
		
		spriteBatch = new SpriteBatch();
		
		loadSounds();
		
		setScreen(splash);
		
	}
	
	/**
	 * @return The game's InputMultiplexer.
	 */
	public InputMultiplexer getInput() {
		return input;
	}

	/**
	 * @return The game's Main Menu.
	 */
	public MainMenuScreen getMainMenu() {
		return mainMenu;
	}
	
	/**
	 * @return The game's Settings screen.
	 */
	public SettingsScreen getSettings() {
		return settings;
	}
	
	/**
	 * Loads all game sounds into the SoundManager's memory.
	 */
	protected abstract void loadSounds();
	
	/**
	 * Reads and applies all game settings.
	 */
	public void readSettings() {
		Preferences pref = Gdx.app.getPreferences(SettingsScreen.SETTINGS_PREF_NAME);
		
		float soundVol = pref.getBoolean("Sound", true) ? 1f : 0f;
		float musicVol = pref.getBoolean("Music", true) ? 1f : 0f;
		
		SoundManager.setSoundVolume(soundVol);
		SoundManager.setMusicVolume(musicVol);
	}
	
	/**
	 * Disposes all of this game's assets, including those that are still in the SoundManager's memory.
	 */
	@Override
	public void dispose() {
		super.dispose();
		
		splash.dispose();
		mainMenu.dispose();
		settings.dispose();
		
		SoundManager.dispose();
		spriteBatch.dispose();
	}
	
	/**
	 * Renders a solid background, and the current screen above it.
	 */
	@Override
	public void render() {
		Gdx.gl.glClearColor(backgroundRed, backgroundGreen, backgroundBlue, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		super.render();
		
		if (useCursor) {
			//Clamp cursor
			int cursorX = Gdx.input.getX();
			int cursorY = Gdx.input.getY();
			
			boolean cursorChanged = false;
			
			if (cursorX < 0) {
				cursorX = 0;
				cursorChanged = true;
			} else if (cursorX > Gdx.graphics.getWidth() - cursorTexture.getWidth()) {
				cursorX = Gdx.graphics.getWidth() - cursorTexture.getWidth();
				cursorChanged = true;
			}
			
			if (cursorY < 0) {
				cursorY = 0;
				cursorChanged = true;
			} else if (cursorY > Gdx.graphics.getHeight() - cursorTexture.getHeight()) {
				cursorY = Gdx.graphics.getHeight() - cursorTexture.getHeight();
				cursorChanged = true;
			}
			
			spriteBatch.begin();
			spriteBatch.draw(cursorTexture, cursorX, Gdx.graphics.getHeight() - cursorY - cursorTexture.getHeight());
			spriteBatch.end();
			
			if (cursorChanged) {
				Gdx.input.setCursorPosition(cursorX, Gdx.graphics.getHeight() - cursorY);
			}
		}
	}
	
}
