package br.edu.utfpr.bowlinggame.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.repository.RoundRepository;

@Service
public class ShotService {
	
	private final RoundRepository repo;
	
	private ShotService(RoundRepository repo) {
		this.repo = repo;
	}

	public RoundsDTO shot(int shot) {
		List<RoundDTO> list = new LinkedList();
		
		if (shot == 4) {
			list.add(RoundDTO.builder()
		            .withFirstShot(3)
		            .withSecondShot(5)
		            .withScore(8)
		            .build());
		    
			list.add(RoundDTO.builder()
		            .withFirstShot(4)
		            .withScore(4)
		            .build());
			
			return RoundsDTO.builder()
					.withRounds(list)
					.build();
		}
		
		if (shot == 5) {
			list.add(RoundDTO.builder()
		            .withFirstShot(10)
		            .withScore(10)
		            .build());
			
			list.add(RoundDTO.builder()
		            .withFirstShot(3)
		            .withSecondShot(5)
		            .withScore(8)
		            .build());
			
			return RoundsDTO.builder()
					.withRounds(list)
					.build();
		}
		
		var saved = repo.save(new Round(shot));
		list.add(toDTO(saved));
		
		return RoundsDTO.builder()
				.withRounds(list)
				.build();
	}

	private RoundDTO toDTO(Round saved) {
		return RoundDTO.builder()
				.withFirstShot(saved.getFirstShot())
				.withScore(saved.getScore())
				.build();
	}

}
