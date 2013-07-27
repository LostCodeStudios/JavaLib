package com.punchline.javalib.entities.systems.render;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.render.Renderable;
import com.punchline.javalib.entities.systems.ComponentSystem;

/**
 * Generic system that renders health bars above every entity that has a Health component.
 * @author Nathaniel
 * @created Jul 27, 2013
 */
public class HealthRenderSystem extends ComponentSystem {

	private static final int VERTICAL_OFFSET = 5;
	
	private SpriteBatch spriteBatch;
	private Camera camera;
	
	private Texture backTexture;
	private Texture frontTexture;
	
	/**
	 * Constructs a HealthRenderSystem.
	 * @param backTextureHandle A FileHandle pointing to the texture that will be drawn as the back of the health bar.
	 * @param frontTextureHandle A FileHandle pointing to the texture that will be drawn as the front of the health bar.
	 */
	public HealthRenderSystem(Camera camera, FileHandle backTextureHandle, FileHandle frontTextureHandle) {
		super(Health.class, Transform.class, Renderable.class);
		
		spriteBatch = new SpriteBatch();
		this.camera = camera;
		
		backTexture = new Texture(backTextureHandle);
		frontTexture = new Texture(frontTextureHandle);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		
		backTexture.dispose();
		frontTexture.dispose();
	}
	
	

	@Override
	public void processEntities() {
		spriteBatch.setTransformMatrix(camera.combined);
		spriteBatch.begin();
		
		super.processEntities();
		
		spriteBatch.end();
	}

	@Override
	protected void process(Entity e) {
		Health health = e.getComponent();
		Transform transform = e.getComponent();
		Renderable sprite = e.getComponent();
		
		Vector2 pos = transform.getPosition().cpy();
		pos.add(new Vector2(-sprite.getWidth() / 2, sprite.getHeight() / 2 + VERTICAL_OFFSET));
		
		float height = backTexture.getHeight();
		float width = sprite.getWidth();
		
		spriteBatch.draw(backTexture, pos.x, pos.y, width, height);
		
		width *= health.fraction();
		
		spriteBatch.draw(frontTexture, pos.x, pos.y, width, height);
		
	}
	
}
