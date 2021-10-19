package Commands;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenuItem;

import Model.Document;
import View.Text2SpeechEditorView;

public class EditDocument implements ActionListener {

	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();	
	private Document line;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		line = app.getCurrentDocument();
		JMenuItem menuItem = (JMenuItem)e.getSource();
		String option = menuItem.getToolTipText();
        String cut = "";
        String temp_words = "";
        
        if (option.equals("Edit Document")) { 
        	if (line.getContents() != null)
        		line.setContents( new ArrayList<ArrayList<String>>() );
        	String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
    		for (int i=0;i<tmp_lines.length;i++) {
    			ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
    			app.getCurrentDocument().getContents().add(lines);
    		}
        }
        
        else if (option.equals("Whole Document") && app.getEncoding().equals("AtBash")) {
        	
        	line.playEncodedContents(app.getVolume(),app.getPitch(),app.getRate(),app.getAPI(),"AtBash");
        	String mpla = line.tuneEncodingStrategy(app.getTextArea().getText(),"AtBash");
        	app.setTextArea(mpla);
			line.setContents(new ArrayList<ArrayList<String>>()); 
			String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
			for (int i=0;i<tmp_lines.length;i++) {
				ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
				line.getContents().add(lines);
			}
			
        	      	
        	if (app.getEncode_flag() == 0) {
				app.setEncoded(true);
				app.setEncode_flag(1);
			}else if (app.getEncode_flag() == 1) {
				app.setEncode_flag(0);
				app.setEncoded(false);
			}else if (app.getEncode_flag() == 2) {
				app.setEncode_flag(3);
			}else if (app.getEncode_flag() == 3) {
				app.setEncode_flag(2);
			}
        }
        
        else if (option.equals("Specific Line") && app.getEncoding().equals("AtBash")) {

        	line.playEncodedLine(app.getCurrentLine(),app.getVolume(),app.getPitch(),app.getRate(),app.getAPI(),"AtBash");
        	
        	cut = "";
        	for (int j=0;j<line.getContents().get(app.getCurrentLine()).size();j++) {
        		if ( j==line.getContents().get(app.getCurrentLine()).size()-1) {
        			cut += line.getContents().get(app.getCurrentLine()).get(j);
        		}else {
        			cut += line.getContents().get(app.getCurrentLine()).get(j) + " ";
        		}
        	}
        	
        	String mpla = line.tuneEncodingStrategy(cut,"AtBash");
        	
        	if (app.getTextArea().getText().contains(cut)) {
        		temp_words = app.getTextArea().getText().replace(cut,mpla);
        	}
			app.setTextArea(temp_words);
			line.setContents(new ArrayList<ArrayList<String>>()); 
			String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
			for (int i=0;i<tmp_lines.length;i++) {
				ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
				line.getContents().add(lines);
			}
			
			if (app.getEncode_flag() == 0) {
				app.setEncoded(true);
				app.setEncode_flag(1);
			}else if (app.getEncode_flag() == 1) {
				app.setEncode_flag(0);
				app.setEncoded(false);
			}else if (app.getEncode_flag() == 2) {
				app.setEncode_flag(3);
			}else if (app.getEncode_flag() == 3) {
				app.setEncode_flag(2);
			}
        }
        
        else if (option.equals("Whole Document") && app.getEncoding().equals("Rot13")) {
        	
        	line.playEncodedContents(app.getVolume(),app.getPitch(),app.getRate(),app.getAPI(),"Rot13");
        	
        	String mpla = line.tuneEncodingStrategy(app.getTextArea().getText(),"Rot13");
        	app.setTextArea(mpla);
			line.setContents(new ArrayList<ArrayList<String>>()); 
			String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
			for (int i=0;i<tmp_lines.length;i++) {
				ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
				line.getContents().add(lines);
			}
			
			
        	if (app.getEncode_flag() == 0) {
				app.setEncoded(true);
				app.setEncode_flag(2);
			}else if (app.getEncode_flag() == 2) {
				app.setEncode_flag(0);
				app.setEncoded(false);
			}else if (app.getEncode_flag() == 1) {
				app.setEncode_flag(3);
			}else if (app.getEncode_flag() == 3) {
				app.setEncode_flag(1);
			}
        }
        
        else if (option.equals("Specific Line") && app.getEncoding().equals("Rot13")) {
        	
        	line.playEncodedLine(app.getCurrentLine(),app.getVolume(),app.getPitch(),app.getRate(),app.getAPI(),"Rot13");
        	
        	cut = "";
        	for (int j=0;j<line.getContents().get(app.getCurrentLine()).size();j++) {
        		if ( j==line.getContents().get(app.getCurrentLine()).size()-1) {
        			cut += line.getContents().get(app.getCurrentLine()).get(j);
        		}else {
        			cut += line.getContents().get(app.getCurrentLine()).get(j) + " ";
        		}
        	}
        	String mpla = line.tuneEncodingStrategy(cut,"Rot13");
        	
        	if (app.getTextArea().getText().contains(cut)) {
        		temp_words = app.getTextArea().getText().replace(cut,mpla);
        	}
			app.setTextArea(temp_words);
			String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
			line.setContents(new ArrayList<ArrayList<String>>()); 
			for (int i=0;i<tmp_lines.length;i++) {
				ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
				line.getContents().add(lines);
			}
			
        	if (app.getEncode_flag() == 0) {
				app.setEncoded(true);
				app.setEncode_flag(2);
			}else if (app.getEncode_flag() == 2) {
				app.setEncode_flag(0);
				app.setEncoded(false);
			}else if (app.getEncode_flag() == 1) {
				app.setEncode_flag(3);
			}else if (app.getEncode_flag() == 3) {
				app.setEncode_flag(1);
			}
        }
        if (app.getMacroValidation()) {
        	app.getManager().getMacroSaver().add(new EditDocument());
        	app.getManager().getOptions().add(option);
        }
        if (menuItem.getText().contains("Macro ")) {
        	String[] new_opt = menuItem.getText().split("Macro ");
        	String tmp = new_opt[1];
        	int opt = Integer.parseInt(tmp);
        	int i = app.getManager().getCount_opt();
        	app.getManager().setCount_opt(i+1);
        	i = app.getManager().getCount_opt();
        	if (i<=app.getManager().getOptions_list().get(opt - 1).size()) {
        		menuItem.setToolTipText(app.getManager().getOptions_list().get(opt - 1).get(i));
        	}else {
        		app.getManager().setCount_opt(0);
        		menuItem.setToolTipText(app.getManager().getOptions_list().get(opt - 1).get(0));
        	}
        }
	}
}
