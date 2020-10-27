package br.edu.utfpr.bowlinggame.service;


import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ShotService.class })
class ShotServiceTest {
	
	@Autowired
	private ShotService unit;
	
	@MockBean
	private RoundRepository repo;
	
	@Test
	public void should_save_the_first_shot() {
		Round round = new Round(10);
				
		BDDMockito.when(repo.save(round)).thenReturn(round);
		
		var roundDTO = RoundDTO.builder()
	            .withFirstShot(10)
	            .withScore(10)
	            .build();
	    
		var expected = RoundsDTO.builder()
	            .withRounds(Collections.singletonList(roundDTO))
	            .build();
		
		var roundsDTO = unit.shot(10);
		
		Assertions.assertThat(roundsDTO).isEqualTo(expected);
		
		BDDMockito.verify(repo, BDDMockito.times(1)).save(round);
	}
	
	@Test
	public void should_save_the_third_shot() {
		var roundOne = RoundDTO.builder()
	            .withFirstShot(3)
	            .withSecondShot(5)
	            .withScore(8)
	            .build();
	    
		var roundTwo = RoundDTO.builder()
	            .withFirstShot(4)
	            .withScore(4)
	            .build();
		
		var expected = RoundsDTO.builder()
	            .withRounds(List.of(roundOne, roundTwo))
	            .build();
		
		var rounds = unit.shot(4);
		
		Assertions.assertThat(rounds).isEqualTo(expected);
	}
	

}
