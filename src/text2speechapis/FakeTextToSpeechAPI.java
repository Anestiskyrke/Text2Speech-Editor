package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class FakeTextToSpeechAPI implements TextToSpeechAPI {

	private int volume,pitch,rate;
	private static String play;
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public void playString(String words) {
		play = words;
	}

	public String getPlay() {
		return play;
	}
	
	public int getVolume() {
		return volume;
	}
	public int getPitch() {
		return pitch;
	}
	public int getRate() {
		return rate;
	}
	
}
