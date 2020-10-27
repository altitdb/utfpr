package br.edu.utfpr.bowlinggame.resource;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.ScoreDTO;
import br.edu.utfpr.bowlinggame.service.ScoreService;

@WebMvcTest({ ScoreController.class })
class ScoreControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ScoreService scoreService;
	
	@Test
	public void shouldReturnSocre() throws Exception {
		BDDMockito.when(scoreService.getScore()).thenReturn(new ScoreDTO(10));
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/score")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.score").value(10));
		
		BDDMockito.verify(scoreService).getScore();
	}


}
