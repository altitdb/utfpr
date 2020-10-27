package br.edu.utfpr.bowlinggame.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.TestConfiguration;

@SpringBootTest(classes = { ShotService.class, TestConfiguration.class, GameService.class, ScoreService.class })
class ShotServiceTest {
	
	@Autowired
	private ShotService shotService;
	
	@Autowired
	private ScoreService scoreService;
	
	@Test
	@DirtiesContext
	public void given_Player_when_Shoots_then_Shoot() {
		this.shotService.shoot(1);
		Round round = this.shotService.shoot(0);
		assertThat(round.score(), is(1));
	}
	
	@Test
	@DirtiesContext
	public void given_Player_when_Score1OnFirstShotAnd3OnSeccondShot_then_ScoreShouldBe4() {
		this.shotService.shoot(1);
		Round round = this.shotService.shoot(3);
		assertThat(round.score(), is(4));
	}
	
	@Test
	@DirtiesContext
	public void given_Player_when_Score10OnFirstShotAndSeccondShoot5_then_ScoreShouldBe15AndRoundScoreShouldBe5() {
		this.shotService.shoot(10);
		Round round = this.shotService.shoot(5);
		assertThat(round.score(), is(5));
	}
	
	@Test
	@DirtiesContext
	public void given_Player_when_Shoots0AllTimes_then_GameShouldFinishAndResultShouldBeZero() {
		IntStream.range(0, 20)
			.forEach(play -> this.shotService.shoot(0));
		assertThat(this.scoreService.nowScore(), is(0));
		assertThat(this.shotService.currentRound().thisRoundNumber(), is(10));
	}
	
	@Test
	@DirtiesContext
	public void given_Player_when_Shoots10AllTimes_then_GameShouldFinishAndResultShouldBe300() { 
		IntStream.range(0, 12)
			.forEach(play -> this.shotService.shoot(10));
		assertThat(this.scoreService.nowScore(), is(300));
		assertThat(this.shotService.currentRound().thisRoundNumber(), is(10));
	}
	
	@Test
	@DirtiesContext
	public void given_Game_when_shootsNegatively_then_ReturnPontuacaoInvalida() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			this.shotService.shoot(-1);
		});
		String expectedMessage = "Pontuação Inválida";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@DirtiesContext
	public void given_Game_when_shoots11_then_ReturnPontuacaoInvalida() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			this.shotService.shoot(11);
		});
		String expectedMessage = "Pontuação Inválida";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@DirtiesContext
	public void given_Game_when_shoots0AndNegative1_then_ReturnPontuacaoInvalida() {
		this.shotService.shoot(0);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			this.shotService.shoot(-1);
		});
		String expectedMessage = "Pontuação Inválida";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@DirtiesContext
	public void given_Game_when_shoots0And11_then_ReturnPontuacaoInvalida() {
		this.shotService.shoot(0);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			this.shotService.shoot(11);
		});
		String expectedMessage = "Pontuação Inválida";
		String actualMessage = exception.getMessage();
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	@DirtiesContext
	public void given_Game_when_shoots0And10_then_ReturnPontuacaoInvalida() {
		this.shotService.shoot(0);
		this.shotService.shoot(10);
		assertThat(this.scoreService.nowScore(), is(10));
		assertThat(this.shotService.currentRound().thisRoundNumber(), is(1));
	}

}
