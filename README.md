# JWToken-Generator-Validator

A JAVA library to generate JWT tokens and to validate them
-----------------------------------------------------------------------------------------------------------------------------------
## Documentation

Create an instance for the JWTokenGenerator class
```java

import org.binaryblood.token.generator.JWTokenGenerator;

JwTokenGenerator gen = new JwTokenGenerator();
Properties prop = new Properties();
InputStream input = App.class.getClassLoader().getResourceAsStream("config.properties");
prop.load(input);
System.out.println(gen.getJWTToken(prop)); // Generate JWT Token
```
------------------------------------------------------------------------------------------------------------------------------------
## Create public and private keys

Do the below steps before generating JWT Tokens
Create certificate.pem and secret.pem file with open ssl
```console
openssl req -nodes -text -x509 -newkey rsa:2048 -keyout secret.pem -out certificate.pem -days 356
```
Convert the secret.pem file to secret.key
```console
openssl pkcs8 -topk8 -inform PEM -outform DER -in secret.pem -nocrypt > secret.key
```
------------------------------------------------------------------------------------------------------------------------------------
## Configure the config.properties file

Change the values of the keys for your need before generating the JWT Token
