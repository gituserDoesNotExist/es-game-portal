package org.commons.test;

import org.commons.FlywayInitializer;
import org.junit.BeforeClass;

public class AbstractTest {

	@BeforeClass
	public static void initializeDb() {
		FlywayInitializer.initialize();
	}

}
