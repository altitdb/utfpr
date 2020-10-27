package br.edu.utfpr.bowlinggame.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;

@SpringBootTest(classes = { ScoreService.class })
class ScoreServiceTest {

	@Autowired
	private ScoreService scoreService;

	@Test
	public void should_return_the_current_game_score() {
		var expected = new ScoreDTO();
		var score = scoreService.getScore();
		
		Assertions.assertThat(score).isEqualTo(expected);
	}
	
	
}
