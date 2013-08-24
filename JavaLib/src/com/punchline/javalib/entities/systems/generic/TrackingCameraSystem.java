package com.punchline.javalib.entities.systems.generic;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.systems.TagSystem;
import com.punchline.javalib.entities.templates.EntityPostProcessor;
import com.punchline.javalib.utils.Convert;

/**
 * System for moving the camera to follow a given entity.
 * @author Natman64
 * @created Jul 26, 2013
 */
public class TrackingCameraSystem extends TagSystem implements EntityPostProcessor {

	private Camera camera;
	private Rectangle bounds;
	
	/**
	 * Constructs a TrackingCameraSystem.
	 * @param tag The tag of the entity for the camera to follow.
	 */
	public TrackingCameraSystem(String tag, Camera camera, Rectangle bounds) {
		super(tag);
		
		this.camera = camera;
		this.bounds = Convert.metersToPixels(bounds);
	}

	@Override
	protected void process(Entity e) {
		setPosition(e);
		
		clampCamera();
	}

	@Override
	public void process(EntityWorld world, Entity e) {
		setPosition(e);
		
		clampCamera();
	}
	
	private void setPosition(Entity e) {
		Transform t = (Transform)e.getComponent(Transform.class);
		
		Vector2 pos = Convert.metersToPixels(t.getPosition());
		
		camera.position.set(pos.x, pos.y, 0f);
	}
	
	private void clampCamera() {
		float left = camera.position.x - camera.viewportWidth / 2;
		float bottom = camera.position.y - camera.viewportHeight / 2;
		float right = left + camera.viewportWidth;
		float top = bottom + camera.viewportHeight;
		
		if (left < bounds.x) {
			camera.position.x = bounds.x + camera.viewportWidth / 2;
		}
		
		if (top > bounds.y + bounds.height) {
			camera.position.y = bounds.y + bounds.height - camera.viewportHeight / 2;
		}
		
		if (right > bounds.x + bounds.width) {
			camera.position.x = bounds.x + bounds.width - camera.viewportWidth / 2;
		}
		
		if (bottom < bounds.y) {
			camera.position.y = bounds.y + camera.viewportHeight / 2;
		}
	}
	
	@Override
	public void dispose() { }

}
