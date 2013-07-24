package com.punchline.javalib.states.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.punchline.javalib.BaseGame;

/**
 * Base class for a game's Main Menu screen.
 * @author Nathaniel
 * @created Jul 24, 2013
 */
public abstract class MainMenuScreen extends MenuScreen {
	
	/**
	 * Makes a MainMenuScreen.
	 * @param game The game.
	 * @param skinHandle A FileHandle pointing to the menu's skin.
	 * @param textureHandle A FileHandle pointing to the background texture.
	 */
	public MainMenuScreen(BaseGame game, FileHandle skinHandle, String title, FileHandle textureHandle) {
		super(game, skinHandle, title, textureHandle);
	}

	@Override
	protected void initialize() {
		
		super.initialize();
		
		Button playButton = new TextButton("Play Game", skin);
		Button settingsButton = new TextButton("Settings", skin);
		Button quitButton = new TextButton("Quit", skin);
		
		//TODO: Add buttons events.
		playButton.addListener(new ClickListener() {
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				onPlayGamePressed();
			}
			
		});
		
		settingsButton.addListener(new ClickListener() {
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				onSettingsPressed();
			}
			
		});
		
		quitButton.addListener(new ClickListener() {
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				
				onQuitPressed();
			}
			
		});
		
		window.add(playButton);
		window.row();
		window.add(settingsButton);
		window.row();
		window.add(quitButton);
		window.row();
		
	}
	
	/**
	 * Called when the Play Game button is pressed.
	 */
	protected abstract void onPlayGamePressed();
	
	/**
	 * Called when the Settings button is pressed.
	 */
	protected abstract void onSettingsPressed();
	
	/**
	 * Called when the Quit button is pressed.
	 */
	protected void onQuitPressed() {
		Gdx.app.exit();
	}
	
}
