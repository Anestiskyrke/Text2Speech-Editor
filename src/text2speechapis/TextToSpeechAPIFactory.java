package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;



public class TextToSpeechAPIFactory {
	
	public static TextToSpeechAPI createTTSAPI(String api) {
		if (api.equals("FreeTTS")) {
			return new FreeTTSAdapter();
		}else if (api.equals("FakeTTS")) {
			return new FakeTextToSpeechAPI();
		}else {
			throw new IllegalArgumentException();
		}
	}
}
