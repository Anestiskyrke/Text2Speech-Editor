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
import text2speechapis.*;

public class DocumentTest1 {
	
	private Text2SpeechEditorView view;	
	private OpenDocument open;
	private Document doc;
	private JMenuItem bt = new JMenuItem("Save");
	private JTextArea txt = new JTextArea();
	private ArrayList<ArrayList<String>> cont;
	private FakeTextToSpeechAPI fake = new FakeTextToSpeechAPI();
	
	
	@Before
	public void setUp(){
		view = Text2SpeechEditorView.getSingletonView();
		txt.setText("Project Test");
		view.setTestArea(txt);
		view.setAPI("FakeTTS");
		open = new OpenDocument();
		bt.setToolTipText("Open Document");
		bt.addActionListener(open);
		doc = new Document();
		view.setCurrentDocument(doc);
		cont = new ArrayList<ArrayList<String>>();
		view.getCurrentDocument().setContents(cont);
	}
	
	@Test
	public void test() {
		bt.doClick(0);
		view.getCurrentDocument().playContents(view.getVolume(), view.getPitch(), view.getRate(), view.getAPI());
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
		fake.playString(words);
		String ap = fake.getPlay();
		Assert.assertEquals(view.getTextArea().getText(),ap);
	}

}
