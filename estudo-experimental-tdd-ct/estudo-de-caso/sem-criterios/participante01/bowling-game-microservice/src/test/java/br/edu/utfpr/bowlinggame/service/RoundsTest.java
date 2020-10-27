package br.edu.utfpr.bowlinggame.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

public class RoundsTest {

	private Rounds rounds;

	@Mock
	private RoundRepository repository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void whenZeroMovies_lastRoundShouldBeEmpty() {
		Mockito.when(repository.findAll()).thenReturn(List.of());
		rounds = new Rounds(repository);

		Optional<Round> last = rounds.last();

		Assertions.assertTrue(last.isEmpty());
	}

	@Test
	public void whenOneMovie_lastRoundShouldNotBeEmpty() {
		Mockito.when(repository.findAll()).thenReturn(List.of(new Round(1)));
		rounds = new Rounds(repository);

		Optional<Round> last = rounds.last();

		Assertions.assertTrue(last.isPresent());
	}

}
