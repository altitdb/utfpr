package br.edu.utfpr.bowlinggame.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.service.ShotService;

@RestController
public class ShotController {
	
	@Autowired
	private ShotService shotService;
	
	@PostMapping("/api/v1/shot/{shot}")
	RoundsDTO addShot(@PathVariable Integer shot) {
		return shotService.shot(shot);
	}
}
