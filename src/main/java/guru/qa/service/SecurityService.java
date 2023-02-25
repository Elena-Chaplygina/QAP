package guru.qa.service;

import guru.qa.domain.Message;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityService {
    public byte[] calculateHash(String inputString) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        return instance.digest(inputString.getBytes(StandardCharsets.UTF_8));


    }
}
