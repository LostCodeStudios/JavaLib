package com.lostcode.javalib.states.screens;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.states.InputScreen;
import com.lostcode.javalib.utils.Display;

/**
 * A GameScreen containing a Scene2D stage for a menu.
 * 
 * @author Natman64
 * @created Jul 24, 2013
 * @deprecated Dec 2, 2013 
 * JavaLib will soon implement its own menu screens that do not use Scene2D.
 */
public abstract class MenuScreen extends InputScreen {

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
	 * 
	 * @param game
	 *            The game containing this screen.
	 * @param skinHandle
	 *            A FileHandle pointing to the UI skin.
	 * @param title
	 *            The title of the menu.
	 * @param textHandle
	 *            A FileHandle pointing to the background texture. Null if no
	 *            background is needed.
	 */
	public MenuScreen(Game game, FileHandle skinHandle, String title,
			FileHandle textureHandle) {
		super(game);

		skin = new Skin(skinHandle);
		stage = new Stage(Display.getPreferredWidth(),
				Display.getPreferredHeight(), false);

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
		window.setSize(Display.getPreferredWidth(),
				Display.getPreferredHeight());
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
		game.getInput().addProcessor(stage);
		super.show();
	}

	@Override
	public void hide() {
		game.getInput().removeProcessor(stage);
		super.hide();
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
