package br.edu.utfpr.bowlinggame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.ManyMovesException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ShotService {

	@Autowired
	private RoundRepository roundRepository;

	public RoundDTO shot(int shot) {
		if (roundRepository.count() > 10) {
			throw new ManyMovesException();
		}

		Rounds rounds = new Rounds(roundRepository);
		Optional<Round> lastRound = rounds.last();

		Round round = null;

		if (lastRound.isEmpty()) {
			round = new Round(shot);
		} else if (lastRound.get().isCompleted()) {
			int nextRoundNumber = lastRound.get().getNumber() + 1;
			round = new Round(shot, nextRoundNumber);
		} else {
			round = lastRound.get();
			round.setSecondShot(shot);
		}

		roundRepository.save(round);
		return new RoundDTO(round);
	}

}
