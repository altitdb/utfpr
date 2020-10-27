package br.edu.utfpr.bowlinggame.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.bowlinggame.entity.Round;

public interface RoundRepository extends JpaRepository<Round, UUID> {

}
