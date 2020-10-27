package br.edu.utfpr.bowlinggame.dto;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoundsDTOTest {

	@Test
	public void should_accept_rounds() {
		var round = RoundDTO.builder()
				.withFirstShot(13)
				.withScore(17)
				.build();
		
		var rounds = List.of(round);
		
		var unit = RoundsDTO.builder()
					.withRounds(rounds)
					.build();
		
		Assertions.assertThat(unit.getRounds()).containsExactlyElementsOf(rounds);
	}
	
}
