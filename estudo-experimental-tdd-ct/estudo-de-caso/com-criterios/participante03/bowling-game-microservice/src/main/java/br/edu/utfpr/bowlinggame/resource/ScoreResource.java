package br.edu.utfpr.bowlinggame.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.service.ScoreService;

@RestController
public class ScoreResource {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/api/v1/score")
    public ResponseEntity<Integer> getScore() {
        Integer score = scoreService.calculateScore();
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
