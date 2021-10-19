package Commands;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import View.Text2SpeechEditorView;
import javax.swing.JSlider;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;


public class TuneAudio implements ActionListener{

	private Text2SpeechEditorView app = Text2SpeechEditorView.getSingletonView();

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem menuItem = (JMenuItem)e.getSource();
        String option = menuItem.getToolTipText();
        JFrame frame;
        JSlider slider;
        
        if (option.equals("Adjust Volume")) {
        	frame = new JFrame("Edit Volume");
			slider = new JSlider(JSlider.HORIZONTAL,0,20,app.getVolume());
			slider.setMajorTickSpacing(5);
			slider.setMinorTickSpacing(1);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					app.setVolume(source.getValue());
					
				}
			});
			frame.add(slider,BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(400,300);
        }
        else if (option.equals("Adjust Pitch")) {
        	frame = new JFrame("Edit Pitch");
			slider= new JSlider(JSlider.HORIZONTAL,30,230,app.getPitch());
			slider.setMajorTickSpacing(50);
			slider.setMinorTickSpacing(10);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					app.setPitch(source.getValue());
					
				}
			});
			frame.add(slider,BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(400,300);
        }
        else if (option.equals("Adjust Rate")) {
        	frame = new JFrame("Edit Rate");
			slider= new JSlider(JSlider.HORIZONTAL,90,290,app.getRate());
			slider.setMajorTickSpacing(50);
			slider.setMinorTickSpacing(10);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					JSlider source = (JSlider) e.getSource();
					app.setRate(source.getValue());
					
				}
			});
			frame.add(slider,BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(400,300);
        }
        
	}
}
