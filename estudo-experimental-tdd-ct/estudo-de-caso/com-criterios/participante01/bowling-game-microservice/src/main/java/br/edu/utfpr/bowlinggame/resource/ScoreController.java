package br.edu.utfpr.bowlinggame.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.service.ScoreService;

@RestController
public class ScoreController {

	@Autowired
	ScoreService scoreService;
	
	@GetMapping("/api/v1/score")
	public ScoreDTO getScore() {
		return this.scoreService.getScore();
	}
}
