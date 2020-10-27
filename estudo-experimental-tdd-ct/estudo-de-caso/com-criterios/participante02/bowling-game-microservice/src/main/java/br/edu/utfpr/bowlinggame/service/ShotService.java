package br.edu.utfpr.bowlinggame.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.GameRepository;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ShotService {
	
	private final RoundRepository roundRepository;
	private final GameService gameService;
	private final ScoreService scoreService;
	
	public ShotService(RoundRepository roundRepository, GameService gameService, ScoreService scoreService) {
		this.roundRepository = roundRepository;
		this.gameService = gameService;
		this.scoreService = scoreService;
	}
	
	public Round shoot(int score) {
		validateShoot(score);
		var currentRound = this.currentRoundForPlay();
		if (currentRound.hasFinished()) {
			currentRound = createNextRound(currentRound);
		}
		updateRound(currentRound, score);
		return currentRound;
		
	}
	
	public List<Round> allRoundsInfo() {
		var currentGame = this.currentRound().thisGame();
		return this.roundRepository.findAllByGameOrderByRoundNumberDesc(currentGame);
	}
	
	public Round currentRoundForPlay() {
		var round = this.roundRepository.findFirstByGameStatusOrderByGameDateDescRoundNumberDesc("STARTED")
				.orElseGet(this::createFirstRound);
		if (round.hasFinished()) {
			return createNextRound(round);
		}
		return round;
	}
	
	public Round currentRound() {
		return this.roundRepository.findFirstByGameStatusOrderByGameDateDescRoundNumberDesc("STARTED").get();
	}
	
	private void validateShoot(int score) {
		if (score < 0 || score > 10) {
			throw new RuntimeException("Pontuação Inválida");
		}
	}
	
	private int updateRound(Round round, int score) {
		var scoreCalculated = round.scored(score);
		this.roundRepository.save(round);
		includeBonusOnBeforeRounds(round, score);
		return scoreCalculated;
	}
	
	private void includeBonusOnBeforeRounds(Round round, int score) {
		this.roundRepository.findAllByGameOrderByRoundNumberDesc(round.thisGame())
				.stream()
				.filter(thisRoundTest -> !thisRoundTest.equals(round))
				.limit(round.thisGame().howMuchBonuses())
				.forEach(roundToApplyBonus -> {
						roundToApplyBonus.addBonus(score);
						this.roundRepository.save(roundToApplyBonus);
						round.thisGame().decreaseBonusBall();
						this.gameService.updateGame(round.thisGame());
					});
	}
	
	private Round createNextRound(Round round) {
		var nextRound = new Round(round.thisGame(), 
				                  round.thisRoundNumber() + 1);
		includeBonus(round);
		return this.roundRepository.saveAndFlush(nextRound);
	}
	
	private void includeBonus(Round round) {
		var game = round.thisGame();
		if (round.spare()) {
			game.increaseBonusOnBall(1);
		}
		if (round.strike()) {
			game.increaseBonusOnBall(2);
		}
		this.gameService.updateGame(game);
	}
	
	private Round createFirstRound() {
		var game = this.gameService.newGame();
		return this.roundRepository.saveAndFlush(new Round(game));
	}

}
