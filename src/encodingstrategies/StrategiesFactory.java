package encodingstrategies;

public class StrategiesFactory {

	public static EncodingStrategy createStrategy(String strat){
		if (strat.equals("AtBash")) {
			return new AtBashEncoding();
		}else if (strat.equals("Rot13")) {
			return new Rot13Encoding();
		}else {
			throw new IllegalArgumentException();
		}
	}
	
}
