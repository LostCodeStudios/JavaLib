package com.lostcode.javalib.entities;

import com.lostcode.javalib.entities.components.ComponentManager;
import com.lostcode.javalib.entities.components.generic.Health;
import com.lostcode.javalib.entities.components.physical.Collidable;

/**
 * Class containing helpful generic collision events.
 * 
 * @author Natman64
 * @created Jul 25, 2013
 */
public final class GenericCollisionEvents {

	/**
	 * @return A Collidable whose event damages the victim by its owner's max
	 *         health.
	 */
	public static Collidable damageVictim() {

		return new Collidable() {

			@Override
			public void onAdd(ComponentManager container) {
			}

			@Override
			public void onRemove(ComponentManager container) {
			}

			@Override
			public void onBeginContact(Entity container, Entity victim) {
				if (container.hasComponent(Health.class)
						&& victim.hasComponent(Health.class) && !victim.getGroup().equals(container.getGroup())) {

					Health h1 = (Health) container.getComponent(Health.class);
					Health h2 = (Health) victim.getComponent(Health.class);
					h2.drain(h1.getMaxValue());
				}
			}

			@Override
			public float continueCollision(Entity container, Entity victim) {
				return 1;
			}

		};

	}

	/**
	 * @return A Collidable that does nothing.
	 */
	public static Collidable empty() {

		return new Collidable() {

			@Override
			public void onAdd(ComponentManager container) {
			}

			@Override
			public void onRemove(ComponentManager contanier) {
			}

		};

	}

}
