package br.edu.utfpr.bowlinggame.service;

import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.*;

import br.edu.utfpr.bowlinggame.repository.TestConfiguration;

@SpringBootTest(classes = { ScoreService.class, TestConfiguration.class })
class ScoreServiceTest {
	
	@Autowired
	private ScoreService scoreService;
	
	@Test
	public void given_NewGameRest_when_VerifyScore_then_ScoreShouldBeZero() {
		long score = scoreService.nowScore();
		assertThat(score, is(0L));
	}
}
