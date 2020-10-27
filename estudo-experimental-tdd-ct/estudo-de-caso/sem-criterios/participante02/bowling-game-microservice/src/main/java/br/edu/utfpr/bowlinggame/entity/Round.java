package br.edu.utfpr.bowlinggame.entity;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Round {

	@Id
	private UUID id;
	
	private Integer firstShot;
	private Integer score;
	
	
	public Round (Integer firstShot) {
		this.firstShot = firstShot;
		this.score = firstShot;
	}
	
	public Round (Integer firstShot, Integer secondShot) {
		
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(firstShot, score);
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
		return Objects.equals(firstShot, other.firstShot) && Objects.equals(score, other.score);
	}


	public Integer getFirstShot() {
		return firstShot;
	}


	public Integer getScore() {
		return score;
	}
	
	
	
}
