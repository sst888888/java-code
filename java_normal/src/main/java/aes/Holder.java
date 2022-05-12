package aes;

import java.security.SecureRandom;
import java.util.Random;

public class Holder {
    public static final Random RANDOM = new Random();
    public static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public Holder() {
    }
}

