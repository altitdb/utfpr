package br.edu.utfpr.bowlinggame.dto;

import br.edu.utfpr.bowlinggame.entity.Round;

public class RoundDTO {
		
	private int firstShot;
	private int secondShot;
	private int bonus;
	private int score;
	
	public RoundDTO(Round round) {
		this.firstShot = round.getFirstShot();
		this.secondShot = round.getSecondShot();
		this.bonus = round.getBonus();
		this.score = round.getScore();
	}

	public RoundDTO(int firstShot, int secondShot, int bonus, int score) {
		this.firstShot = firstShot;
		this.secondShot = secondShot;
		this.bonus = bonus;
		this.score = score;
	}

	
	public int getFirstShot() {
		return firstShot;
	}
	
	public int getSecondShot() {
		return secondShot;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public int getScore() {
		return score;
	}	
}
