package com.punchline.javalib.entities.systems.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.components.render.Renderable;
import com.punchline.javalib.entities.systems.ComponentSystem;
import com.punchline.javalib.utils.Convert;

/**
 * System for rendering every {@link Entity} that has a {@link Renderable} component.
 * @author Natman64
 *
 */
public final class RenderSystem extends ComponentSystem {
	
	private Camera camera;
	private SpriteBatch spriteBatch;

	//region Initialization/Disposal
	
	/**
	 * Constructs a RenderSystem.
	 * @param camera The camera for rendering.
	 */
	@SuppressWarnings("unchecked")
	public RenderSystem(Camera camera) {
		super(Renderable.class);
		
		this.camera = camera;
		
		spriteBatch = new SpriteBatch();
		spriteBatch.enableBlending();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}
	
	//endregion
	
	//region Processing
	
	@Override
	public void processEntities() {
		
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
		
		super.processEntities();
		
		spriteBatch.end();
		
	}

	@Override
	protected void process(Entity e) {
		
		Renderable r = (Renderable)e.getComponent(Renderable.class);
		
		if (e.hasComponent(Transform.class)) { 
			Transform t = (Transform)e.getComponent(Transform.class);
			
			Vector2 pos = t.getPosition().cpy();
			float angle = t.getRotation();
			
			r.setPosition(Convert.metersToPixels(pos));
			r.setRotation((float)Math.toDegrees(angle));
		}
		
		r.draw(spriteBatch, deltaSeconds());
		
	}	
	
	//endregion
	
}
