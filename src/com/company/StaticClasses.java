package com.company;
import java.time.LocalDate;
import java.util.UUID;

public class StaticClasses {

    public static class IdGenerator {

        public static String Generate() {
            UUID uuid = UUID.randomUUID();
            return (uuid.toString());
        }
    }

    public static class Timer {

        private static LocalDate date;
        public Timer(){
            date = LocalDate.MIN;
        }

        public static LocalDate getDate() {
            return date;
        }


    }
}