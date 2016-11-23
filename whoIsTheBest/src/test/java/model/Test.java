package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class Test {
	@Test
	public void testKdRatio() {
		Statistics statistics = new Statistics();
		statistics.setKills(20);
		statistics.setDeaths(10);
		assertEquals(2.0, statistics.getKdRatio());
	}
}
