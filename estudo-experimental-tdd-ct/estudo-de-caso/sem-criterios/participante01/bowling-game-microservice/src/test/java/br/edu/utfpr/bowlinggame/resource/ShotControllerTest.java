package br.edu.utfpr.bowlinggame.resource;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.edu.utfpr.bowlinggame.dto.RoundDTO;
import br.edu.utfpr.bowlinggame.service.ShotService;

@WebMvcTest({ ShotController.class })
class ShotControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ShotService shotService;

	@Mock
	RoundDTO roundDTO;

	@Test
	public void shouldPostNewShot() throws Exception {
		BDDMockito.when(shotService.shot(10)).thenReturn(new RoundDTO(10, 0, 0, 10));

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/shot/10")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.rounds[0].firstShot").value(10))
				.andExpect(MockMvcResultMatchers.jsonPath("$.rounds[0].secondShot").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.rounds[0].bonus").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.rounds[0].score").value(10));
	}

}
