package ru.lisin.bazopt.services.impl;

import org.apache.commons.codec.Decoder;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.services.EncryptorService;

@Service
public class EncryptorServiceImpl implements EncryptorService {
    @Override
    public String encrypt(String message) {
        return DigestUtils.sha3_256Hex(message);
    }
}
