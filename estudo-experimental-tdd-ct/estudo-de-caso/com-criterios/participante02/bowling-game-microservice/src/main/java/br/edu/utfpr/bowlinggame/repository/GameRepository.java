package br.edu.utfpr.bowlinggame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.bowlinggame.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	
	Game findFirstByStatusOrderByDateDesc(String status);

}
