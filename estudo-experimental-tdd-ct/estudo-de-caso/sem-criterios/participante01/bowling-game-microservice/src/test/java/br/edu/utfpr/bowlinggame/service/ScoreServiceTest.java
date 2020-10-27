package br.edu.utfpr.bowlinggame.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ScoreService.class })
class ScoreServiceTest {

	@Autowired
	private ScoreService scoreService;
	
	@MockBean
	private RoundRepository roundRepository;
	
	@Test
	public void shoudlReturnScore() {
		Round round = new Round(10);
		
		BDDMockito.when(roundRepository.findAll()).thenReturn(List.of(round));
		
		ScoreDTO score = scoreService.getScore();
		
		Assertions.assertNotNull(score);
		Assertions.assertEquals(10, score.getScore());
		
		BDDMockito.verify(roundRepository).findAll();
	}
	
}
