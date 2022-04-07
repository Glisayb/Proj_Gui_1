package com.company;
import java.util.UUID;

public class StaticClasses {

    public static class IdGenerator {

        public static String Generate() {
            UUID uuid = UUID.randomUUID();
            return (uuid.toString());
        }
    }
}