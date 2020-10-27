package br.edu.utfpr.bowlinggame.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.service.ShotService;

@RestController
public class ShotController {

	@Autowired
	private ShotService shotService;

	@PostMapping("/api/v1/shot/{shot}")
	public ResponseEntity<RoundsDTO> postShot(@PathVariable("shot") int shot) {
		RoundDTO round = shotService.shot(shot);
		return ResponseEntity.ok(new RoundsDTO(List.of(round)));
	}

}
