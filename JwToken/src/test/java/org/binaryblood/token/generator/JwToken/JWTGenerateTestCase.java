package org.binaryblood.token.generator.JwToken;

import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JWTGenerateTestCase extends TestCase {
	JwTokenGenerator gen;
	Properties prop ;
	@Before
	public void setUp() throws Exception {
		gen = new JwTokenGenerator();
		prop = new Properties();
	}

	@After
	public void tearDown() throws Exception {
       
	}

	@Test
	public void test() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		InputStream input = JWTGenerateTestCase.class.getClassLoader().getResourceAsStream("config.properties");
		prop.load(input);
		System.out.println(gen.getJWTToken(prop));
	}
}
