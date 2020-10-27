package br.edu.utfpr.bowlinggame.dto;

import java.util.List;
import java.util.Objects;

public class RoundsDTO {

	private List<RoundDTO> rounds;
	
	private RoundsDTO(Builder builder) {
		this.rounds = builder.rounds;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private List<RoundDTO> rounds;

		public Builder withRounds(List<RoundDTO> rounds) {
			this.rounds = rounds;
			return this;
		}

		public RoundsDTO build() {
			return new RoundsDTO(this);
		}
		
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(rounds);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoundsDTO other = (RoundsDTO) obj;
		return Objects.equals(rounds, other.rounds);
	}
	

	@Override
	public String toString() {
		return "RoundsDTO [rounds=" + rounds + "]";
	}

	public List<RoundDTO> getRounds() {
		return rounds;
	}

}
