package br.edu.utfpr.bowlinggame.service;

import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.entity.Game;
import br.edu.utfpr.bowlinggame.repository.GameRepository;

@Service
public class GameService {
	
	private final GameRepository gameRepository;
	
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
	
	public Game newGame() {
		return this.gameRepository.save(new Game());
	}

	public void updateGame(Game game) {
		this.gameRepository.save(game);
		
	}

}
