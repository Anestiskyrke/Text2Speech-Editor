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

public class TuneEncodingTest {
	
	private Text2SpeechEditorView view;	
	private JMenuItem bt1 = new JMenuItem("Tune Encoding");
	private JTextArea txt = new JTextArea();
	private String enc;
	
	@Before
	public void setUp(){
		view = Text2SpeechEditorView.getSingletonView();
		view.setAPI("FakeTTS");
		enc = "encodingstrategies.AtBashEncoding"; // AtBash Default
		bt1.setToolTipText("Select Rot13");
		bt1.addActionListener(new TuneEncoding());
		view.setCurrentDocument(new Document());
	}
	
	@Test
	public void test() {
		bt1.doClick(0);
		view.getCurrentDocument().tuneEncodingStrategy("", view.getEncoding());
		Assert.assertFalse(enc.equals(view.getCurrentDocument().getStrat().getClass().getName()));
	}

}
