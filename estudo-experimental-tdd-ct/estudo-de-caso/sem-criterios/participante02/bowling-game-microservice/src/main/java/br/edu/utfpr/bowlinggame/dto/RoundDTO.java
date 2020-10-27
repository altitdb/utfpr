package br.edu.utfpr.bowlinggame.dto;

import java.util.Objects;

public class RoundDTO {

	private Integer firstShot;
	private Integer secondShot;
	private Integer score;
	
	private RoundDTO(Builder builder) {
		this.firstShot = builder.firstShot;
		this.secondShot = builder.secondShot;
		this.score = builder.score;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private Integer firstShot;
		private Integer secondShot;
		private Integer score;
		
		public Builder withFirstShot(Integer firstShot) {
			this.firstShot = firstShot;
			return this;
		}
		
		public Builder withSecondShot(Integer secondShot) {
			this.secondShot = secondShot;
			return this;
		}
		
		public Builder withScore(Integer score) {
			this.score = score;
			return this;
		}
		
		
		public RoundDTO build() {
			return new RoundDTO(this);
		}
		
		
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
		RoundDTO other = (RoundDTO) obj;
		return Objects.equals(firstShot, other.firstShot) && Objects.equals(score, other.score);
	}
	
	

	@Override
	public String toString() {
		return "RoundDTO [firstShot=" + firstShot + ", secondShot=" + secondShot + ", score=" + score + "]";
	}

	public Integer getFirstShot() {
		return firstShot;
	}
	
	public Integer getSecondShot() {
		return secondShot;
	}
	
	public Integer getScore() {
		return score;
	}
	
}
