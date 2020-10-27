package br.edu.utfpr.bowlinggame.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "round")
public class Round {
	
	@Id
	@GeneratedValue
	private Long id;
	private Integer firstShot;
	private Integer secondShot;
	private Integer thirdShot;
	@ManyToOne
	@JoinColumn(name = "gameId", referencedColumnName = "id")
	private Game game;
	private int score;
	private int bonus;
	private int roundNumber;
	
	public Round() {
		
	}
	
	public Round(Game game) {
		this.game = game;
		this.roundNumber = 1;
	}
	
	public Round(Game game, int roundNumber) {
		this.game = game;
		this.roundNumber = roundNumber;
	}
	
	public Integer scored(int scored) {
		if (Objects.isNull(this.firstShot)) {
			this.firstShot = scored;
		} else if (Objects.isNull(this.secondShot)) {
			this.secondShot = scored;
		} else {
			this.thirdShot = scored;
		}
		return scored;
	}
	
	public Integer addBonus(int bonus) {
		return this.bonus += bonus;
	}
	
	public int score() {
		if (Objects.isNull(this.firstShot)) {
			return 0;
		}
		if (Objects.isNull(this.secondShot)) {
			return this.firstShot + this.bonus;
		}
		if (Objects.isNull(this.thirdShot)) {
			return this.firstShot + this.secondShot + this.bonus;
		}
		return this.firstShot + this.secondShot + this.bonus + this.thirdShot;
	}
	
	public boolean hasFinished() {
		if (this.roundNumber == 10) {
			return !Objects.isNull(this.firstShot) && !(this.firstShot == 10) && 
					!Objects.isNull(this.secondShot) && !(this.secondShot == 10);
		}
		return (!Objects.isNull(this.firstShot) && !Objects.isNull(this.secondShot))
				|| this.score() >= 10;
	}
	
	public Game thisGame() {
		return this.game;
	}
	
	public int thisRoundNumber() {
		return this.roundNumber;
	}

	public boolean strike() {
		return this.firstShot == 10;
	}
	
	public boolean spare() {
		if (Objects.isNull(this.secondShot)) return false;
		if (this.firstShot == 10) return false;
		return this.firstShot + this.secondShot == 10;
	}
	
	public int getFirstShot() {
		return this.firstShot;
	}
	
	public int getSecondShot() {
		return this.secondShot;
	}
	
	public int getBonus() {
		return this.bonus;
	}
	
	public void setFirstShot(int firsShot) {
		this.firstShot = firsShot;
	}
	
	public void setSecondShot(int secondShot) {
		this.secondShot = secondShot;
	}
	
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bonus;
		result = prime * result + ((firstShot == null) ? 0 : firstShot.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + roundNumber;
		result = prime * result + score;
		result = prime * result + ((secondShot == null) ? 0 : secondShot.hashCode());
		result = prime * result + ((thirdShot == null) ? 0 : thirdShot.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Round other = (Round) obj;
		if (bonus != other.bonus)
			return false;
		if (firstShot == null) {
			if (other.firstShot != null)
				return false;
		} else if (!firstShot.equals(other.firstShot))
			return false;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roundNumber != other.roundNumber)
			return false;
		if (score != other.score)
			return false;
		if (secondShot == null) {
			if (other.secondShot != null)
				return false;
		} else if (!secondShot.equals(other.secondShot))
			return false;
		if (thirdShot == null) {
			if (other.thirdShot != null)
				return false;
		} else if (!thirdShot.equals(other.thirdShot))
			return false;
		return true;
	}
	
	
	
}
