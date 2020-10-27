package br.edu.utfpr.bowlinggame.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Round {

	private final int STRIKE_VALUE = 10;

	private final int LAST_SHOT_VALUE = 2;

	@Id
	private UUID id = UUID.randomUUID();

	@Column(nullable = false)
	private int firstShot;

	@Column(nullable = false)
	private int secondShot;

	@Column(nullable = false)
	private int bonus;

	@Column
	private int number;

	@Column
	private int currentShot = 0;
	
	public Round(int firstShot) {
		this(firstShot, 1);
	}

	public Round(int firstShot, int number) {
		this.firstShot = firstShot;
		this.number = number;
		this.currentShot = 1;
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
		return this.firstShot + this.secondShot + this.bonus;
	}

	public void setSecondShot(int shot) {
		this.currentShot = 2;
		this.secondShot = shot;
	}

	public int getNumber() {
		return number;
	}

	public boolean isCompleted() {
		return this.firstShot == STRIKE_VALUE || this.currentShot == LAST_SHOT_VALUE;
	}

}
