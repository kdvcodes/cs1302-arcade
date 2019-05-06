package cs1302.arcade;

public class Score {
	
	private int score;
	private String name;
	
	public Score (String name, int score) {
		this.score = score;
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
}