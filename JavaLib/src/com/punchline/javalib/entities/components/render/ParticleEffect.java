/**
 * 
 */
package com.punchline.javalib.entities.components.render;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * The particle effect component for instantiating particle effects.
 * @author William
 * @created Jul 27, 2013
 */
public class ParticleEffect implements Renderable {

	/**
	 * The particle effect from which the
	 */
	com.badlogic.gdx.graphics.g2d.ParticleEffect particleEffect;
	
	
	/**
	 * Creates a particle effect
	 */
	public ParticleEffect(FileHandle effect, FileHandle imageDir ) {
		this.particleEffect = new com.badlogic.gdx.graphics.g2d.ParticleEffect();
		particleEffect.load(effect, imageDir);
	}

	/**
	 * Starts the particle effect.
	 */
	public void start(){
		particleEffect.start();
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch, float deltaSeconds) {
		particleEffect.draw(spriteBatch, deltaSeconds);
	}
	
	//region Getters/Setters
	
	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onAdd(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onAdd(ComponentManager container) {
		// TODO Auto-generated method stub

	}

	/** {@inheritDoc}
	 * @see com.punchline.javalib.entities.Component#onRemove(com.punchline.javalib.entities.ComponentManager)
	 */
	@Override
	public void onRemove(ComponentManager container) {
		// TODO Auto-generated method stub

	}
	

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public void setPosition(Vector2 position) {
		particleEffect.setPosition(position.x, position.y);
	}

	@Override
	public void setRotation(float degrees) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScale(float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrigin(Vector2 origin) {
		// TODO Auto-generated method stub
		
	}

	//endregion
}
