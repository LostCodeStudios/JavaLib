package com.punchline.javalib.entities;

/**
 * A game entity that contains several {@link Component Components} which define its attributes.
 * @author Nathaniel + WIlliam
 *
 */
public final class Entity extends ComponentManager {
	
	/**
	 * This entity's unique tag, for identifying it individually. This must be set only once, by a template.
	 */
	private String tag = "";
	
	/**
	 * The name of the group this entity belongs to. This must be set only once, by a template.
	 */
	private String group = "";
	
	/**
	 * This entity's type. This must be set only once, by a template.
	 */
	private String type = "";
	
	/**
	 * Deletion flag.
	 */
	private boolean deleted = false;
	
	/**
	 * Changed flag.
	 */
	private boolean changed = false;
	
	/**
	 * Assigns the Entity's metadata.
	 * @param tag This Entity's unique tag.
	 * @param group This Entity's group name.
	 * @param type This Entity's type.
	 */
	public Entity(String tag, String group, String type) {
		this.tag = tag;
		this.group = group;
		this.type = type;
	}
	
	/**
	 * @return This entity's unique tag.
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * @return This entity's group name.
	 */
	public String getGroup() {
		return group;
	}
	
	/**
	 * @return This entity's type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Flags this entity for deletion by the {@link EntityManager}.
	 */
	public void delete() {
		deleted = true;
	}

	/**
	 * @return Whether this Entity has been flagged for deletion.
	 */
	public boolean isDeleted() {
		return deleted;
	}
	
	/**
	 * @return Whether this Entity's components were changed.
	 */
	public boolean wasChanged() {
		if (changed) {
			changed = false;
			return true;
		}

		return false;
	}

	@Override
	public Component addComponent(Class<? extends Component> type, Component value) {
		changed = true;
		return super.addComponent(type, value);
	}
	
	@Override
	public <T extends Component> T addComponent(T value) {
		changed = true;
		return super.addComponent(value);
	}

	@Override
	public void removeComponent(Component value) {
		super.removeComponent(value);
		changed = true;
	}
	
}
