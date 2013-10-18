package com.punchline.javalib.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Helper for playing all game audio, including both sound effects and music.
 * 
 * @author Natman64
 * 
 */
public final class SoundManager {

	//region Fields
	
	private static Map<String, Sound> soundEffects = new HashMap<String, Sound>();
	private static Map<String, Music> songs = new HashMap<String, Music>();
	private static Music currentSong;

	private static float soundVol = 1f;
	private static float musicVol = 1f;

	//endregion
	
	//region Initialization
	
	/**
	 * Loads all the sound effects and songs encoded in the provided content XML file.
	 * @param xmlHandle FileHandle of the XML file.
	 * @throws IOException
	 */
	public static void loadContent(FileHandle xmlHandle) throws IOException {
		XmlReader reader = new XmlReader();
		Element xml = reader.parse(xmlHandle);
		
		for (Element sound : xml.getChildrenByName("Sound")) {
			String key = sound.getAttribute("key");
			String filePath = sound.getText();
			
			addSound(key, Gdx.files.internal(filePath));
		}
		
		for (Element song : xml.getChildrenByName("Song")) {
			String key = song.getAttribute("key");
			String filePath = song.getText();
			
			addSong(key, Gdx.files.internal(filePath));
		}
	}
	
	//endregion
	
	//region Disposal
	
	/**
	 * Disposes of all sound effects and music, and removes them from the
	 * SoundManager's memory.
	 */
	public static void dispose() {

		Set<String> keys = soundEffects.keySet();

		String key;
		Iterator<String> it = keys.iterator();

		while (it.hasNext()) {
			key = it.next();

			Sound sound = getSound(key);
			sound.dispose();
		}

		keys = songs.keySet();

		it = keys.iterator();

		while (it.hasNext()) {
			key = it.next();

			Music music = getSong(key);
			music.dispose();
		}

		soundEffects.clear();
		songs.clear();
	}
	
	//endregion

	//region Sound Volume
	
	/**
	 * @return The current sound volume, from the range [0, 1].
	 */
	public static float getSoundVolume() {
		return soundVol;
	}

	/**
	 * Sets the current sound volume.
	 * 
	 * @param volume
	 *            The new sound volume. Must fall in the range [0, 1].
	 */
	public static void setSoundVolume(float volume) {
		if (volumeInvalid(volume))
			return;

		soundVol = volume;
	}

	//endregion
	
	//region Music Volume
	
	/**
	 * @return The current music volume, from the range [0, 1].
	 */
	public static float getMusicVolume() {
		return musicVol;
	}

	/**
	 * Sets the current music volume.
	 * 
	 * @param volume
	 *            The new music volume. Must fall in the range [0, 1].
	 */
	public static void setMusicVolume(float volume) {
		if (volumeInvalid(volume))
			return;

		musicVol = volume;
	}

	//endregion
	
	//region Helpers
	
	/**
	 * Helper method for making sure a volume amount falls in the valid range of
	 * [0, 1].
	 * 
	 * @param volume
	 *            The volume to check.
	 * @return Whether this volume is invalid.
	 */
	private static boolean volumeInvalid(float volume) {
		return (volume < 0 || volume > 1);
	}
	
	//endregion

	//region Sound Management
	
	/**
	 * Adds a sound effect to manager's memory. This sound can later be played
	 * by calling SoundManager.playSound() with its key.
	 * 
	 * @param key
	 *            The key that will be used to play this sound later.
	 * @param soundHandle
	 *            A FileHandle pointing to the sound effect.
	 */
	public static void addSound(String key, FileHandle soundHandle) {
		soundEffects.put(key, Gdx.audio.newSound(soundHandle));
	}

	/**
	 * Removes a sound effect from the manager's memory, and returns it so it
	 * can be disposed. It's a good idea to do this when a sound is no longer
	 * being frequently used.
	 * 
	 * @param key
	 *            The key of the sound effect to remove.
	 * @return The removed sound effect.
	 */
	public static Sound removeSound(String key) {
		return soundEffects.remove(key);
	}

	/**
	 * @param key
	 *            The key of a sound effect.
	 * @return The sound effect associated with that key.
	 */
	private static Sound getSound(String key) {
		return soundEffects.get(key);
	}
	
	//endregion

	//region Sound Playback
	
	/**
	 * Plays a sound effect.
	 * 
	 * @param key
	 *            The key of the sound effect to play.
	 * @param volume
	 *            The volume to play the sound effect at.
	 */
	public static void playSound(String key, float volume) {
		if (volumeInvalid(volume))
			return;

		soundEffects.get(key).play(soundVol * volume);
	}

	/**
	 * Plays a sound effect.
	 * 
	 * @param key
	 *            The key of the sound effect to play.
	 */
	public static void playSound(String key) {
		playSound(key, 1f);
	}

	//endregion
	
	//region Song Management
	
	/**
	 * Adds a song to the SoundManager's memory.
	 * 
	 * @param key
	 *            The key by which this song can be played.
	 * @param songHandle
	 *            A FileHandle pointing to the song.
	 */
	public static void addSong(String key, FileHandle songHandle) {
		songs.put(key, Gdx.audio.newMusic(songHandle));
	}

	/**
	 * Removes a song from the SoundManager's memory, and returns it for
	 * disposing.
	 * 
	 * @param key
	 *            The key by which this song can be played.
	 * @return The song, for disposing.
	 */
	public static Music removeSong(String key) {
		return songs.remove(key);
	}

	/**
	 * @param key
	 *            The key of a song.
	 * @return The song associated with that key.
	 */
	private static Music getSong(String key) {
		return songs.get(key);
	}
	
	//endregion
	
	//region Song Playback

	/**
	 * Plays a song from the SoundManager's memory.
	 * 
	 * @param key
	 *            The key of the song to play.
	 * @param volume
	 *            The volume to play it at.
	 * @param looping
	 *            Whether the song should loop.
	 */
	public static void playSong(String key, float volume, boolean looping) {
		if (volumeInvalid(volume))
			return;

		if (isMusicPlaying())
			currentSong.stop();

		Music song = songs.get(key);
		song.setLooping(looping);
		song.setVolume(musicVol * volume);
		song.play();

		currentSong = song;
	}

	/**
	 * @return Whether there is any music playing from the SoundManager.
	 */
	public static boolean isMusicPlaying() {
		return (currentSong != null && currentSong.isPlaying());
	}

	/**
	 * @return Whether a song is currently paused.
	 */
	public static boolean isMusicPaused() {
		return (currentSong != null && !currentSong.isPlaying());
	}

	/**
	 * Pauses the current music, if there is any playing.
	 */
	public static void pauseMusic() {
		if (isMusicPlaying())
			currentSong.pause();
	}

	/**
	 * Resumes the current music, if there is any paused.
	 */
	public static void resumeMusic() {
		if (isMusicPaused())
			currentSong.play();
	}

	/**
	 * Stops the current song, if a song is playing or paused.
	 */
	public static void stopMusic() {
		if (currentSong != null)
			currentSong.stop();
	}
	
	//endregion

}
