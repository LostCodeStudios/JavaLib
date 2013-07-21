package com.punchline.javalib.entities.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.punchline.javalib.entities.Component;
import com.punchline.javalib.entities.ComponentContainer;

/**
 * {@link EntityWorld} Component containing a Box2D {@link com.badlogic.gdx.physics.box2d.World World}
 * @author Nathaniel
 *
 */
public class PhysicsWorld extends Component {
	
	World world;
	
	/**
	 * Instantiates the Box2D {@link com.badlogic.gdx.physics.box2d.World World} with a default gravity vector 
	 * and sleeping enabled.
	 */
	public PhysicsWorld() {
		this(new Vector2(0, -10), true);
	}
	
	/**
	 * Instantiates the Box2D {@link com.badlogic.gdx.physics.box2d.World World} with the given parameters.
	 * @param gravity The world's gravity vector.
	 * @param doSleeping Whether the world should allow sleeping.
	 */
	public PhysicsWorld(Vector2 gravity, boolean doSleeping) {
		world = new World(gravity, doSleeping);
	}
	
	/**
	 * @return The Box2D {@link com.badlogic.gdx.physics.box2d.World World}.
	 */
	public World getWorld() {
		return world;
	}
	
}
