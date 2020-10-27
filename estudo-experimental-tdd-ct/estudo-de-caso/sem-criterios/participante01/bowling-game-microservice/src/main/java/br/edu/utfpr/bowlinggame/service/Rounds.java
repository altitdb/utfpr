package br.edu.utfpr.bowlinggame.service;

import java.util.List;
import java.util.Optional;

import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

public class Rounds {

	private final List<Round> list;
	
	public Rounds(RoundRepository repository) {
		this.list = repository.findAll();
	}

	public Optional<Round> last() {
		int last = list.stream().mapToInt(Round::getNumber).max().orElse(0);
		return list.stream().filter(round -> round.getNumber() == last).findFirst();
	}

}
