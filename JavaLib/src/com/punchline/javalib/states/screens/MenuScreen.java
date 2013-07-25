package com.punchline.javalib.states.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.punchline.javalib.BaseGame;
import com.punchline.javalib.states.GameScreen;

/**
 * A GameScreen containing a menu composed of 
 * @author Nathaniel
 * @created Jul 24, 2013
 */
public abstract class MenuScreen extends GameScreen {
	
	/**
	 * The menu's skin.
	 */
	protected Skin skin;
	
	/**
	 * The menu's stage.
	 */
	private Stage stage;
	
	/**
	 * The window containing menu elements.
	 */
	protected Window window;
	
	/**
	 * The title of the menu.
	 */
	protected String title;
	
	private Drawable background;
	
	/**
	 * Makes a MenuScreen.
	 * @param game The game containing this screen.
	 * @param skinHandle A FileHandle pointing to the UI skin.
	 * @param title The title of the menu.
	 * @param textHandle A FileHandle pointing to the background texture. Null if no background is needed.
	 */
	public MenuScreen(BaseGame game, FileHandle skinHandle, String title, FileHandle textureHandle) {
		super(game);
		
		skin = new Skin(skinHandle);
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		this.title = title;
		
		if (textureHandle != null) {
			Texture backgroundTexture = new Texture(textureHandle);
			TextureRegion source = new TextureRegion(backgroundTexture);
			background = new TextureRegionDrawable(source);
		}
		
		initialize();
	}
	
	/**
	 * Initializes this MenuScreen's table with all necessary elements.
	 */
	protected void initialize() { 
		window = new Window(title, skin);
		window.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		window.setPosition(0, 0);
		
		if (background != null)
			window.setBackground(background);
		
		stage.addActor(window);
	}
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
	
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(game.getInput());
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
