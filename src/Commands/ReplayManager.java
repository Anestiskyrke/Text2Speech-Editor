package Commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import View.Text2SpeechEditorView;

public class ReplayManager {
	
	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();
	private ArrayList<ActionListener> MacroSaver;
	private ArrayList<String> options;
	private ArrayList<ArrayList<String>> options_list = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<ActionListener>> ComMacros = new ArrayList<ArrayList<ActionListener>>();
	private int mymacro = 1;
	private int count_opt = 0;
	
	public void replay(JMenuItem menuItem) {
		if (menuItem.getToolTipText().equals("Stop")) {
			JMenuItem a = new JMenuItem("Macro " +  String.valueOf(mymacro));
			mymacro++;
			menuItem.getParent().add(a);
			int size = ComMacros.size();
			for (int i=0;i<ComMacros.get(size-1).size();i++) {
				a.addActionListener(ComMacros.get(size-1).get(i));
			}
			count_opt = 0;
			a.setToolTipText(options_list.get(size-1).get(count_opt));
		}
	}

	public int getMyMacro() {
		return mymacro;
	}
	public ArrayList<ActionListener> getMacroSaver() {
		return MacroSaver;
	}

	public void setMacroSaver(ArrayList<ActionListener> macroSaver) {
		MacroSaver = macroSaver;
	}

	public ArrayList<ArrayList<ActionListener>> getComMacros() {
		return ComMacros;
	}

	public void setComMacros(ArrayList<ArrayList<ActionListener>> comMacros) {
		ComMacros = comMacros;
	}

	public ArrayList<ArrayList<String>> getOptions_list() {
		return options_list;
	}

	public void setOptions_list(ArrayList<ArrayList<String>> options_list) {
		this.options_list = options_list;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	public int getCount_opt() {
		return count_opt;
	}

	public void setCount_opt(int count_opt) {
		this.count_opt = count_opt;
	}
}
