package br.edu.utfpr.bowlinggame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.entity.Shot;
import br.edu.utfpr.bowlinggame.repository.ShotRepository;

@Service
@ComponentScan("br.edu.utfpr.bowlinggame.repository")
public class ScoreService {

    @Autowired
    private ShotRepository shotRepository;

    public Integer calculateScore() {
        return shotRepository.findAll()
                .stream()
                .mapToInt(Shot::getPinsDropped)
                .sum();
    }

}
