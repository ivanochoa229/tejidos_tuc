package com.tejidos;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SelectPackages("com.tejidos.service")
class TejidosTucApplicationTests {

	@Test
	void contextLoads() {
	}

}
