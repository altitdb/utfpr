package br.edu.utfpr.bowlinggame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.entity.Game;
import br.edu.utfpr.bowlinggame.repository.GameRepository;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ScoreService {
	
	private final GameRepository gameRepository;
	private final RoundRepository roundRepository;
	
	@Autowired
	public ScoreService(GameRepository gameRepository, RoundRepository roundRepository) {
		this.gameRepository = gameRepository;
		this.roundRepository = roundRepository;
	}
	
	public Integer nowScore() {
		var game = this.gameRepository.findFirstByStatusOrderByDateDesc("STARTED");
		return this.roundRepository.findAllByGameOrderByRoundNumberDesc(game)
				.stream()
				.map(round -> round.score())
				.reduce(0, (a,b) -> a + b);
	}

}
