package br.edu.utfpr.bowlinggame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ScoreService {

	@Autowired
	private RoundRepository roundRepository;
	
	public ScoreDTO getScore() {
		int sum = roundRepository.findAll().stream().mapToInt(Round::getScore).sum();
		return new ScoreDTO(sum);
	}

}
