import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LifeTest {
private static Life life=new Life(20, 60);
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void testsetGrid() {
		int grid[][]= {{0,1,0},{1,1,0},{1,1,1}};
		life.setGrid(grid);
		Assert.assertArrayEquals(grid, life.getGrid());
	}

	@Test
	void testUpdate() {
		life.update();
		//assertEquals();
		//fail("Not yet implemented");
	}

}
