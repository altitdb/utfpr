package br.edu.utfpr.bowlinggame.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.dto.RoundsDTO;
import br.edu.utfpr.bowlinggame.service.ShotService;

@WebMvcTest({ ShotController.class })
class ShotControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ShotService shotService;
	
	@Test
	void shouldIncludeShot() throws Exception {
		RoundsDTO rounds = RoundsDTO.builder()
									.withRounds(Collections.singletonList(
											new RoundDTO(34,null,39)
									)).build();

		BDDMockito.when(shotService.shot(9)).thenReturn(rounds);
		
		this.mvc.perform(post("/api/v1/shot/9"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.rounds[0].firstShot").value(34))
	            .andExpect(jsonPath("$.rounds[0].secondShot").isEmpty())
	            .andExpect(jsonPath("$.rounds[0].score").value(39));;
		
		Mockito.verify(shotService, Mockito.times(1)).shot(9);
	}
	
}
