package br.edu.utfpr.bowlinggame.repository;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.edu.utfpr.bowlinggame.entity.Round;

@Profile("test")
@SpringBootTest(classes = { TestConfiguration.class })
class RoundRepositoryTest {

	@Autowired
	private RoundRepository roundRepository;
	
	@Test
	void shouldReturnZeroNotExsistsShots() {
		roundRepository.deleteAll();
		
		Integer score = roundRepository.getScore();
		
		Assertions.assertSame(0, score);
	}

	@Test
	void shouldSumScore() {
		Round round = Round.builder()
				.withId(UUID.randomUUID())
				.withScore(46)
				.build();
		
		roundRepository.save(round);
		Integer score = roundRepository.getScore();
		
		Assertions.assertSame(46, score);
	}
}
