package br.edu.utfpr.bowlinggame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.bowlinggame.entity.Game;
import br.edu.utfpr.bowlinggame.entity.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
	
	Optional<Round> findFirstByGameStatusOrderByGameDateDescRoundNumberDesc(String status);
	
	List<Round> findAllByGameOrderByRoundNumberDesc(Game game);

}
