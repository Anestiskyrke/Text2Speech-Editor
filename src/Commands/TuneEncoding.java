package Commands;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import View.Text2SpeechEditorView;

public class TuneEncoding implements ActionListener{

	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem menuItem = (JMenuItem)e.getSource();
        String option = menuItem.getToolTipText();
        if (option.equals("Select AtBash")) {
        	app.setEncoding("AtBash");
        }
        else if (option.equals("Select Rot13")) {
        	app.setEncoding("Rot13");
        }
        
        if (menuItem.getText().contains("Macro ")) {
        	String[] new_opt = menuItem.getText().split("Macro ");
        	String tmp = new_opt[1];
        	int opt = Integer.parseInt(tmp);
        	int i = app.getManager().getCount_opt();
        	app.getManager().setCount_opt(i+1);
        	i = app.getManager().getCount_opt();
        	if (i<app.getManager().getOptions_list().get(opt-1).size()) {
        		menuItem.setToolTipText(app.getManager().getOptions_list().get(opt - 1).get(i));
        	}else {
        		app.getManager().setCount_opt(0);
        	}
        }
	}
}
