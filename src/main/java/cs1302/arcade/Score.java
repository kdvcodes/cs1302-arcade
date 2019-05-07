package cs1302.arcade;

/**
 * Represents a score and it's owner's name
 */
public class Score {

	/**
	 * The score itself
	 */
	private int score;

	/**
	 * The name of the person who got this Score
	 */
	private String name;

	/**
	 * this is the score method that keeps score for the games
	 * 
	 * @param name  the name
	 * @param score the score
	 */
	public Score(String name, int score) {
		this.score = score;
		this.name = name;
	}

	/**
	 * this method will return the score of the game when it gets called
	 * 
	 * @return score of the game
	 */
	public int getScore() {
		return score;
	}

	/**
	 * this method will return the name when it gets called
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}