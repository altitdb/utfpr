package br.edu.utfpr.bowlinggame.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;


@Profile("test")
@SpringBootTest(classes = { TestConfiguration.class })
class RoundRepositoryTest {

	@Autowired
	private RoundRepository repo;
		
	
	@Test
	public void should_return_the_current_score() {
		var score = repo.getScore();
		
		Assertions.assertThat(score).isEqualTo(30);
		
	}

}
