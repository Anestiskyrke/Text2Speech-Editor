package Commands;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JMenuItem;

import View.*;
import Model.*;

public class DocumentToSpeech implements ActionListener {
	
	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();
	private Document doc;
	// might not get the text from textArea
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		doc = app.getCurrentDocument();
		JMenuItem menuItem = (JMenuItem)e.getSource();
		String option = menuItem.getToolTipText();
	
		
        if (option.equals("Transform Document")) {
        	doc.playContents(app.getVolume(),app.getPitch(),app.getRate(),app.getAPI());
        }
        else if (option.equals("Reverse Transform Document")) {
        	doc.playReverseContents(app.getVolume(),app.getPitch(),app.getRate(),app.getAPI());
        }
        if (app.getMacroValidation()) {
        	app.getManager().getMacroSaver().add(new DocumentToSpeech());
        	app.getManager().getOptions().add(option);
        }
        if (menuItem.getText().contains("Macro ")) {
        	String[] new_opt = menuItem.getText().split("Macro ");
        	String tmp = new_opt[1];
        	int opt = Integer.parseInt(tmp);
        	int i = app.getManager().getCount_opt();
        	app.getManager().setCount_opt(i+1);
        	i = app.getManager().getCount_opt();
        	if (i<=app.getManager().getOptions_list().get(opt-1).size()) {
        		menuItem.setToolTipText(app.getManager().getOptions_list().get(opt - 1).get(i));
        	}else {
        		app.getManager().setCount_opt(0);
        		menuItem.setToolTipText(app.getManager().getOptions_list().get(opt - 1).get(0));
        	}
        }
	}
	
}
