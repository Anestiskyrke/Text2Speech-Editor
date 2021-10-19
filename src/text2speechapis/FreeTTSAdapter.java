package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {

	Voice voice;
	
	private int volume,pitch,rate;
	
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
	
	    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		VoiceManager voiceManager = VoiceManager.getInstance();
	    voice = voiceManager.getVoice("kevin16");
	    if (voice != null) {
	        voice.allocate();// Allocating Voice
	        try {
	            voice.setRate(rate);// Setting the rate of the voice
	            voice.setPitch(pitch);// Setting the Pitch of the voice
	            voice.setVolume(volume);// Setting the volume of the voice
	            voice.speak(words);// Calling speak() method

	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	    } else {
	        throw new IllegalStateException("Cannot find voice: kevin16");
	    }
	}
	

}
