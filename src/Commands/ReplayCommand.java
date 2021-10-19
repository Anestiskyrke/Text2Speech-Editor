package Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import View.Text2SpeechEditorView;

public class ReplayCommand implements ActionListener {

	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();
	private ReplayManager manager;
	
	
	public ReplayCommand(ReplayManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem menuItem = (JMenuItem)e.getSource();
        String option = menuItem.getToolTipText();
		
		if (option.equals("Start")) {
			manager.setMacroSaver( new ArrayList<ActionListener>() );
			manager.setOptions( new ArrayList<String>() );
			app.setMacroValidation(true);
		}
		else if (option.equals("Stop")) {
			manager.getComMacros().add(manager.getMacroSaver());
			manager.getOptions_list().add(manager.getOptions());
			manager.setMacroSaver( new ArrayList<ActionListener>() );
			manager.setOptions( new ArrayList<String>() );
			app.setMacroValidation(false);
			manager.replay(menuItem);
		}
	}
}
