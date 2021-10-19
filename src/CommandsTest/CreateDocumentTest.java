package CommandsTest;

import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import org.junit.Assert;
import org.junit.Test;

import Model.Document;
import Commands.*;
import View.Text2SpeechEditorView;

public class CreateDocumentTest {
	
	private Text2SpeechEditorView view;	
	private JMenuItem doc = new JMenuItem("Create New Document");
	private JTextArea txt = new JTextArea();
	
	public CreateDocumentTest() {
		view = Text2SpeechEditorView.getSingletonView();
		txt.setText(null);
		view.setTestArea(txt);
		doc.addActionListener(new NewDocument());
	}
	
	
	@Test
	public void test() {
		doc.doClick(0);
		Assert.assertEquals(null,view.getCurrentDocument().getContents());
	}

}
