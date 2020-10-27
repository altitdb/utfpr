package br.edu.utfpr.bowlinggame.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.bowlinggame.entity.Shot;

@Repository
public interface ShotRepository extends JpaRepository<Shot, UUID> {

}
