package CommandsTest;

import java.util.*;
import javax.swing.JTextArea;
import javax.swing.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Model.Document;
import Commands.*;
import View.Text2SpeechEditorView;
import text2speechapis.*;

public class Rot13EncodeLineTest {
	
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
		view.setEncoding("Rot13");
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
		view.getCurrentDocument().playEncodedContents(view.getVolume(), view.getPitch(), view.getRate(), view.getAPI(), view.getEncoding());
		String words = "";
		String tmp = "";
		String cut = "";
    	for (int j=0;j<view.getCurrentDocument().getContents().get(0).size();j++) {
    		if ( j==view.getCurrentDocument().getContents().get(0).size()-1) {
    			cut += view.getCurrentDocument().getContents().get(0).get(j);
    		}else {
    			cut += view.getCurrentDocument().getContents().get(0).get(j) + " ";
    		}
    	}
		
		String mpla = view.getCurrentDocument().tuneEncodingStrategy(cut,"Rot13");
    	txt.setText(mpla);
    	view.setTestArea(txt);
		view.getCurrentDocument().setContents(new ArrayList<ArrayList<String>>()); 
		String[] tmp_lines = view.getTextArea().getText().split("\\r?\\n");
		for (int i=0;i<tmp_lines.length;i++) {
			ArrayList<String> lines = new ArrayList<String>(Arrays.asList(tmp_lines[i].split(" ")));
			view.getCurrentDocument().getContents().add(lines);
		}
		for (int j=0; j<view.getCurrentDocument().getContents().get(0).size(); j++) {
			if (j == view.getCurrentDocument().getContents().get(0).size() - 1) {
				tmp += view.getCurrentDocument().getContents().get(0).get(j);
			}else {
				tmp += view.getCurrentDocument().getContents().get(0).get(j) + " ";
			}
			words += tmp;
			tmp = "";
		}
		
		fake.playString(view.getTextArea().getText());
		String ap = fake.getPlay();
		Assert.assertEquals(words,ap);
	}

}
