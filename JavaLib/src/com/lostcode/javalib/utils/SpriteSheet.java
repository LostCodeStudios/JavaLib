package com.lostcode.javalib.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Contains a texture and a map of Strings to TextureRegions, for accessing the
 * proper regions when creating sprites. Using a single SpriteSheet for all
 * sprites will greatly improve rendering performance by eliminating texture
 * switching.
 * 
 * @author Natman64
 * 
 */
public class SpriteSheet implements Disposable {

	// region Fields

	private Texture sheet;
	private Map<String, TextureRegion> regions = new HashMap<String, TextureRegion>();

	// endregion

	// region Initialization

	/**
	 * Creates a SpriteSheet.
	 * 
	 * @param sheet
	 *            The texture containing the SpriteSheet's graphics.
	 */
	public SpriteSheet(Texture sheet) {
		this.sheet = sheet;
	}
	
	// endregion

	// region Static Initialization
	
	/**
	 * Parses a SpriteSheet from an XML file.
	 * @param xmlPath FileHandle of the XML file.
	 * @return A fully initialized SpriteSheet.
	 * @throws IOException
	 */
	public static SpriteSheet fromXML(FileHandle xmlHandle) throws IOException {
		XmlReader reader = new XmlReader();
		Element xml = reader.parse(xmlHandle);
		
		String texturePath = xml.getChildByName("texture").getText();
		
		SpriteSheet sheet = new SpriteSheet(new Texture(Gdx.files.internal(texturePath)));
		
		Rectangle rect = new Rectangle();
		for (Element child : xml.getChildrenByName("rect")) {
			String[] coords = child.getText().split(",");
			rect.x = Float.parseFloat(coords[0]);
			rect.y = Float.parseFloat(coords[1]);
			rect.width = Float.parseFloat(coords[2]);
			rect.height = Float.parseFloat(coords[3]);
			
			sheet.addRegion(child.getAttribute("key"), rect);
		}
		
		return sheet;
	}
	
	// endregion
	
	// region Disposal

	@Override
	public void dispose() {
		regions.clear();
		sheet.dispose();
	}

	// endregion

	// region Adding Regions

	/**
	 * Adds a TextureRegion to the SpriteSheet.
	 * 
	 * @param key
	 *            The key that this region should be associated with.
	 * @param region
	 *            The TextureRegion.
	 */
	public void addRegion(String key, TextureRegion region) {

		// Make sure the TextureRegion is part of the same texture.
		if (region.getTexture() == sheet) {
			regions.put(key, region);
		} else {
			throw new IllegalArgumentException("All SpriteSheet texture "
					+ "regions must share the same texture.");
		}

	}

	/**
	 * Adds a TextureRegion to the SpriteSheet.
	 * 
	 * @param key
	 *            The key that this region should be associated with.
	 * @param region
	 *            Rectangle defining the region. NOTE: The Rectangle's
	 *            coordinates are not converted into the y-down coordinate
	 *            system. They are taken as-is.
	 */
	public void addRegion(String key, Rectangle region) {

		TextureRegion texRegion = new TextureRegion(sheet, (int) region.x,
				(int) region.y, (int) region.width, (int) region.height);

		regions.put(key, texRegion);

	}

	/**
	 * Adds a TextureRegion to the SpriteSheet.
	 * 
	 * @param key
	 *            The key that this region should be associated with.
	 * @param x
	 *            The region's x coordinate.
	 * @param y
	 *            The region's y coordinate.
	 * @param width
	 *            The region's width.
	 * @param height
	 *            The region's height.
	 */
	public void addRegion(String key, int x, int y, int width, int height) {

		TextureRegion texRegion = new TextureRegion(sheet, x, y, width, height);

		regions.put(key, texRegion);

	}

	// endregion

	// region Accessors

	/**
	 * @return This SpriteSheet's sheet texture.
	 */
	public Texture getTexture() {
		return sheet;
	}

	/**
	 * @param key
	 *            The key of the desired TextureRegion.
	 * @return The TextureRegion associated with the given key.
	 */
	public TextureRegion getRegion(String key) {
		return regions.get(key);
	}

	/**
	 * @param prefix
	 *            A key prefix.
	 * @return A map of all TextureRegions that use the given prefix, with their
	 *         suffixes as the keys.
	 */
	public Map<String, TextureRegion> getRegions(String prefix) {
		Map<String, TextureRegion> regions = new HashMap<String, TextureRegion>();

		for (String key : this.regions.keySet()) {
			if (key.startsWith(prefix)) {
				String suffix = key.replace(prefix, "");
				regions.put(suffix, this.regions.get(key));
			}
		}

		return regions;
	}

	// endregion

}
