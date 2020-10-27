package br.edu.utfpr.bowlinggame.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ScoreService.class })
class ScoreServiceTest {

	@Autowired
	private ScoreService scoreService;
	
	@MockBean
	private RoundRepository roundRepository;
	
	@Test
	void shouldGetScore() {
		Mockito.when(roundRepository.getScore()).thenReturn(25);
		ScoreDTO expected = new ScoreDTO(25);
		
		ScoreDTO score = scoreService.getScore();
		Assertions.assertNotNull(score);
		Assertions.assertEquals(expected , score);
		
		Mockito.verify(roundRepository, Mockito.times(1)).getScore();
	}
	
}
