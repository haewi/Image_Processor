package edu.handong.java.round4;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Brightness {

	JFrame frame;
	
	public Brightness(MainFrame mainFrame) {
		frame = new JFrame("Brightness");
		frame.setBounds(0, 100, 317, 118);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 305, 84);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSlider slider = new JSlider(-255, 255, 0);
		slider.setBounds(0, 38, 305, 35);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				mainFrame.changeBrightness(slider.getValue());
			}
			
		});
		panel.add(slider);
		
		JLabel brightnessLabel = new JLabel("Brightness");
		brightnessLabel.setBounds(115, 13, 84, 24);
		panel.add(brightnessLabel);
		frame.setVisible(true);
	}
}
