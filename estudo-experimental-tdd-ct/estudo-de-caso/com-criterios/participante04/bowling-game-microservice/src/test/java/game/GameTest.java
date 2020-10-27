package game;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GameTest {
	
	@Test
	public void shouldBeStrikeStatus() {
		Game game = new Game();
		game.roll(10);
		
		Assertions.assertEquals("strike", game.getStatus());
	}
	
}
