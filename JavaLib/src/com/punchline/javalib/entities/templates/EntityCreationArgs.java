package com.punchline.javalib.entities.templates;

import com.badlogic.gdx.utils.Array;
import com.punchline.javalib.entities.Entity;

/**
 * Data structure containing all of the necessary information to create an {@link Entity}.
 * @author Natman64
 *
 */
public final class EntityCreationArgs {
	
	private String templateTag;
	private boolean groupTemplate;
	private Array<Object> args = new Array<Object>();
	
	/**
	 * Constructs a set of EntityCreationArgs.
	 * @param templateTag The tag of the template that should be used.
	 * @param groupTemplate Whether the template that should be used is a group template.
	 * @param args The arguments for creating the Entity.
	 */
	public EntityCreationArgs(String templateTag, boolean groupTemplate, Object... args) {
		this.templateTag = templateTag;
		this.groupTemplate = groupTemplate;
		
		for (Object arg : args) {
			this.args.add(arg);
		}
	}
	
	/**
	 * @return The tag of the template that should be used.
	 */
	public String getTemplateTag() {
		return templateTag;
	}
	
	/**
	 * @return Whether the template that should be used is a group template.
	 */
	public boolean useGroupTemplate() {
		return groupTemplate;
	}
	
	/**
	 * @return The arguments for creating the Entity.
	 */
	public Object[] getArgs() {
		return args.toArray();
	}
	
}
