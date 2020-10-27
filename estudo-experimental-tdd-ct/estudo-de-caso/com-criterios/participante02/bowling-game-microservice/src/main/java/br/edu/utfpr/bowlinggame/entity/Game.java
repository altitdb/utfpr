package br.edu.utfpr.bowlinggame.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String status;
	
	private LocalDateTime date;
	
	private int bonusesShoots = 0;
	
	public Game() {
		this.status = "STARTED";
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getStatus() {
		return this.getStatus();
	}
	
	public void finishGame() {
		this.status = "FINISHED";
	}
	
	public void startGame() {
		this.status = "STARTED";
	}
	
	public void increaseBonusOnBall(int bonusBall) {
		this.bonusesShoots = bonusBall;
	}
	
	public void decreaseBonusBall() {
		this.bonusesShoots--;
	}
	
	public int howMuchBonuses() {
		return this.bonusesShoots;
	}
}
