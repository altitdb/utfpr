package br.edu.utfpr.bowlinggame.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import br.edu.utfpr.bowlinggame.entity.Shot;
import br.edu.utfpr.bowlinggame.repository.ShotRepository;

@SpringBootTest(classes = { ScoreService.class })
class ScoreServiceTest {

    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private ShotRepository shotRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_calculate_score_for_zero_points() {
        when(shotRepository.findAll()).thenReturn(new ArrayList<>());
        Integer score = scoreService.calculateScore();
        assertThat(score).isZero();
    }

    @Test
    void should_calculate_score_for_maximum_points() {
        List<Shot> allShots = Lists.newArrayList(
            buildShot(5, 1, 1),
            buildShot(5, 1, 2),
            buildShot(5, 2, 1),
            buildShot(5, 2, 2),
            buildShot(5, 3, 1),
            buildShot(5, 3, 2),
            buildShot(5, 4, 1),
            buildShot(5, 4, 2),
            buildShot(5, 5, 1),
            buildShot(5, 5, 2),
            buildShot(5, 6, 1),
            buildShot(5, 6, 2),
            buildShot(5, 7, 1),
            buildShot(5, 7, 2),
            buildShot(5, 8, 1),
            buildShot(5, 8, 2),
            buildShot(5, 9, 1),
            buildShot(5, 9, 2),
            buildShot(5, 10, 1),
            buildShot(5, 10, 2)
        );
        when(shotRepository.findAll()).thenReturn(allShots);
        Integer score = scoreService.calculateScore();
        assertThat(score).isEqualTo(100);
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
