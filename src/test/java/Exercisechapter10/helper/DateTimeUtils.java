package Exercisechapter10.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
      public static String getCurrentDateTime(String pattern){
            DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime currentDateTime = LocalDateTime.now();
            return currentDateTime.format(format);
      }

      public static String getPlusDateTime(String pattern, int addDays){
            DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime plusDateTime = LocalDateTime.now().plusDays(addDays);
            return plusDateTime.format(format);
      }

}

