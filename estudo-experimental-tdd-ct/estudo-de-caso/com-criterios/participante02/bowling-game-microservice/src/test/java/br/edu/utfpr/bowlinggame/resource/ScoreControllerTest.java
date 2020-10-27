package br.edu.utfpr.bowlinggame.resource;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.utfpr.bowlinggame.repository.TestConfiguration;
import br.edu.utfpr.bowlinggame.service.ScoreService;

@WebMvcTest({ ScoreController.class, ScoreService.class })
class ScoreControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ScoreService scoreService;
	
	@Test
	public void given_ScoreController_when_AskForScore_then_ReturnNowScore() throws Exception {
		mockMvc.perform(get("/api/v1/score")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}
	
}
