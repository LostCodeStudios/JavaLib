package com.punchline.javalib.entities.components.render;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.ComponentManager;
import com.punchline.javalib.utils.SpriteSheet;

/**
 * A sprite that contains multiple Animations, each mapped to a String showing
 * the state which that animation represents. (Jumping, running, etc.)
 * 
 * @author Natman64
 * 
 */
public class AnimatedSprite implements Renderable {

	// region Fields

	private Map<String, Animation> animations = new HashMap<String, Animation>();
	private String state;

	private float stateTime = 0f;

	private Vector2 position;
	private float scaleX = 1f;
	private float scaleY = 1f;
	private Vector2 origin;
	private float rotation = 0f;
	private int layer = 0;

	private boolean pause = false;

	// endregion

	// region Initialization

	/**
	 * Constructs an AnimatedSprite.
	 * 
	 * @param spriteSheet
	 *            The game's SpriteSheet.
	 * @param prefix
	 *            The key prefix of the AnimatedSprite's separate Animations.
	 * @param frameWidth
	 *            The width of each frame.
	 * @param frameDuration
	 *            The duration of each frame.
	 * @param playType
	 *            How the Animations should animate.
	 */
	public AnimatedSprite(SpriteSheet spriteSheet, String prefix,
			int frameWidth, int playType, float frameDuration) {

		this(spriteSheet, prefix, frameWidth, 0, 0, playType, frameDuration);

	}

	/**
	 * Constructs an AnimatedSprite.
	 * 
	 * @param spriteSheet
	 *            The game's SpriteSheet.
	 * @param prefix
	 *            The key prefix of the AnimatedSprite's separate Animations.
	 * @param frameWidth
	 *            The width of each frame.
	 * @param xPadding
	 *            The number of pixels separating each frame column.
	 * @param yPadding
	 *            The number of pixels separating each frame row.
	 * @param frameDuration
	 *            The duration of each frame.
	 * @param playType
	 *            How the Animations should animate.
	 */
	public AnimatedSprite(SpriteSheet spriteSheet, String prefix,
			int frameWidth, int xPadding, int yPadding, int playType,
			float frameDuration) {

		Map<String, TextureRegion> regions = spriteSheet.getRegions(prefix);

		for (String key : regions.keySet()) {
			int frameCols = regions.get(key).getRegionWidth() / frameWidth;

			Animation animation = new Animation(spriteSheet, prefix + key,
					frameCols, 1, xPadding, yPadding, playType, frameDuration);

			this.animations.put(key, animation);
			setState(key, true);
		}

		position = Vector2.Zero.cpy();
		setOrigin(new Vector2(getWidth() / 2, getHeight() / 2));

	}

	/**
	 * Constructs an AnimatedSprite.
	 * 
	 * @param keys
	 *            The animation keys.
	 * @param animations
	 *            The animation values. NOTE: This must have the same size as
	 *            the keys array.
	 * @param initialState
	 *            The key of the AnimatedSprite's initial state.
	 */
	public AnimatedSprite(String[] keys, Animation[] animations,
			String initialState) {

		for (int i = 0; i < keys.length; i++) {
			if (i < animations.length) {
				this.animations.put(keys[i], animations[i]);
			}
		}

		setState(initialState, true);
		setOrigin(new Vector2(getWidth() / 2, getHeight() / 2));

	}

	// endregion

	// region Events

	@Override
	public void onAdd(ComponentManager container) {
	}

	@Override
	public void onRemove(ComponentManager container) {
	}

	// endregion

	// region Accessors

	public boolean isPaused() {
		return pause;
	}

	@Override
	public float getWidth() {
		TextureRegion region = getCurrentFrame(0f);
		return region.getRegionWidth();
	}

	@Override
	public float getHeight() {
		TextureRegion region = getCurrentFrame(0f);
		return region.getRegionHeight();
	}

	@Override
	public Vector2 getPosition() {
		TextureRegion region = getCurrentFrame(0f);
		return position.cpy().add(
				new Vector2(region.getRegionWidth() / 2, region
						.getRegionHeight() / 2));
	}
	
	@Override
	public Vector2 getOrigin() {
		return origin.cpy();
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	/**
	 * @return The current animation state key.
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            An animation key.
	 * @return Whether this AnimatedSprite contains an Animation with that key.
	 */
	public boolean hasState(String state) {
		return animations.containsKey(state);
	}

	/**
	 * @param deltaSeconds
	 *            The amount of seconds since this was last called.
	 * @return The current frame that needs to be drawn.
	 */
	public TextureRegion getCurrentFrame(float deltaSeconds) {
		Animation currentAnimation = animations.get(state);

		if (!pause)
			currentAnimation.setStateTime(stateTime += deltaSeconds);
		return currentAnimation.getCurrentFrame(0f); // Delta is already
														// accounted for.
	}

	// endregion

	// region Mutators

	/**
	 * Pauses the animation and ends looping.
	 */
	public void pause() {
		pause = true;
	}

	/**
	 * Resumes the animation and starts looping.
	 */
	public void resume() {
		pause = false;
	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position.cpy().sub(origin);
	}

	@Override
	public void setRotation(float degrees) {
		this.rotation = degrees;
	}

	@Override
	public void setScale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	@Override
	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	@Override
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/**
	 * Sets the current state key to the given key, as long as there is a
	 * corresponding animation.
	 * 
	 * @param state
	 *            The key to set.
	 * @param keepStateTime
	 *            Whether the state time should be reset to 0.
	 */
	public void setState(String state, boolean keepStateTime)
			throws IllegalArgumentException {
		if (animations.containsKey(state)) {
			this.state = state;

			if (!keepStateTime)
				stateTime = 0f;
		} else {
			throw new IllegalArgumentException(
					"The animations map does not contain the specified key.");
		}
	}

	// endregion

	// region Rendering

	@Override
	public void draw(SpriteBatch spriteBatch, float deltaSeconds) {
		TextureRegion region = getCurrentFrame(deltaSeconds);
		spriteBatch.draw(region, position.x, position.y, origin.x, origin.y,
				region.getRegionWidth(), region.getRegionHeight(), scaleX,
				scaleY, 0f);
	}

	// endregion
}
