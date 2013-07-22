package com.punchline.javalib.entities.components.render;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * A sprite that contains multiple Animations, each mapped to a String showing the state which that animation represents.
 * (Jumping, running, etc.)
 * @author Nathaniel
 *
 */
public class AnimatedSprite extends Renderable {

	private Map<String, Animation> animations;
	private String state;
	
	private float stateTime = 0f;
	
	private Vector2 position;
	private float rotation;
	private float scale;
	private Vector2 origin;
	
	/**
	 * Constructs an AnimatedSprite.
	 * @param keys The animation keys.
	 * @param animations The animation values. NOTE: This must have the same size as the keys array.
	 * @param frameWidth The width of each frame.
	 * @param frameHeight The height of each frame.
	 * @param initialState The key of the AnimatedSprite's initial state.
	 */
	public AnimatedSprite(String[] keys, Animation[] animations, int frameWidth, int frameHeight, String initialState) {
		
		this.animations = new HashMap<String, Animation>();
		
		for (int i = 0; i < keys.length; i++) {
			if (i < animations.length) {
				this.animations.put(keys[i], animations[i]);
			}
		}
		
		setState(initialState, true);
		
	}
	
	/**
	 * Sets the current state key to the given key, as long as there is a corresponding animation.
	 * @param state The key to set.
	 * @param keepStateTime Whether the state time should be reset to 0.
	 */
	public void setState(String state, boolean keepStateTime) throws IllegalArgumentException {
		if (animations.containsKey(state)) {
			this.state = state;
			
			if (!keepStateTime) stateTime = 0f;
		} else {
			throw new IllegalArgumentException("The animations map does not contain the specified key.");
		}
	}
	
	/**
	 * @param deltaSeconds The amount of seconds since this was last called.
	 * @return The current frame that needs to be drawn.
	 */
	public TextureRegion getCurrentFrame(float deltaSeconds) {
		Animation currentAnimation = animations.get(state);
		
		currentAnimation.setStateTime(stateTime += deltaSeconds);
		return currentAnimation.getCurrentFrame(0f); //Delta is already accounted for.
	}

	@Override
	public void setPosition(Vector2 position) {
		this.position = position;
	}

	@Override
	public void setRotation(float degrees) {
		this.rotation = degrees;
	}

	@Override
	public void setScale(float scale) {
		this.scale = scale;
	}

	@Override
	public void setOrigin(Vector2 origin) {
		this.origin = origin;
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float deltaSeconds) {
		TextureRegion region = getCurrentFrame(deltaSeconds);
		spriteBatch.draw(region, position.x, position.y, origin.x, origin.y, 
				region.getRegionWidth(), region.getRegionHeight(), scale, scale, rotation);
	}
	
}
