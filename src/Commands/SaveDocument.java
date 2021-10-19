package Commands;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Document;
import View.Text2SpeechEditorView;

public class SaveDocument implements ActionListener{
	
	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();

	
	public void actionPerformed(ActionEvent e) {
		
		JMenuItem menuItem = (JMenuItem)e.getSource();
		app.setCurrentDocument(new Document());
		app.getCurrentDocument().setContents( new ArrayList<ArrayList<String>>() );
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text File", "txt");
		final JFileChooser saveAsFileChooser = new JFileChooser();
		saveAsFileChooser.setApproveButtonText("Save");
		saveAsFileChooser.setFileFilter(extensionFilter);
		int actionDialog = saveAsFileChooser.showSaveDialog(app.getFrame());
		if (actionDialog != JFileChooser.APPROVE_OPTION) {
			return;
		}

		// !! File fileName = new File(SaveAs.getSelectedFile() + ".txt");
		File file = saveAsFileChooser.getSelectedFile();
		if (!file.getName().endsWith(".txt")) {
			file = new File(file.getAbsolutePath() + ".txt");
		}
		
		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter(new FileWriter(file));
			app.getTextArea().write(outFile);
			String[] tmp_lines = app.getTextArea().getText().split("\\r?\\n");
			for (int i=0;i<tmp_lines.length;i++) {
				ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
				app.getCurrentDocument().getContents().add(lines);
			}
			
			app.getCurrentDocument().setSaveTime(new Date());
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (outFile != null) {
				try {
					outFile.close();
				} catch (IOException e2) {}
			}
		}
		if (app.getMacroValidation()) {
        	app.getManager().getMacroSaver().add(new DocumentToSpeech());
        	app.getManager().getOptions().add("");
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