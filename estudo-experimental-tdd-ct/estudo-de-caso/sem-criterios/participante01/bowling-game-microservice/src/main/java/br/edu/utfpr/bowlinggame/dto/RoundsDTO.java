package br.edu.utfpr.bowlinggame.dto;

import java.util.List;

public class RoundsDTO {

	private final List<RoundDTO> rounds;
	
	public RoundsDTO(List<RoundDTO> rounds) {
		this.rounds = rounds;
	}

	public List<RoundDTO> getRounds() {
		return rounds;
	}
}
