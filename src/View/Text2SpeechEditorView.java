package View;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import Model.*;
import Commands.*;


public class Text2SpeechEditorView{
	
	private static JFrame frame;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private boolean reversed;
	private boolean encoded;
	private String encoding = "AtBash" ; // AtBash default
	
	private static int defaultVol = 3;
	private static int defaultPitch = 150;
	private static int defaultRate =160 ;
	private int volume = defaultVol;
	private int pitch = defaultPitch;
	private int rate = defaultRate;
	private String api = "FreeTTS";
	
	private boolean MacroValidation = false;
	private ReplayManager manager;
	
	Container cont1;
	private static Text2SpeechEditorView editor = null;
	private static CommandsFactory factory;
	private Document currentDoc;
	private int currentLine;
	private JMenuItem[] file_table;
	private JMenuItem[] edit_table;
	private JMenuItem[] encoding_table;
	private JMenuItem[] transform_table;
	private JMenuItem[] freeTTS_table;
	private JMenuItem[] macro_table;
	private int encode_flag = 0;
	
	
	public boolean getMacroValidation() {
		return MacroValidation;
	}
	
	public void setMacroValidation(boolean MacroValidation) {
		this.MacroValidation = MacroValidation;
	}
	
	public boolean getReversed() {
		return reversed;
	}
	
	public void setReversed(boolean rev) {
		reversed = rev;
	}
	
	public boolean getEncoded() {
		return encoded;
	}
	
	public void setEncoded(boolean enc) {
		encoded = enc;
	}
	
	public int getEncode_flag() {
		return encode_flag;
	}

	public void setEncode_flag(int encode_flag) {
		this.encode_flag = encode_flag;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public String getAPI() {
		return api;
	}	
	
	// The following function is solely for test cases
	public void setAPI(String api) {
		this.api = api;
	}
		
	public int getVolume() {
		return volume; 
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public int getRate() {
		return rate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	public int getPitch() {
		return pitch;
	}
	
	public void setPitch(int pitch) {
		this.pitch = pitch;
	}
		
	public Document getCurrentDocument() {
		return currentDoc;
	}
	
	public void setCurrentDocument(Document currentDoc) {
		this.currentDoc = currentDoc;
	}
	
	public int getCurrentLine() {
		return currentLine;
	}
	
	public void setCurrentLine(int currentLine) {
		this.currentLine = currentLine;
	}
	
	public static Text2SpeechEditorView getSingletonView() 
    { 
        if (editor == null) {
        	editor = new Text2SpeechEditorView(); 
        }
        return editor; 
    } 
	
	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String txt) {
		this.textArea.setText(txt);
	}
	
	// The following function exists solely for the Test Cases.
	public JTextArea setTestArea(JTextArea textArea) {
		return this.textArea = textArea;
	}
	
	public ReplayManager getManager() {
		return manager;
	}

	public void CreateMenu(JMenu menu, JMenuBar menuBar,JMenuItem[] table, String[] commands,String[] ToolTips) {
		menuBar.add(menu);
		for (int i = 0; i<commands.length;i++) {
			menu.add(factory.createCommand(table,commands[i],ToolTips[i]));
		}
		factory.setCommands(0);
	}
	
	
	
	public void initialize() {
	
		manager = new ReplayManager();
		factory = new CommandsFactory();
		frame = new JFrame("Text to Speech");
		frame.setLayout(new BorderLayout());
		// Create Swing Component
		textArea = setTestArea(new JTextArea());
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				// TODO Auto-generated method stub
				try
		        {
		            int offset = textArea.getCaretPosition();
		            int line = textArea.getLineOfOffset(offset);
		            setCurrentLine(line);
		            System.out.printf("Offset %d on line %d %d%n", offset, line, getCurrentLine());
		        }
		        catch (BadLocationException ex)
		        {
		            ex.printStackTrace();
		        }
			}
		});
		frame.add(new JScrollPane(textArea));
		JButton button = new JButton("Quick Transform");
		// Add Swing components to content pane
		Container cont = frame.getContentPane();
		cont.add(textArea, BorderLayout.CENTER);
		cont.add(button,BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("Hello World\n");
			}
		});
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);	
		
		
		JMenu file = new JMenu("File");
		String[] file_commands = new String[] {"New Document","Open Document","Save Document","Exit"};
		String[] file_tooltips = new String[] {"Create New Document","Save Document","Open Document","Exit"};
		file_table = new JMenuItem[4];
		CreateMenu(file,menuBar,file_table,file_commands,file_tooltips);

		/////// ACTIONS FOR FILE ///////
		file_table[0].addActionListener(new NewDocument());
		file_table[1].addActionListener(new OpenDocument());
		file_table[2].addActionListener(new SaveDocument());
		file_table[3].addActionListener(new CloseDocument());
		////// ACTIONS FOR FILE //////
	
		
		JMenu tr = new JMenu("Transform");
		
		String[] transform_commands = new String[] {"Transform Document","Transform Line","Reverse Transform Document","Reverse Line"};
		String[] transform_tooltips = new String[] {"Transform Document","Transform Line","Reverse Transform Document","Reverse Transform Line"};
		transform_table = new JMenuItem[4];

		CreateMenu(tr,menuBar,transform_table,transform_commands,transform_tooltips);
		
		
		/////// ACTIONS FOR FILE ///////
		transform_table[0].addActionListener(new DocumentToSpeech());
		transform_table[1].addActionListener(new LineToSpeech());
		transform_table[2].addActionListener(new DocumentToSpeech());
		transform_table[3].addActionListener(new LineToSpeech());
		////// ACTIONS FOR FILE //////
	
		JMenu edit = new JMenu("Edit/Encode Document");
		String[] bash_commands = new String[] {"Edit Document","Whole Document","Specific Line"};
		String[] bash_tooltips = new String[] {"Edit Document","Whole Document","Specific Line"};
		edit_table = new JMenuItem[3];

		CreateMenu(edit,menuBar,edit_table,bash_commands,bash_tooltips);

		
		
		/////// ACTIONS FOR FILE ///////
		for (int i=0;i<3;i++) {
			edit_table[i].addActionListener(new EditDocument());
		}
		////// ACTIONS FOR FILE //////
		
		JMenu encoding = new JMenu("Tune Encoding");
		String[] enc_command = new String[] {"AtBash","Rot13"};
		String[] enc_tooltip = new String[] {"Select AtBash","Select Rot13"};
		encoding_table = new JMenuItem[2];
		
		CreateMenu(encoding,menuBar,encoding_table,enc_command,enc_tooltip);
		
		
		/////// ACTIONS FOR FILE ///////
		encoding_table[0].addActionListener(new TuneEncoding());
		encoding_table[1].addActionListener(new TuneEncoding());
		////// ACTIONS FOR FILE //////
		
		
		
		
		JMenu EV = new JMenu("Edit Voice");
		
		String[] ev_commands = new String[] {"Volume","Pitch","Rate"};
		String[] ev_tooltips = new String[] {"Adjust Volume","Adjust Pitch","Adjust Rate"};
		freeTTS_table = new JMenuItem[3];
		CreateMenu(EV,menuBar,freeTTS_table,ev_commands,ev_tooltips);
		
		
		/////// ACTIONS FOR FILE ///////
		for (int i=0;i<3;i++) {
			freeTTS_table[i].addActionListener(new TuneAudio());
		}
		////// ACTIONS FOR FILE //////
		
		JMenu macro = new JMenu("Macros");
		String[] macro_commands = new String[] {"Start Recording Macro","Stop/Store Recording Macro"};
		String[] macro_tooltips = new String[] {"Start","Stop"};
		macro_table = new JMenuItem[2];
		CreateMenu(macro,menuBar,macro_table,macro_commands,macro_tooltips);
		
		for (int i=0;i<2;i++) {
			macro_table[i].addActionListener(new ReplayCommand(manager));
		}
		
	
		frame.setSize(560,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	private Text2SpeechEditorView() {}
	
	public static void main(String[] args) {
		editor = Text2SpeechEditorView.getSingletonView();
		editor.initialize();
	}

	

}
