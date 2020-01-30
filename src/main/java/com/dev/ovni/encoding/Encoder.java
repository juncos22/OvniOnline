package com.dev.ovni.encoding;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder {
    private String rawPassword;
    private static Encoder encoder;

    public static Encoder getInstance(String rawPassword) {
        if (encoder == null) {
            encoder = new Encoder(rawPassword);
        }
        return encoder;
    }

    private Encoder(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String encode() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(this.rawPassword);
    }
}
