package br.edu.utfpr.bowlinggame.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoundDTOTest {

	@Test
	public void should_accept_firt_short() {
		var unit = RoundDTO.builder()
						.withFirstShot(10)
						.build();
		
		Assertions.assertThat(unit.getFirstShot()).isEqualTo(10);
	}
	
	@Test
	public void should_accept_score() {
		var unit = RoundDTO.builder()
						.withScore(10)
						.build();
		
		Assertions.assertThat(unit.getScore()).isEqualTo(10);
	}
	
	@Test
	public void should_accept_second_short() {
		var unit = RoundDTO.builder()
						.withSecondShot(10)
						.build();
		
		Assertions.assertThat(unit.getSecondShot()).isEqualTo(10);
	}
	
}
