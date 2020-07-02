package edu.handong.java.round4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 649);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 112, 615);
		contentPane.add(panel);
		
		JButton openFile = new JButton("Open");
		openFile.setBounds(9, 5, 97, 47);
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setCurrentDirectory(new File("/Users/yeahn/Desktop"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				file.setFileFilter(filter);
				file.showOpenDialog(null);
			}
		});
		panel.setLayout(null);
		panel.add(openFile);
		
		JButton filter = new JButton("Filter");
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		filter.setBounds(9, 64, 97, 47);
		panel.add(filter);
		
		JButton addImage = new JButton("Add Image");
		addImage.setBounds(9, 123, 97, 47);
		panel.add(addImage);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		save.setBounds(9, 182, 97, 47);
		panel.add(save);
	}
}
