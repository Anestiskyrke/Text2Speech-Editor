package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public interface TextToSpeechAPI {

	void playString(String str);
	void setVolume(int vol);
	void setPitch(int pitch);
	void setRate(int rate);		
	
}
