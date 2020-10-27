package br.edu.utfpr.bowlinggame.dto;

import java.util.Objects;

public class ScoreDTO {

	public Integer score = 30;

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreDTO other = (ScoreDTO) obj;
		return Objects.equals(score, other.score);
	}
	
	
	
}
