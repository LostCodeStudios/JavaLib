package com.lostcode.javalib.entities.components.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.components.Component;
import com.lostcode.javalib.entities.components.ComponentManager;

/**
 * The Parallax scrolling component which stores a depth ratio.
 * 
 * @author MadcowD
 * @created Oct 2, 2013
 */
public class Parallax implements Component {

	// region Fields

	private Camera camera;
	private float depthRatio;

	// endregion

	/**
	 * Initializes the Parallax component.
	 * 
	 * @param camera
	 *            The camera to which the parallax scrolling effect is bound.
	 * @param depthRatio
	 *            The ration of movement compared to the camera and it's
	 *            position.
	 */
	public Parallax(Camera camera, float depthRatio) {
		this.camera = camera;
		this.depthRatio = depthRatio;
	}

	// region Accessors/Mutators

	/**
	 * @return the depthRatio
	 */
	public float getDepthRatio() {
		return depthRatio;
	}

	/**
	 * @param depthRatio
	 *            the depthRatio to set
	 */
	public void setDepthRatio(float depthRatio) {
		this.depthRatio = depthRatio;
	}

	/**
	 * @return The camera to which the parallax object is bound.
	 */
	public Vector2 getCameraPosition() {
		return new Vector2(camera.position.x, camera.position.y);
	}

	// endregion

	// region Methods
	@Override
	public void onAdd(ComponentManager container) {

	}

	@Override
	public void onRemove(ComponentManager container) {

	}

	// endregion

}
