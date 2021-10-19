package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.io.*;
import java.util.*;
import View.*;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

public class Line {
	

	private TextToSpeechAPI audiomanager;
	private EncodingStrategy strat; // AtBash Default at first
	private String encoded_string;
	private ArrayList<String> words = new ArrayList<String>();
	private String new_line;

	
	public void playLine(int Volume,int Pitch,int Rate,String api) {
		new_line = "";
		audiomanager = TextToSpeechAPIFactory.createTTSAPI(api);
		audiomanager.setVolume(Volume);
		audiomanager.setPitch(Pitch);
		audiomanager.setRate(Rate);
		for (int i = 0; i < words.size(); i++) { 
			new_line += words.get(i) + " ";
        } 
		audiomanager.playString(new_line);
	}
	
	public void playReverseLine(int Volume,int Pitch,int Rate,String api) {
		new_line = "";
		ArrayList<String> templine = words;
		audiomanager = TextToSpeechAPIFactory.createTTSAPI(api);
		audiomanager.setVolume(Volume);
		audiomanager.setPitch(Pitch);
		audiomanager.setRate(Rate);
		Collections.reverse(templine);
		for (int i=0;i<templine.size();i++) {
			new_line += templine.get(i) + " ";
		}
		Collections.reverse(words);
		audiomanager.playString(new_line);
	}
	
	public void playEncodedLine(int Volume,int Pitch,int Rate,String api,String strat) {
		new_line = "";
		audiomanager = TextToSpeechAPIFactory.createTTSAPI(api);
		audiomanager.setVolume(Volume);
		audiomanager.setPitch(Pitch);
		audiomanager.setRate(Rate);
		for(int i=0;i< words.size();i++) {
			new_line += tuneEncodingStrategy(words.get(i),strat) + " ";
		}
		audiomanager.playString(new_line);
	}
	
	public String tuneEncodingStrategy(String words, String strategy) {
		strat = StrategiesFactory.createStrategy(strategy);
		encoded_string = strat.encode(words);
		return encoded_string;
	}
	
	public void setWords(ArrayList<String> words) {
		this.words = words;
	}
	
	
}
