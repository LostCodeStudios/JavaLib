package com.punchline.javalib.entities;

import com.punchline.javalib.entities.components.generic.Health;
import com.punchline.javalib.entities.components.physical.Collidable;

/**
 * Class containing helpful generic collision events.
 * @author Nathaniel
 * @created Jul 25, 2013
 */
public final class GenericCollisionEvents {

	/**
	 * @return A Collidable whose event damages the victim by its owner's max health.
	 */
	public static Collidable damageVictim() {
		
		return new Collidable() {

			@Override
			public void onAdd(ComponentManager container) { }

			@Override
			public void onRemove(ComponentManager container) { }

			@Override
			public float onCollide(Entity container, Entity victim) {
				if (container.hasComponent(Health.class) && victim.hasComponent(Health.class)) {
					Health h1 = container.getComponent();
					Health h2 = victim.getComponent();
					h2.drain(h1.getMaxValue());
				}
				
				return 1;
			}
			
		};
		
	}
	
}