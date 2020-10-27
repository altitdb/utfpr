package br.edu.utfpr.bowlinggame.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.entity.Shot;
import br.edu.utfpr.bowlinggame.exception.MaximumRoundsExceededException;
import br.edu.utfpr.bowlinggame.repository.ShotRepository;

@SpringBootTest(classes = { ShotService.class })
class ShotServiceTest {

    @InjectMocks
    private ShotService service;

    @Mock
    private ShotRepository shotRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_do_the_first_shot() {
        when(shotRepository.findAll(any(Sort.class))).thenReturn(new ArrayList<>());
        List<RoundDTO> rounds = service.doShot(6);
        verify(shotRepository, times(1)).save(
            buildShot(6, 1, 1)
        );
        RoundDTO firstShotExpected = new RoundDTO(6, 0, 6);
        assertAll(
            () -> assertThat(rounds).hasSize(1),
            () -> assertThat(rounds.get(0)).isEqualTo(firstShotExpected)
        );
    }

    @Test
    void should_do_the_second_shot() {
        List<Shot> previouosShots = Lists.newArrayList(
            buildShot(6, 1, 1)
        );
        when(shotRepository.findAll(any(Sort.class))).thenReturn(previouosShots);
        List<RoundDTO> rounds = service.doShot(1);
        verify(shotRepository, times(1)).save(
                buildShot(1, 1, 2)
        );
        RoundDTO secondShotExpected = new RoundDTO(6, 1, 7);
        assertAll(
            () -> assertThat(rounds).hasSize(1),
            () -> assertThat(rounds.get(0)).isEqualTo(secondShotExpected)
        );
    }

    @Test
    void should_do_the_first_shot_of_second_round() {
        List<Shot> previouosShots = Lists.newArrayList(
            buildShot(6, 1, 1),
            buildShot(1, 1, 2)
        );
        when(shotRepository.findAll(any(Sort.class))).thenReturn(previouosShots);
        List<RoundDTO> rounds = service.doShot(3);
        verify(shotRepository, times(1)).save(
                buildShot(3, 2, 1)
        );
        RoundDTO firstShotOfSecondRoundExpected = new RoundDTO(3, 0, 3);
        assertAll(
            () -> assertThat(rounds).hasSize(2),
            () -> assertThat(rounds.get(1)).isEqualTo(firstShotOfSecondRoundExpected)
        );
    }

    @Test
    void should_do_the_second_shot_of_second_round() {
        List<Shot> previouosShots = Lists.newArrayList(
                buildShot(6, 1, 1),
                buildShot(1, 1, 2),
                buildShot(3, 2, 1)
        );
        when(shotRepository.findAll(any(Sort.class))).thenReturn(previouosShots);
        List<RoundDTO> rounds = service.doShot(4);
        verify(shotRepository, times(1)).save(
                buildShot(4, 2, 2)
        );
        RoundDTO secondShotOfSecondRoundExpected = new RoundDTO(3, 4, 7);
        assertAll(
            () -> assertThat(rounds).hasSize(2),
            () -> assertThat(rounds.get(1)).isEqualTo(secondShotOfSecondRoundExpected)
        );
    }

    @Test
    void should_not_allow_eleventh_round() {
        List<Shot> previouosShots = Lists.newArrayList(
                buildShot(6, 1, 1),
                buildShot(1, 1, 2),
                buildShot(3, 2, 1),
                buildShot(4, 2, 2),
                buildShot(4, 3, 1),
                buildShot(4, 3, 2),
                buildShot(4, 4, 1),
                buildShot(4, 4, 2),
                buildShot(4, 5, 1),
                buildShot(4, 5, 2),
                buildShot(4, 6, 1),
                buildShot(4, 6, 2),
                buildShot(4, 7, 1),
                buildShot(4, 7, 2),
                buildShot(4, 8, 1),
                buildShot(4, 8, 2),
                buildShot(4, 9, 1),
                buildShot(4, 9, 2),
                buildShot(4, 10, 1),
                buildShot(4, 10, 2)
        );
        when(shotRepository.findAll(any(Sort.class))).thenReturn(previouosShots);
        assertThrows(MaximumRoundsExceededException.class, () -> {
            service.doShot(4);
        });
    }

    private Shot buildShot(int pinsDropped, int round, int roundShotNumber) {
        return Shot.newBuilder()
                .withId(UUID.randomUUID())
                .withPinsDropped(pinsDropped)
                .withRound(round)
                .withRoundShotNumber(roundShotNumber)
                .build();
    }
}
