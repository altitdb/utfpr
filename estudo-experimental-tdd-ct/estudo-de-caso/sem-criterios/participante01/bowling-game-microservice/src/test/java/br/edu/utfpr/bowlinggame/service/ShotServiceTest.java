package br.edu.utfpr.bowlinggame.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.ManyMovesException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ShotService.class })
class ShotServiceTest {
	
	@Autowired
	private ShotService shotService;
	
	
	@MockBean
	private RoundRepository roundRepository;
	
	@Test
	public void whenThereAlreAlredyTenMoves_shouldReturnThrow() {
		BDDMockito.when(roundRepository.count()).thenReturn(11L);
		Exception exception = Assertions.assertThrows(ManyMovesException.class, () -> shotService.shot(1));
		Assertions.assertEquals("There are already ten completed plays", exception.getMessage());		
	}
	
	@Test
	public void whenIsFirstsShot_shouldSaveAndReturnRound() {
		BDDMockito.when(roundRepository.count()).thenReturn(0L);
		
		ArgumentCaptor<Round> roundCaptor = ArgumentCaptor.forClass(Round.class);
		
		RoundDTO round = shotService.shot(1);
		
		BDDMockito.verify(roundRepository).save(roundCaptor.capture());
		
		Round roundSaved = roundCaptor.getValue();
		Assertions.assertEquals(1, roundSaved.getFirstShot());
		Assertions.assertEquals(1, roundSaved.getNumber());
		Assertions.assertEquals(0, roundSaved.getSecondShot());
		Assertions.assertEquals(0, roundSaved.getBonus());
		Assertions.assertEquals(1, roundSaved.getScore());
		
		Assertions.assertNotNull(round);
		Assertions.assertEquals(1, round.getFirstShot());
		Assertions.assertEquals(0, round.getSecondShot());
		Assertions.assertEquals(0, round.getBonus());
		Assertions.assertEquals(1, round.getScore());
	}
	
	@Test
	public void whenIsSecondShot_shouldSaveAndReturnRound() {
		BDDMockito.when(roundRepository.count()).thenReturn(0L);
		BDDMockito.when(roundRepository.findAll()).thenReturn(List.of(new Round(1)));
		
		ArgumentCaptor<Round> roundCaptor = ArgumentCaptor.forClass(Round.class);
		
		
		
		RoundDTO round = shotService.shot(1);
		
		BDDMockito.verify(roundRepository).save(roundCaptor.capture());
		
		Round roundSaved = roundCaptor.getValue();
		Assertions.assertEquals(1, roundSaved.getFirstShot());
		Assertions.assertEquals(1, roundSaved.getNumber());
		Assertions.assertEquals(1, roundSaved.getSecondShot());
		Assertions.assertEquals(0, roundSaved.getBonus());
		Assertions.assertEquals(2, roundSaved.getScore());
		
		Assertions.assertNotNull(round);
		Assertions.assertEquals(1, round.getFirstShot());
		Assertions.assertEquals(1, round.getSecondShot());
		Assertions.assertEquals(0, round.getBonus());
		Assertions.assertEquals(2, round.getScore());
	}
	
	@Test
	public void whenIsThirdShot_shouldSaveAndReturnRound() {
		Round roundCompleted = new Round(1);
		roundCompleted.setSecondShot(1);
		BDDMockito.when(roundRepository.count()).thenReturn(0L);
		BDDMockito.when(roundRepository.findAll()).thenReturn(List.of(roundCompleted));
		
		ArgumentCaptor<Round> roundCaptor = ArgumentCaptor.forClass(Round.class);
		
		
		
		RoundDTO round = shotService.shot(1);
		
		BDDMockito.verify(roundRepository).save(roundCaptor.capture());
		
		Round roundSaved = roundCaptor.getValue();
		Assertions.assertEquals(1, roundSaved.getFirstShot());
		Assertions.assertEquals(2, roundSaved.getNumber());
		Assertions.assertEquals(0, roundSaved.getSecondShot());
		Assertions.assertEquals(0, roundSaved.getBonus());
		Assertions.assertEquals(1, roundSaved.getScore());
		
		Assertions.assertNotNull(round);
		Assertions.assertEquals(1, round.getFirstShot());
		Assertions.assertEquals(0, round.getSecondShot());
		Assertions.assertEquals(0, round.getBonus());
		Assertions.assertEquals(1, round.getScore());
	}
	
	@Test
	public void whenIsFirstShotIsStrike_shouldCreateAndSaveSecondRoundAndReturnRound() {
		Round roundCompleted = new Round(10);
		BDDMockito.when(roundRepository.count()).thenReturn(0L);
		BDDMockito.when(roundRepository.findAll()).thenReturn(List.of(roundCompleted));
		
		ArgumentCaptor<Round> roundCaptor = ArgumentCaptor.forClass(Round.class);
		
		
		
		RoundDTO round = shotService.shot(1);
		
		BDDMockito.verify(roundRepository).save(roundCaptor.capture());
		
		Round roundSaved = roundCaptor.getValue();
		Assertions.assertEquals(1, roundSaved.getFirstShot());
		Assertions.assertEquals(2, roundSaved.getNumber());
		Assertions.assertEquals(0, roundSaved.getSecondShot());
		Assertions.assertEquals(0, roundSaved.getBonus());
		Assertions.assertEquals(1, roundSaved.getScore());
		
		Assertions.assertNotNull(round);
		Assertions.assertEquals(1, round.getFirstShot());
		Assertions.assertEquals(0, round.getSecondShot());
		Assertions.assertEquals(0, round.getBonus());
		Assertions.assertEquals(1, round.getScore());
	}
}
