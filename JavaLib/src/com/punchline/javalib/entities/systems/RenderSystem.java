package com.punchline.javalib.entities.systems;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.components.BaseTransform;
import com.punchline.javalib.entities.components.render.Renderable;

/**
 * System for rendering every {@link Entity} that has a {@link Renderable} component.
 * @author Nathaniel
 *
 */
public class RenderSystem extends ComponentSystem {
	
	Camera camera;
	SpriteBatch spriteBatch;

	public RenderSystem(Camera camera) {
		super(Renderable.class);
		
		this.camera = camera;
		spriteBatch = new SpriteBatch();
	}

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
		
		Renderable r = e.getComponent();
		BaseTransform t = e.getComponent();
		
		r.setPosition(t.getPosition());
		r.setRotation(t.getRotation());
		
		r.draw(spriteBatch, deltaSeconds());
		
	}
	
}
