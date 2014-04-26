package com.dkhalife.projects;

import java.io.IOException;

import java.util.Date;

import org.jbox2d.common.Settings;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Dany Khalife
 * @since 26 December 2012
 * 
 */
public class Breakout extends StateBasedGame {
	// We'll have two states so we'll need an ID for each of them
	public static final int MAINMENU_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;

	// The game world
	private static World world;

	/**
	 * 
	 * The game is constructed by constructing a StateBasedGame with both of the
	 * states
	 * 
	 */
	public Breakout() throws SlickException {
		super("Breakout");
		
		// Settings
		Settings.velocityThreshold = 0;

		// Add our two states
		addState(new MainMenuState(MAINMENU_STATE));
		addState(new PlayingState(GAMEPLAY_STATE));

		// Load our resources
		ResourceManager.loadResources("res/resources.xml");

		// And load the main menu state
		// TODO: Change to main menu
		enterState(GAMEPLAY_STATE);
	}

	/**
	 * 
	 * Getter for the world
	 * 
	 * @return The current world
	 * 
	 */
	public static World getWorld() {
		return world;
	}

	/**
	 * 
	 * This method resets the world
	 * 
	 */
	public static void resetWorld() {
		// Create the world with no gravity and allow bodies to sleep
		world = new World(new Vec2(), true);
	}

	private static float scalingFactor = 100;

	public static float toPixels(float meters) {
		return meters * scalingFactor;
	}

	public static float toMeters(float pixels) {
		return pixels / scalingFactor;
	}

	/**
	 * 
	 * This method loads the resources in deferred processing
	 * 
	 * @return True when resources are loaded. False otherwise.
	 * 
	 */
	public static boolean loadResources() throws SlickException {
		if (LoadingList.get().getRemainingResources() > 0) {
			try {
				DeferredResource e = LoadingList.get().getNext();

				System.out.println(new Date() + " INFO:" + e.getDescription());

				e.load();
			} catch (IOException e) {
				throw new SlickException("Error loading resource", e);
			}

			return false;
		}

		return true;
	}

	/**
	 * 
	 * This method initialises both states
	 * 
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENU_STATE).init(gc, this);
		this.getState(GAMEPLAY_STATE).init(gc, this);
	}

	// This main method launches the game
	public static void main(String args[]) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Breakout());

		// Limit the update calls and fps so that CPU wont explode
		app.setTargetFrameRate(60);
		// We'll set the display as 800x600
		app.setDisplayMode(800, 600, false);
		app.start();
	}
}
