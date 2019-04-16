package cs1302.arcade.numberGame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 */
public class NumberGameMainContent extends Canvas{
	// Divide canvas into 4x4 grid
	// Draw lines in between grid
	// calc the center
	// make method to get center by passing in box coor
	
	int width, height, boxWidth, boxHeight;
	final int gameSize = 4;
	GraphicsContext graphicContext;
	NumberGame numberGame;
	
	public NumberGameMainContent(NumberGame numberGame, int h, int w) {
		super(h, w);
		this.numberGame = numberGame;
		this.height = h;
		this.width = w;
		
		boxWidth = this.width/gameSize;
		boxHeight = this.height/gameSize;
		
		graphicContext = this.getGraphicsContext2D();
		
		// Draw horizontal lines
		for (int i = 1; i < gameSize; i++) {
			graphicContext.strokeLine(0, boxHeight * i, this.width, boxHeight * i);
		} // for
		
		// Draw vertical lines
		for (int i = 1; i < gameSize; i++) {
			graphicContext.strokeLine(boxWidth * i, 0, boxWidth * i, this.height);
		}
		
		// Testing number printout on grid
		setNumber(2,2,2048);
	}
	
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
	}
	
	public void setNumber(int x, int y, int numToAssign) {
		int[] coordinate = getBoxCenterCoordinate(x, y);
		
		graphicContext.strokeText("" + numToAssign, coordinate[0], coordinate[1]);
	}
}
