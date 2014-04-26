package com.dkhalife.projects;

import java.util.LinkedList;
import java.util.Random;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * This class represents the playing screen
 * 
 * @author Dany Khalife
 * 
 */
public class PlayingState extends BasicGameState {
	// The current screen state id
	private int stateID = -1;

	private final static float PADDING = Breakout.toMeters(10);

	private static Ball ball;
	private static Paddle paddle;
	private static LinkedList<Brick> bricks = new LinkedList<>();
	private static LinkedList<Body> garbage = new LinkedList<>();
	private static Wall leftWall, rightWall, topWall, bottomWall;

	public static Wall getLeftWall() {
		return leftWall;
	}

	public static Wall getRightWall() {
		return rightWall;
	}

	public static Wall getTopWall() {
		return topWall;
	}

	public static Wall getBottomWall() {
		return bottomWall;
	}

	/**
	 * 
	 * To construct a PlayingState all you need is a unique id
	 * 
	 * @param stateID The ID set for this state
	 * 
	 */
	public PlayingState(int stateID) {
		this.stateID = stateID;
	}

	/**
	 * 
	 * Getter for the current state ID
	 * 
	 */
	public int getID() {
		return stateID;
	}

	public static void destroyBrick(Brick b) {
		bricks.remove(b);
		
		garbage.add(b.getBody());
	}

	/**
	 * 
	 * This method initialises the game for this state
	 * 
	 */
	public void init(GameContainer gc, StateBasedGame sb) throws SlickException {
		Breakout.resetWorld();
		Breakout.getWorld().setContactListener(new CollisionListener());

		paddle = new Paddle(Breakout.toMeters(gc.getWidth() / 2), Breakout.toMeters(gc.getHeight()) - 2 * Paddle.HEIGHT - PADDING);
		ball = new Ball(Breakout.toMeters(gc.getWidth() / 2) + paddle.getWidth(), Breakout.toMeters(gc.getHeight()) - 4 * Paddle.HEIGHT - 2 * Ball.RADIUS, new Vec2(0.0f, 0.2f));

		float startX = PADDING + Breakout.toMeters(40) + Brick.WIDTH / 2;
		float startY = PADDING + Breakout.toMeters(40);

		paddle.setSize(Paddle.SIZE.PADDLE_LARGE);

		Random rnd = new Random();
		bricks.clear();
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 4; ++j) {
				bricks.add(new Brick(startX + i * (Brick.WIDTH + PADDING), startY + j * (Brick.HEIGHT + PADDING), rnd.nextInt(7)));
			}
		}

		// Walls
		leftWall = new Wall(PADDING / 2, Breakout.toMeters(gc.getHeight()) / 2, PADDING, Breakout.toMeters(gc.getHeight()));
		rightWall = new Wall(Breakout.toMeters(gc.getWidth()) - PADDING / 2, Breakout.toMeters(gc.getHeight()) / 2, PADDING, Breakout.toMeters(gc.getHeight()));
		topWall = new Wall(Breakout.toMeters(gc.getWidth()) / 2, PADDING / 2, Breakout.toMeters(gc.getWidth()), PADDING);
		bottomWall = new Wall(Breakout.toMeters(gc.getWidth()) / 2, Breakout.toMeters(gc.getHeight()) - PADDING / 2, Breakout.toMeters(gc.getWidth()), PADDING);
	}

	/**
	 * 
	 * This method updates the viewport so that it corresponds to the user's
	 * input
	 * 
	 */
	public void update(GameContainer gc, StateBasedGame sb, int deltaT) throws SlickException {
		if (Breakout.loadResources()) {
			// Step in the world simulation
			Breakout.getWorld().step(16 / 60f, 6, 2);
			
			//TODO: Could the bricks, paddle and walls be rotating?
			
			// Empty the garbage
			for(Body b : garbage){
				Breakout.getWorld().destroyBody(b);
			}
			garbage.clear();

			// Move paddle
			Input input = gc.getInput();
			if (input.isKeyDown(Input.KEY_LEFT)) {
				paddle.setSpeed(new Vec2(-0.25f, 0));
			}
			else if (input.isKeyDown(Input.KEY_RIGHT)) {
				paddle.setSpeed(new Vec2(0.25f, 0));
			}
			else {
				paddle.setSpeed(new Vec2());
			}

			// Game rules
			if (bricks.size() == 0) {
				System.out.println("You win!");
				gc.exit();
			}
		}
	}

	/**
	 * 
	 * This method renders the game viewport
	 * 
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO: Add background

		// Draw the bricks
		for (Brick b : bricks) {
			b.draw(g);
		}

		// Draw the ball
		ball.draw(g);

		// Draw the paddle
		paddle.draw(g);

		// And finally draw the walls
		leftWall.draw(g);
		rightWall.draw(g);
		topWall.draw(g);

		// TODO: Comment this
		bottomWall.draw(g);
	}
}