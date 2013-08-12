package com.punchline.javalib.entities.components.render;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.components.ComponentManager;

/**
 * The particle effect component for instantiating particle effects.
 * @author MadcowD
 * @created Jul 27, 2013
 */
public class ParticleEffect implements Renderable {

	//region Fields/Initialization
	
	private com.badlogic.gdx.graphics.g2d.ParticleEffect particleEffect;
	
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
	public void start() {
		particleEffect.start();
	}
	
	//endregion
	
	//region Events
	
	@Override
	public void onAdd(ComponentManager container) { }

	@Override
	public void onRemove(ComponentManager container) { }
	
	//endregion

	//region Accessors
	
	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}
	
	@Override
	public Vector2 getPosition() {
		return new Vector2();
	}
	
	//endregion

	//region Mutators
	
	@Override
	public void setPosition(Vector2 position) {
		particleEffect.setPosition(position.x, position.y);
	}

	@Override
	public void setRotation(float degrees) { }

	@Override
	public void setScale(float scaleX, float scaleY) { }

	@Override
	public void setOrigin(Vector2 origin) { }

	//endregion
	
	//region Rendering
	
	@Override
	public void draw(SpriteBatch spriteBatch, float deltaSeconds) {
		particleEffect.draw(spriteBatch, deltaSeconds);
	}
	
	//endregion
	
}
