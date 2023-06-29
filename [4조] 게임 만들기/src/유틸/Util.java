package 유틸;

import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.AbstractButton;
import javax.swing.JPanel;

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
	
	 public static void removeAllButtonFocus(JPanel panel) {
	        Component[] components = panel.getComponents();
	        for (Component component : components) {
	            if (component instanceof AbstractButton) {
	                AbstractButton button = (AbstractButton) component;
	                button.setFocusable(false);
	            }
	        }
	    }
}
