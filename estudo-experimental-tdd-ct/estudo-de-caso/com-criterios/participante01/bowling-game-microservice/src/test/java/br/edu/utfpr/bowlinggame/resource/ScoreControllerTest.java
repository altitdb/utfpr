package br.edu.utfpr.bowlinggame.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.service.ScoreService;

@WebMvcTest({ ScoreController.class })
class ScoreControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ScoreService scoreService;
	
	@Test
	public void shouldGetScore() throws Exception {
		Mockito.when(scoreService.getScore()).thenReturn(new ScoreDTO(7));
		
		this.mvc.perform(get("/api/v1/score"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.score").value(7));
		
		Mockito.verify(scoreService, Mockito.times(1)).getScore();
	}
	
	
}
