package Persistance;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersistanceStatics {

    public static class PatternProperties {

        public static String getStringProperty(String input, Pattern pattern){
            Matcher propertyMatcher = pattern.matcher(input);
            propertyMatcher.find();
            return propertyMatcher.group(1);
        }

        public static  int getIntProperty(String input, Pattern pattern){
            Matcher propertyMatcher = pattern.matcher(input);
            propertyMatcher.find();
            return Integer.parseInt(propertyMatcher.group(1));
        }

        public static  double getDoubleProperty(String input, Pattern pattern){
            Matcher propertyMatcher = pattern.matcher(input);
            propertyMatcher.find();
            return Double.parseDouble(propertyMatcher.group(1));
        }

    }

    public static class FilePersistance{


        public static boolean FileExists(String stringPath){
            var path = Paths.get(stringPath);
            return Files.exists(path);
        }

        public static void WriteFile(String stringPath, String content){
            try {
                var path = Paths.get(stringPath);
                if(!Files.exists(path)){
                    Files.createFile(path);
                }
                Files.write(path, content.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static String Read(String stringPath){
            try {
                var path = Paths.get(stringPath);
                if(Files.exists(path)){
                    var content = Files.readAllLines(path);
                    return String.join("\n", content);
                }
                else
                    throw new FileNotFoundException();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
