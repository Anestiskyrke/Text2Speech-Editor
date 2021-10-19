package CommandsTest;

import static org.junit.Assert.*;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Model.Document;
import Commands.*;
import View.Text2SpeechEditorView;

public class EditDocumentTest {
	
	private Text2SpeechEditorView view;	
	private EditDocument edit;
	private Document doc;
	private JMenuItem bt = new JMenuItem("Edit");
	private JTextArea txt = new JTextArea();
	private ArrayList<ArrayList<String>> cont;
	
	@Before
	public void setUp(){
		view = Text2SpeechEditorView.getSingletonView();
		txt.setText("Project Test");
		view.setTestArea(txt);
		edit= new EditDocument();
		bt.setToolTipText("Edit Document");
		bt.addActionListener(edit);
		doc = new Document();
		view.setCurrentDocument(doc);
		cont = new ArrayList<ArrayList<String>>();
		view.getCurrentDocument().setContents(cont);
		
	}
	
	@Test
	public void test() {
		bt.doClick(0);
		String words = "";
		String tmp = "";
		for (int i=0; i<view.getCurrentDocument().getContents().size(); i++) {
			for (int j=0; j<view.getCurrentDocument().getContents().get(i).size(); j++) {
				if (j == view.getCurrentDocument().getContents().get(i).size() - 1) {
					tmp += view.getCurrentDocument().getContents().get(i).get(j);
				}else {
					tmp += view.getCurrentDocument().getContents().get(i).get(j) + " ";
				}
				words += tmp;
				tmp = "";
			}
		}
		Assert.assertEquals(view.getTextArea().getText(),words);
	}

}
