package cs1302.arcade.numberGame;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This is the NumberGameInfoBar class
 *
 */
public class NumberGameInfoBar extends HBox{
	NumberGame numGame;
	int gameScore = 0;
	int bestScore = 0;
	Text scoreText = new Text("Score: " + gameScore);
	Text bestScoreText = new Text("Best:  " + bestScore);
	
	public NumberGameInfoBar(NumberGame numGame) {
		super();
		this.numGame = numGame;
		VBox vbox = new VBox();
		this.getChildren().add(vbox);
		vbox.getChildren().addAll(scoreText, bestScoreText);
	}
}
