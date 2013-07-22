package com.punchline.javalib.entities.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.punchline.javalib.entities.Component;

/**
 * Component wrapper for a LibGDX {@link com.badlogic.gdx.graphics.g2d.Animation Animation}.
 * @author Nathaniel
 *
 */
public class Animation extends Component {

	private com.badlogic.gdx.graphics.g2d.Animation animation;
	private float stateTime = 0f;
	private boolean looping = true;
	
	/**
	 * Constructs an Animation.
	 * @param texture The Animation's texture.
	 * @param animationRegion The region on the texture which contains the complete animation.
	 * @param frameWidth The width of each animation frame.
	 * @param frameHeight The height of each animation frame.
	 * @param frameCols The amount of columns in the animation.
	 * @param frameRows The amount of rows in the animation.
	 * @param frameDuration The duration of each frame, in seconds.
	 */
	public Animation(Texture texture, TextureRegion animationRegion, int frameWidth, int frameHeight, int frameCols, int frameRows, float frameDuration) {
		
		TextureRegion[] regions = new TextureRegion[frameCols * frameRows];
		
		int i = 0;
		
		for (int y = 0; y < frameRows; y++) {
			
			int yCoord = animationRegion.getRegionY() + y * frameHeight;
			
			for (int x = 0; x < frameCols; x++) {
				
				int xCoord = animationRegion.getRegionX() + x * frameWidth;
				
				TextureRegion region = new TextureRegion(texture, xCoord, yCoord, frameWidth, frameHeight);
				
				regions[i++] = region;
				
			}
		}
		
		animation = new com.badlogic.gdx.graphics.g2d.Animation(frameDuration, regions);
		
	}
	
	/**
	 * Constructs an Animation.
	 * @param texture The Animation's texture.
	 * @param frameCols The amount of columns in the animation.
	 * @param frameRows The amount of rows in the animation.
	 * @param frameDuration The duration of each frame, in seconds.
	 */
	public Animation(Texture texture, int frameCols, int frameRows, float frameDuration) {
		
		TextureRegion[] regions = new TextureRegion[frameCols * frameRows];
		
		TextureRegion[][] tmp = new TextureRegion[frameRows][frameCols];
		
		tmp = TextureRegion.split(texture, texture.getWidth() / frameCols, texture.getHeight() / frameRows);
		
		int i = 0;
		
		for (int y = 0; y < frameRows; y++) {
			for (int x = 0; x < frameCols; x++) {
				regions[i++] = tmp[y][x];
			}
		}
		
		animation = new com.badlogic.gdx.graphics.g2d.Animation(frameDuration, regions);
		
	}
	
	/**
	 * Sets how long this Animation has been running.
	 * @param stateTime How long this Animation has been running.
	 */
	public void setStateTime(float stateTime) {
		this.stateTime = stateTime;
	}
	
	/**
	 * Sets whether or not this Animation is looping.
	 * @param looping Whether this Animation is looping.
	 */
	public void setLooping(boolean looping) {
		this.looping = looping;
	}
	
	/**
	 * @param stateTime The total amount of time that this Animation has been running.
	 * @param looping Whether this Animation is looping.
	 * @return The current frame.
	 */
	public TextureRegion getCurrentFrame(float stateTime) {
		return animation.getKeyFrame(stateTime, looping);
	}
	
}
