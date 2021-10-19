package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding{

	public String encode(String s) {
		String atBashed = "";
		for (int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        int temp = (int) c;
	        int newc;
	        if       (c >= 'a' && c <= 'z') {
	        	newc = 'a' - temp + 'z';
	        	c = (char)newc;
	        }
	        else if (c >= 'A' && c <= 'Z'){
	        	newc = 'Z' - temp + 'A';
	        	c = (char)newc;
	        }
	        
	        atBashed += c;
	    }
		return atBashed;
	}
}
