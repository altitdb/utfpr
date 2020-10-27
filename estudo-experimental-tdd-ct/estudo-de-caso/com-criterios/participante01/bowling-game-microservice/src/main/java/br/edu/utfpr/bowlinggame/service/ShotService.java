package br.edu.utfpr.bowlinggame.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Iterables;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.exception.InvalidShotValueException;
import br.edu.utfpr.bowlinggame.exception.RoundExcededLimitException;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ShotService {

	@Autowired
	RoundRepository roundRepository;
	
	public RoundsDTO shot(Integer shot) {
		if(shot < 0 || shot > 10) {
			throw new InvalidShotValueException();
		}
		
		var rounds = roundRepository.findAll();
		verifyHasManyShot(rounds);
				
		if(rounds.isEmpty()) {
			rounds.add(roundRepository.save(new Round(shot)));
		} else {
			var lastRound = Iterables.getLast(rounds);
			if(lastRound.isComplete()) {
				rounds.add(roundRepository.save(new Round(shot)));
			} else {
				lastRound.addShotTwo(shot);
				roundRepository.save(lastRound);	
			}
		}
		return RoundsDTO.builder()
						.withRounds(rounds.stream()
								.map(item -> new RoundDTO(item.getShotOne(), item.getShotTwo(), item.getScore()))
								.collect(Collectors.toList())).build();
		
	}
	
	private void verifyHasManyShot(List<Round> rounds) {
		if(rounds.size() == 10L && Iterables.getLast(rounds).isComplete()) {
			throw new RoundExcededLimitException();
		}
	}

}
