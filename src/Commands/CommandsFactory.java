package Commands;

import javax.swing.JMenuItem;


public class CommandsFactory {
	
	private int commands = 0;
	
	public void setCommands(int com) {
		commands = com;
	}
	
	public JMenuItem createCommand(JMenuItem[] table, String command, String ToolTip) {
		JMenuItem item = new JMenuItem(command);
		item.setToolTipText(ToolTip);
		table[commands] = item;
		commands++;
		return item;
	}
}
