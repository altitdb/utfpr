package br.edu.utfpr.bowlinggame.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@Profile("test")
@SpringBootTest(classes = { TestConfiguration.class })
class RoundRepositoryTest {


}
