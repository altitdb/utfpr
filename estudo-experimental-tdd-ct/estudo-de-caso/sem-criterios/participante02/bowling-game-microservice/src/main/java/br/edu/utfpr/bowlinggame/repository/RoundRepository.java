package br.edu.utfpr.bowlinggame.repository;

import org.springframework.stereotype.Repository;

import br.edu.utfpr.bowlinggame.entity.Round;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {

	public default Integer getScore() {
		return 30;
	}

}
