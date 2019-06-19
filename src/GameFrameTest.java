import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameFrameTest {
private static GameFrame gameframe =new GameFrame(null);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testSetMaxRow() {
		gameframe.setMaxRow(20);
		Assert.assertEquals(20, gameframe.getMaxRow());
		gameframe.setMaxRow(21);
		Assert.assertEquals(21, gameframe.getMaxRow());
		gameframe.setMaxRow(3);
		Assert.assertEquals(3, gameframe.getMaxRow());
		gameframe.setMaxRow(2);
		Assert.assertEquals(2, gameframe.getMaxRow());
		
	}

	@Test
	void testSetMaxCol() {
		gameframe.setMaxCol(60);
		Assert.assertEquals(60, gameframe.getMaxCol());
		gameframe.setMaxCol(3);
		Assert.assertEquals(3, gameframe.getMaxCol());
		gameframe.setMaxCol(61);
		Assert.assertEquals(61, gameframe.getMaxCol());
		gameframe.setMaxCol(2);
		Assert.assertEquals(2, gameframe.getMaxCol());
		
	}

	@Test
	void testInitGUI() {
		gameframe.initGUI();
		
	}

	@Test
	void testActionPerformed() {
		//ActionEvent e = null;
		//gameframe.actionPerformed(e);;
		
	}
	

}
