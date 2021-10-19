package Commands;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Model.Document;
import View.Text2SpeechEditorView;
import java.util.*;

public class NewDocument implements ActionListener {
	
	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();

	public void actionPerformed(ActionEvent e) {
		app.setCurrentDocument(new Document());
		JFrame f = new JFrame("textfield"); 
		JLabel l1 = new JLabel("Enter Author"); 
		JButton b1 = new JButton("Submit Author"); 
		JLabel l2 = new JLabel("Enter title");
		JTextField t1 = new JTextField(16); 
		JTextField t2 = new JTextField(17);
		JButton b2 = new JButton("Submit Title"); 
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        app.getCurrentDocument().setAuthor(t1.getText());
		        if (app.getCurrentDocument().getTitle() != null && !app.getCurrentDocument().getTitle().equals("")) {
		        	app.getCurrentDocument().setCreationTime(new Date());
		        	app.setTextArea(null);
		        	app.getCurrentDocument().setContents(new ArrayList<ArrayList<String>>());
		        	f.dispose();
		        }
		    }
		});
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.getCurrentDocument().setTitle(t2.getText());
				if (app.getCurrentDocument().getAuthor() != null && !app.getCurrentDocument().getAuthor().equals("")) {
		        	app.getCurrentDocument().setCreationTime(new Date());
		        	app.setTextArea(null);
		        	app.getCurrentDocument().setContents(new ArrayList<ArrayList<String>>());
		        	f.dispose();
		        }
			}
		});
		JPanel p = new JPanel(); 
		p.add(l1); 
		p.add(b1); 
		p.add(t1);
		p.add(l2);
		p.add(b2);
		p.add(t2);
		f.add(p); 
		f.setSize(450, 300);
		f.setVisible(true);
		
	}

}
