package br.edu.utfpr.bowlinggame.resource;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.bowlinggame.service.ShotService;
import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.ShotDTO;
import br.edu.utfpr.bowlinggame.entity.Round;

@RestController
public class ShotController {
	
	@Autowired
	private ShotService shotService;
	
	@PostMapping("/api/v1/shot/{shot}")
	public ResponseEntity<ShotDTO> shoot(@PathVariable("shot") Integer shot) {
		this.shotService.shoot(shot);
		ShotDTO shotDTO = new ShotDTO();
		shotDTO.setRounds(this.shotService.allRoundsInfo().stream()
				.map(this::transformRoundIntoDTO).collect(Collectors.toList()));
		return ResponseEntity.ok(shotDTO);
	}
	
	private RoundDTO transformRoundIntoDTO(Round round) {
		var dto = new RoundDTO();
		dto.setFirstShot(round.getFirstShot());
		dto.setSecondShot(round.getSecondShot());
		dto.setBonus(round.getBonus());
		dto.setScore(round.score());
		return dto;
	}

}
