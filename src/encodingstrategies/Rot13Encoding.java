package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding{
	
	public String encode(String s) {
		String rot13nd = "";
		for (int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        if       (c >= 'a' && c <= 'm') c += 13;
	        else if  (c >= 'A' && c <= 'M') c += 13;
	        else if  (c >= 'n' && c <= 'z') c -= 13;
	        else if  (c >= 'N' && c <= 'Z') c -= 13;
	        rot13nd += c;
	    }
		return rot13nd;
	}

}
