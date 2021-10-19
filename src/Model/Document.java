package Model;



import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Timer;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import text2speechapis.*;
import encodingstrategies.*;


public class Document {

	private TextToSpeechAPI audiomanager;
	private EncodingStrategy strat ; 
	private Line line = new Line();
	private String encoded_string;
	private String author;
	private String title;
	private Date creation_time;
	private Date save_time;
	private ArrayList<ArrayList<String>> contents;
	
	
	public void playContents(int Volume,int Pitch,int Rate,String api) {
		String words = "";
		audiomanager = TextToSpeechAPIFactory.createTTSAPI(api);
		audiomanager.setVolume(Volume);
		audiomanager.setPitch(Pitch);
		audiomanager.setRate(Rate);
		
		for (ArrayList<String> arr1 : contents) {
			for (String str : arr1) {
				words += str + " ";
			}
		}

		audiomanager.playString(words);
		
	}
	
	public void playReverseContents(int Volume,int Pitch,int Rate,String api) {
		String words = "";
		ArrayList<ArrayList<String>> templist = contents;
		audiomanager = TextToSpeechAPIFactory.createTTSAPI(api);
		audiomanager.setVolume(Volume);
		audiomanager.setPitch(Pitch);
		audiomanager.setRate(Rate);
		for (int i=0;i<templist.size();i++) {
			Collections.reverse(templist.get(i));
		}
		Collections.reverse(templist);
		for (ArrayList<String> arr1 : templist) {
			for (String str : arr1) {
				words += str + " ";
			}
		}
		for (int i=0;i<contents.size();i++) {
			Collections.reverse(contents.get(i));
		}
		Collections.reverse(contents);
		audiomanager.playString(words);
	}
	
	public void playEncodedContents(int Volume,int Pitch,int Rate,String api,String strat) {
		String words = "";
		audiomanager = TextToSpeechAPIFactory.createTTSAPI(api);
		audiomanager.setVolume(Volume);
		audiomanager.setPitch(Pitch);
		audiomanager.setRate(Rate);
		for (ArrayList<String> arr1 : contents) {
			for (String str : arr1) {
				words += tuneEncodingStrategy(str,strat) + " ";
			}
		}
		audiomanager.playString(words);
	}
	
	public Line getLine() {
		return line;
	}
	
	public void playLine(int currentLine, int Volume,int Pitch,int Rate,String api) {
		line.setWords(contents.get(currentLine));
		line.playLine(Volume, Pitch, Rate,api);
	}
	
	public void playReverseLine(int currentLine,int Volume,int Pitch,int Rate,String api) {
		line.setWords(contents.get(currentLine));
		line.playReverseLine(Volume, Pitch, Rate,api);
	}
	
	public void playEncodedLine(int currentLine,int Volume,int Pitch,int Rate,String api,String strat) {
		line.setWords(contents.get(currentLine));
		line.playEncodedLine(Volume, Pitch, Rate,api,strat);
	}
	
	
	public String tuneEncodingStrategy(String words, String strategy) {
		strat = StrategiesFactory.createStrategy(strategy);
		encoded_string = strat.encode(words);
		return encoded_string;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationTime() {
		return creation_time;
	}

	public void setCreationTime(Date creation_time) {
		this.creation_time = creation_time;
	}

	public ArrayList<ArrayList<String>> getContents() {
		return contents;
	}

	public void setContents(ArrayList<ArrayList<String>> contents) {
		this.contents = contents;
	}

	public Date getSaveTime() {
		return save_time;
	}

	public void setSaveTime(Date save_time) {
		this.save_time = save_time;
	}
	
	public EncodingStrategy getStrat() {
		return strat;
	}

}
