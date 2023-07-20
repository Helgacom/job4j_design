package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte a = 1;
        short s = 1;
        char c = '1';
        int i = 1;
        long l = 1L;
        float f = 1.0f;
        double d = 1.0;
        boolean b = true;
        LOG.debug("Variable list: {}, {}, {}, {}, {}, {}, {}, {}", a, s, c, i, l, f, d, b);

    }
}