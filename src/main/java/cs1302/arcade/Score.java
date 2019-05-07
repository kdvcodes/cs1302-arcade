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
	 * @param name
	 * @param score
	 */
	public Score (String name, int score) {
		this.score = score;
		this.name = name;
	}
	
	/**
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}
	
}