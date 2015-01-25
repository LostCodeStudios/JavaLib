package com.lostcode.javalib.utils;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public interface AbstractSpriteSheet {
	public void addRegion(String key, Rectangle region);
	public void addRegion(String key, int x, int y, int width, int height);
	public TextureRegion getRegion(String key);
	public String getTexturePath();
	public Map<String, TextureRegion> getRegions(String prefix);
}
