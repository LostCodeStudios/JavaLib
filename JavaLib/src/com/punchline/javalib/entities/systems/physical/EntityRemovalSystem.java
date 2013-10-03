package com.punchline.javalib.entities.systems.physical;

import com.badlogic.gdx.math.Rectangle;
import com.punchline.javalib.entities.Entity;
import com.punchline.javalib.entities.EntityWorld;
import com.punchline.javalib.entities.components.physical.Transform;
import com.punchline.javalib.entities.systems.ComponentSystem;

/**
 * The EntityRemovalSystem processes all Entities that have a position in the EntityWorld.
 * When an entity moves outside of the world's bounding rectangle, it is removed.
 * @author Natman64
 * @created Jul 24, 2013
 */
public class EntityRemovalSystem extends ComponentSystem {

	/**
	 * How far an entity can be outside of the world, in meters. This is necessary because a position describes an entity's center.
	 * Entities whose sprites have a larger radius than this number will be removed while still visible to the player.
	 */
	public static final float REMOVAL_THRESHOLD = 5f;
	
	private Rectangle bounds;
	
	/**
	 * Constructs the EntityRemovalSystem.
	 */
	@SuppressWarnings("unchecked")
	public EntityRemovalSystem() {
		super(Transform.class);
	}
	
	@Override
	public void setWorld(EntityWorld world) {
		super.setWorld(world);
	}

	@Override
	public void dispose() { }

	@Override
	public void processEntities() {
		
		//Set up the removal bounds
		Rectangle bounds = world.getBounds();
		this.bounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
		this.bounds.x -= REMOVAL_THRESHOLD;
		this.bounds.y -= REMOVAL_THRESHOLD;
		this.bounds.width += REMOVAL_THRESHOLD * 2;
		this.bounds.height += REMOVAL_THRESHOLD * 2;
		
		super.processEntities();
		
	}
	
	@Override
	protected void process(Entity e) {
		Transform t = (Transform)e.getComponent(Transform.class);
		
		if (!bounds.contains(t.getPosition().x, t.getPosition().y))
			e.delete();
	}

}
