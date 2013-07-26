package com.punchline.javalib.states.screens.generic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.punchline.javalib.BaseGame;
import com.punchline.javalib.states.screens.MenuScreen;

/**
 * Base class for a generic Settings screen.
 * @author Nathaniel
 * @created Jul 26, 2013
 */
public class SettingsScreen extends MenuScreen {

	/**
	 * The name of the Settings preference.
	 */
	public static final String SETTINGS_PREF_NAME = "settings";
	
	private CheckBox sounds;
	private CheckBox music;
	
	/**
	 * Constructs a SettingsScreen.
	 * @param game The game.
	 * @param skinHandle FileHandle pointing to the menu's skin.
	 * @param title The title of the menu.
	 * @param textureHandle FileHandle pointing to the menu's background texture.
	 */
	public SettingsScreen(BaseGame game, FileHandle skinHandle, String title, FileHandle textureHandle) {
		super(game, skinHandle, title, textureHandle);
	}

	@Override
	protected void initialize() {
		super.initialize();
		
		sounds = new CheckBox(" Sound Effects", skin);
		music = new CheckBox(" Music ", skin);
		
		ClickListener listener = new ClickListener() {
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.readSettings();
			}	
			
		};
		
		sounds.addListener(listener);
		music.addListener(listener);
		
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(game.getMainMenu());
			}	
			
		});
		
		window.add(sounds);
		window.row();
		window.add(music);
		window.row();
		window.add(backButton);
	}

	@Override
	public void show() {
		readSettings();
		
		super.show();
	}

	@Override
	public void hide() {
		writeSettings();
		
		super.hide();
	}
	
	protected void readSettings() {
		
		Preferences prefs = Gdx.app.getPreferences(SETTINGS_PREF_NAME);
		
		boolean soundVol = prefs.getBoolean("Sound", true);
		boolean musicVol = prefs.getBoolean("Music", true);
		
		sounds.setChecked(soundVol); 
		music.setChecked(musicVol);
		
	}
	
	protected void writeSettings() {
		
		Preferences prefs = Gdx.app.getPreferences(SETTINGS_PREF_NAME);
		
		prefs.putBoolean("Sound", sounds.isChecked());
		prefs.putBoolean("Music", music.isChecked());
		
		prefs.flush();
		
	}
	
}
