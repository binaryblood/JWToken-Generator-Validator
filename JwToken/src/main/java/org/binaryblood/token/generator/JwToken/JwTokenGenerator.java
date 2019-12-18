package org.binaryblood.token.generator.JwToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwTokenGenerator {

	public String getJWTToken(Properties prop)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		// Load properties from prop file
		String iss = prop.getProperty("iss");
		String sub = prop.getProperty("sub");
		String aud = prop.getProperty("aud");
		String keyPath = prop.getProperty("key_path");
		// Expiration time set for 24 hours
		Long expirationTime = System.currentTimeMillis() / 1000 + 86400L;
		byte[] privateKeyFileContent = Files.readAllBytes(Paths.get(keyPath));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		KeySpec ks = new PKCS8EncodedKeySpec(privateKeyFileContent);
		RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(ks);

		// Create JWT payload
		Map<String, Object> jwtClaims = new HashMap<>();
		jwtClaims.put("iss", iss);
		jwtClaims.put("sub", sub);
		jwtClaims.put("exp", expirationTime);
		jwtClaims.put("aud", aud);
		// Customize here to add more claims

		SignatureAlgorithm signatureAlgo = SignatureAlgorithm.RS256;
		// Create the final JWT token
		String jwtToken = Jwts.builder().setClaims(jwtClaims).signWith(signatureAlgo, privateKey).compact();

		return jwtToken;
	}
}
