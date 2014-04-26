package com.dkhalife.projects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * This class implements the main menu for the game
 * 
 * @author Dany Khalife
 * 
 */
public class MainMenuState extends BasicGameState {
	// The current screen state id
	private int stateID = -1;

	/**
	 * 
	 * To construct a main menu state we only need to give it an ID
	 * 
	 * @param stateID The ID for this main menu state
	 * 
	 */
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	/**
	 * 
	 * Getter for the ID
	 * 
	 * @return The ID
	 * 
	 */
	public int getID() {
		return stateID;
	}

	/**
	 * 
	 * This method initialises the main menu
	 * 
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	/**
	 * 
	 * This method updates the viewport according to the user's input
	 * 
	 */
	public void update(GameContainer gc, StateBasedGame sb, int deltaT) throws SlickException {
		if (Breakout.loadResources()) {

		}
	}

	/**
	 * 
	 * This method renders the main menu
	 * 
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

	}
}
