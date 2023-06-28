package 메소드모음;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	public static String extractNumbers(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        StringBuilder sb = new StringBuilder();
        
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        
        return sb.toString();
    }
}
