package br.edu.utfpr.bowlinggame.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({ ScoreController.class })
class ScoreControllerTest {
	
	@Autowired
	private MockMvc api;
	
	@Test
	public void should_return_the_current_game_store() throws Exception {
		api.perform(get("/api/v1/score"))
			.andExpect(jsonPath("$.score").value("30"));
		
	}
	
	@Test
	public void should_be_status_ok_when_get_the_current_game_store_successfuly() throws Exception {
		api.perform(get("/api/v1/score"))
			.andExpect(status().is(200));
		
	}
	

}
