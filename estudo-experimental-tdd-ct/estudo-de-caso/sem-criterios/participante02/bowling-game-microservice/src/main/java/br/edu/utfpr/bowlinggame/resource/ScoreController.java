package br.edu.utfpr.bowlinggame.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;

@RestController
public class ScoreController {

	
	@GetMapping("/api/v1/score")
	public ResponseEntity<ScoreDTO> getScore(){
		return ResponseEntity.ok(new ScoreDTO());
	}
	
}
