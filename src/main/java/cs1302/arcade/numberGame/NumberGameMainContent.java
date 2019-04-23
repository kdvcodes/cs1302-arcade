package cs1302.arcade.numberGame;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This is the NumberGameMainContent class
 */
public class NumberGameMainContent extends Canvas{
	// Divide canvas into 4x4 grid
	// Draw lines in between grid
	// calc the center
	// make method to get center by passing in box coor
	
	int width, height, boxWidth, boxHeight;
	final int gameSize = 4;
	GraphicsContext graphicsContext;
	NumberGame numberGame;
	NumberGameBoard board;
	
	public NumberGameMainContent(NumberGame numberGame, int h, int w) {
		super(h, w);
		this.numberGame = numberGame;
		this.height = h;
		this.width = w;
		
		board = new NumberGameBoard(gameSize, gameSize);
		
		boxWidth = this.width/gameSize;
		boxHeight = this.height/gameSize;
		
		graphicsContext = this.getGraphicsContext2D();
		
		// Draw horizontal lines
		for (int i = 1; i < gameSize; i++) {
			graphicsContext.strokeLine(0, boxHeight * i, this.width, boxHeight * i);
		} // for
		
		// Draw vertical lines
		for (int i = 1; i < gameSize; i++) {
			graphicsContext.strokeLine(boxWidth * i, 0, boxWidth * i, this.height);
		}
		
		//setDefaultNumbers();
		//setDefaultGrid();
	}
	
	
	/**
	 * This will set everything in the array to be 0 at the start of the game
	 */
	public void setDefaultNumbers() {
		for(int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				numInBox[i][j] = 0;
			}
		}
	} // setDefaultNumbers
	
	/**
	 * This will set everything in the grid itself to be white blanks if the its box container is 0
	 */
	public void setDefaultGrid() {
		for(int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				int[] coordinate = getBoxCenterCoordinate(i, j);
				if(numInBox[i][j] == 0) {
					graphicsContext.strokeText("", coordinate[0], coordinate[1]);
				}
			}
		}
	} // setDefaultGrid
	
	/**
	 * This method will get the actual coordinate in pixels in the canvas to be worked with 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return an array of 2 numbers [x,y]
	 */
	public int[] getBoxCenterCoordinate(int x, int y) {
		int jumpStepHorizontal = boxWidth / 2;
		int jumpStepVertical = boxHeight / 2;
		int returnX = x * boxWidth + jumpStepHorizontal;
		int returnY = y * boxHeight + jumpStepVertical;
		return new int[] {returnX, returnY};
	} // getBoxCenterCoordinate
	
	/**
	 * This method will set the specified number to the specified coordinate
	 * @param x the x of the coordinate
	 * @param y the y of the coordinate
	 * @param numToAssign the specified number to be assigned
	 */
	public void setNumber(int x, int y, int numToAssign) {
		int[] coordinate = getBoxCenterCoordinate(x, y);
		
		if(numInBox[x][y] >= 0 ) {
			graphicsContext.strokeText("", coordinate[0], coordinate[1]);
		} // if
		
		numInBox[x][y] = numToAssign;
		
		graphicsContext.strokeText("" + numInBox[x][y], coordinate[0], coordinate[1]);
		
	} // setNumber
	
	EventHandler<? super KeyEvent> createKeyHandler() {
		return event -> {
			System.out.println(event);
			if(event.getCode() == KeyCode.LEFT) {
				
			}
			
			if(event.getCode() == KeyCode.RIGHT) {
				
			}
			
			if(event.getCode() == KeyCode.UP) {
				
			}
			
			if(event.getCode() == KeyCode.DOWN) {
				
			}
		};
	}
}
