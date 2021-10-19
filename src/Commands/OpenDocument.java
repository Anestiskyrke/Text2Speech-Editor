package Commands;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Document;
import View.Text2SpeechEditorView;

public class OpenDocument implements ActionListener{
	
	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();
	
	public void actionPerformed(ActionEvent e) {
		app.setCurrentDocument(new Document());
		app.getCurrentDocument().setContents( new ArrayList<ArrayList<String>>() );
		try {
			JFileChooser chooser = new JFileChooser();
			int value = chooser.showOpenDialog(null);
			if (value == JFileChooser.APPROVE_OPTION) {
				FileInputStream doc =new FileInputStream(chooser.getSelectedFile());
		        Scanner useDelimiter2 = extracted(doc);
				Scanner useDelimiter = useDelimiter2;
				String text =  useDelimiter.next();
				app.setTextArea(text);
				app.getCurrentDocument().setCreationTime(new Date());
				String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
				
				for (int i=0;i<tmp_lines.length;i++) {
					ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
					app.getCurrentDocument().getContents().add(lines);
				}

			}
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "e");
		}
		
	}

	private Scanner extracted(FileInputStream doc) {
		return new Scanner(doc,"UTF-8").useDelimiter("\\A");
	}
}