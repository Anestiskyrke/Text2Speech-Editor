package Commands;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import java.io.*;
import java.util.*;
import View.*;
import Model.*;


public class LineToSpeech implements ActionListener {

	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();
	private Document line;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		line = app.getCurrentDocument();
		// TODO Auto-generated method stub
		JMenuItem menuItem = (JMenuItem)e.getSource();
		String option = menuItem.getToolTipText();
	
		
        if (option.equals("Transform Line")) {
        	line.playLine(app.getCurrentLine(),app.getVolume(),app.getPitch(),app.getRate(),app.getAPI());
        }
        else if (option.equals("Reverse Transform Line")) {
        	line.playReverseLine(app.getCurrentLine(),app.getVolume(),app.getPitch(),app.getRate(),app.getAPI());
        }
        if (app.getMacroValidation()) {
        	app.getManager().getMacroSaver().add(new LineToSpeech());
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
