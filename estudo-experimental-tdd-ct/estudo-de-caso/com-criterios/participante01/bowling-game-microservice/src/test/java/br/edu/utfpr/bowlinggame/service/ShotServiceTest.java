package br.edu.utfpr.bowlinggame.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.google.common.collect.Iterables;		

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.InvalidShotValueException;
import br.edu.utfpr.bowlinggame.exception.RoundExcededLimitException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@SpringBootTest(classes = { ShotService.class })
class ShotServiceTest {
	
	@Autowired
	private ShotService shotService;
	
	@MockBean
	private RoundRepository roundRepository;
	
	@Test
	void shouldntAcceptMoreShots() {
		BDDMockito.when(roundRepository.findAll())
				.thenReturn(IntStream.rangeClosed(1, 10).boxed().map(Round::new).collect(Collectors.toList()));
		Assertions.assertThrows(RoundExcededLimitException.class, () -> shotService.shot(3));
	}
	
	@Test
	void shoulAcceptMoreShotsRoundTenIncomplete() {
		BDDMockito.when(roundRepository.findAll())
				.thenReturn(IntStream.rangeClosed(0, 9).boxed().map(Round::new).collect(Collectors.toList()));
		shotService.shot(1);
		Mockito.verify(roundRepository, Mockito.times(1)).save(Matchers.any());
	}
	
	@Test
    void shouldSaveAShot() {
        Round roundEntity = new Round(10);
        BDDMockito.when(roundRepository.findAll()).thenReturn(new ArrayList<Round>());
        BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
        RoundDTO round = new RoundDTO(10, null ,10);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(Collections.singletonList(round))
            .build();
        
        RoundsDTO rounds = shotService.shot(10);

        Assertions.assertEquals(expected, rounds);
    }
	
	@Test
	void shouldAddNewShotLastIsCompleted() {
		Round roundEntity = new Round(5);
		BDDMockito.when(roundRepository.findAll()).thenReturn(new ArrayList<Round>(Arrays.asList(new Round(10))));
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		
		RoundDTO round = new RoundDTO(10, null ,10);
		RoundDTO roundNew = new RoundDTO(5, null ,5);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(Arrays.asList(round, roundNew))
            .build();
        
        RoundsDTO rounds = shotService.shot(5);

        Assertions.assertEquals(expected, rounds);
	}
	
	@Test
	void shouldAddSecondShotLastRound() {
		var roundEntity = Round.builder().withShotOne(6).withShotTwo(4).withScore(10).build();
		BDDMockito.when(roundRepository.findAll()).thenReturn(new ArrayList<Round>(Arrays.asList(new Round(6))));
		BDDMockito.when(roundRepository.save(roundEntity)).thenReturn(roundEntity);
		
		RoundDTO round = new RoundDTO(6, 4 ,10);
        RoundsDTO expected = RoundsDTO.builder()
            .withRounds(Arrays.asList(round))
            .build();
        
        RoundsDTO rounds = shotService.shot(4);

        Assertions.assertEquals(expected, rounds);
	}
	
	@Test
	void shouldInvalidNegativeShot() {
		Assertions.assertThrows(InvalidShotValueException.class, () -> shotService.shot(-1));
	}
	
	@Test
	void shouldInvalidMoreTenValueShot() {
		Assertions.assertThrows(InvalidShotValueException.class, () -> shotService.shot(11));
	}
	
	@Test
	void shouldAddSecondShotLastRoundInvalidScore() {
		BDDMockito.when(roundRepository.findAll()).thenReturn(new ArrayList<Round>(Arrays.asList(new Round(6))));
		Assertions.assertThrows(InvalidShotValueException.class, () -> shotService.shot(7));
	}
}
