package br.edu.utfpr.bowlinggame.dto;

public class ScoreDTO {
	private int score;
	
	public ScoreDTO() {
		
	}
	
	public ScoreDTO(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
