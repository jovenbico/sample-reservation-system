package com.bicjo.resys.config;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SystemConfiguration.class })
public class SystemConfigurationTest {

	@Test
	public void test() throws Exception {
		assertTrue(Boolean.TRUE);
	}

}
