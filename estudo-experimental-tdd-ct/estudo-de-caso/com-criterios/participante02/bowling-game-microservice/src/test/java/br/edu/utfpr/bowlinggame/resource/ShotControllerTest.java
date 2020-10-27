package br.edu.utfpr.bowlinggame.resource;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.edu.utfpr.bowlinggame.entity.Round;
import br.edu.utfpr.bowlinggame.service.ScoreService;
import br.edu.utfpr.bowlinggame.service.ShotService;

@WebMvcTest({ ShotController.class, ShotService.class })
class ShotControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ShotService shotService;
	
	@Test
	public void given_ShotController_when_calledShootForOne_then_ReturnRound1With1Score() throws Exception {
		Mockito.when(shotService.allRoundsInfo()).thenReturn(stubRoundList());
		mockMvc.perform(post("/api/v1/shot/{shot}", 1)
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().json("{rounds: [ {firstShot:1, secondShot:0, bonus: 0, score: 1}]}"));
	}
	
	private List<Round> stubRoundList() {
		Round round = new Round();
		round.scored(1);
		round.setSecondShot(0);
		round.setBonus(0);
		return List.of(round);
	}

}
