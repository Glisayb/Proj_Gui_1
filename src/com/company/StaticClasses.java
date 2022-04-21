package com.company;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

public class StaticClasses {

    public static class IdGenerator {

        public static String Generate() {
            UUID uuid = UUID.randomUUID();
            return (uuid.toString());
        }
    }

    public static class Timer {


        private static LocalDate date = LocalDate.MIN;
        public Timer(){
            date = LocalDate.MIN;
        }

        public static LocalDate getDate() {
            return date;
        }
        public static LocalDate getDDate() {
            return LocalDate.now();
        }
    }
}