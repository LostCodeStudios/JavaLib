package com.lostcode.javalib.entities.components.physical;

import com.badlogic.gdx.math.Vector2;
import com.lostcode.javalib.entities.components.Component;

/**
 * Velocity interface for velocity
 * 
 * @author MadcowD
 * @created Jul 23, 2013
 */
public interface Velocity extends Component {
	/**
	 * Gets linear velocity
	 * 
	 * @return the linearVelocity
	 */
	public Vector2 getLinearVelocity();

	/**
	 * Sets linear velocity
	 * 
	 * @param linearVelocity
	 *            the linearVelocity to set
	 */
	public void setLinearVelocity(Vector2 linearVelocity);

	/**
	 * Gets angular velocity
	 * 
	 * @return the angularVelocity
	 */
	public float getAngularVelocity();

	/**
	 * Sets angular velocity.
	 * 
	 * @param angularVelocity
	 *            the angularVelocity to set
	 */
	public void setAngularVelocity(float angularVelocity);

}
