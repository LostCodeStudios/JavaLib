package com.punchline.javalib.states.screens.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.punchline.javalib.BaseGame;
import com.punchline.javalib.states.screens.MenuScreen;

/**
 * Base class for a generic Main Menu screen.
 * @author Nathaniel
 * @created Jul 26, 2013
 */
public class MainMenuScreen extends MenuScreen {

	/**
	 * Constructs a MainMenuScreen.
	 * @param game The game.
	 * @param skinHandle FileHandle pointing to the menu's skin.
	 * @param title The title of the menu.
	 * @param textureHandle FileHandle pointing to the menu's background texture.
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
		
	}

	/**
	 * Called when the Play Game button is pressed.
	 */
	private void onPlayGamePressed() {
		//game.setScreen(new GameplayScreen(game));
	}
	
	/**
	 * Called when the Settings button is pressed.
	 */
	private void onSettingsPressed() {
		game.setScreen(game.getSettings());
	}
	
	/**
	 * Called when the Quit button is pressed.
	 */
	private void onQuitPressed() {
		Gdx.app.exit();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	

}
