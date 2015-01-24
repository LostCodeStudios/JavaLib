package com.lostcode.javalib.states.screens;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lostcode.javalib.Game;
import com.lostcode.javalib.states.GameScreen;
import com.lostcode.javalib.states.InputScreen;
import com.lostcode.javalib.utils.Display;

/**
 * A splash screen that fades in and out.
 * 
 * @author Natman64
 * @created Jul 23, 2013
 */
public final class SplashScreen extends InputScreen {

	/**
	 * Represents the SplashScreen's current state of transition.
	 * 
	 * @author Nathaniel
	 * @created Jul 23, 2013
	 */
	private enum Transition {
		TransitionOn, OnScreen, TransitionOff, OffScreen
	}

	private SpriteBatch spriteBatch;

	private Texture texture;
	private TextureRegion region;

	private Transition transition;
	private float elapsedTime = 0f;
	private float fadeInTime;
	private float onScreenTime;
	private float fadeOutTime;

	private GameScreen nextScreen;

	/**
	 * Makes a splash screen.
	 * 
	 * @param game
	 *            The game that owns this screen.
	 * @param textureHandle
	 *            A FileHandle pointing to the texture. This will be cropped to
	 *            the size of the window.
	 * @param nextScreen
	 *            The screen that will appear after the splash screen is done
	 *            animating.
	 * @param fadeInTime
	 *            The amount of seconds it will take this screen to fade on.
	 * @param onScreenTime
	 *            The amount of seconds this screen will remain fully visible on
	 *            the screen.
	 * @param fadeOutTime
	 *            The amount of seconds it will take this screen to fade off.
	 */
	public SplashScreen(Game game, FileHandle textureHandle,
			GameScreen nextScreen, float fadeInTime, float onScreenTime,
			float fadeOutTime) {
		super(game);

		spriteBatch = new SpriteBatch();

		texture = new Texture(textureHandle);
		region = new TextureRegion(texture, Display.getPreferredWidth(),
				Display.getPreferredHeight());

		this.nextScreen = nextScreen;

		transition = Transition.TransitionOn;
		this.fadeInTime = fadeInTime;
		this.onScreenTime = onScreenTime;
		this.fadeOutTime = fadeOutTime;
	}

	@Override
	public void render(float delta) {

		elapsedTime += delta;

		float alpha = 0f;

		switch (transition) {

		case TransitionOn:

			alpha = elapsedTime / fadeInTime;

			if (elapsedTime >= fadeInTime) {
				elapsedTime = 0f;
				transition = Transition.OnScreen;
			}

			break;

		case OnScreen:

			alpha = 1f;

			if (elapsedTime >= onScreenTime) {
				elapsedTime = 0f;
				transition = Transition.TransitionOff;
			}

			break;

		case TransitionOff:

			alpha = (fadeOutTime - elapsedTime) / fadeOutTime;

			if (elapsedTime >= fadeOutTime) {
				elapsedTime = 0f;
				transition = Transition.OffScreen;
			}

			break;

		case OffScreen:

			dismiss();
			return;

		}

		spriteBatch.setColor(new Color(1, 1, 1, alpha));
		spriteBatch.begin();
		spriteBatch.draw(region, 0, 0, Display.getRealWidth(),
				Display.getRealHeight());
		spriteBatch.end();

	}

	@Override
	public boolean keyDown(int keycode) {
		dismiss();
		return true;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		dismiss();
		return true;
	}

	private void dismiss() {
		exit();
		game.getScreenManager().addScreen(nextScreen);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
		texture.dispose();
		spriteBatch.dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

}
