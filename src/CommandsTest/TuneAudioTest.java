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

public class TuneAudioTest {
	
	private Text2SpeechEditorView view;	
	private JMenuItem bt1 = new JMenuItem("Tune Audio");
	private JMenuItem bt2 = new JMenuItem("Tune Audio");
	private JMenuItem bt3 = new JMenuItem("Tune Audio");
	private JTextArea txt = new JTextArea();
	private FakeTextToSpeechAPI fake = new FakeTextToSpeechAPI();
	
	
	@Before
	public void setUp(){
		view = Text2SpeechEditorView.getSingletonView();
		view.setAPI("FakeTTS");
		bt1.setToolTipText("Adjust Volume");
		bt1.addActionListener(new TuneAudio());
		bt2.setToolTipText("Adjust Pitch");
		bt2.addActionListener(new TuneAudio());
		bt3.setToolTipText("Adjust Rate");
		bt3.addActionListener(new TuneAudio());
	}
	
	@Test
	public void test() {
		bt1.doClick(0);
		fake.setVolume(view.getVolume());
		bt2.doClick(0);
		fake.setPitch(view.getPitch());
		bt3.doClick(0);
		fake.setRate(view.getRate());
		Assert.assertEquals(view.getVolume(),fake.getVolume());
		Assert.assertEquals(view.getPitch(),fake.getPitch());
		Assert.assertEquals(view.getRate(),fake.getRate());	
	}

}
