package edu.handong.java.round4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class MainFrame {
	
	private JFrame frame = new JFrame();
	private JPanel contentPane;
	JFrame filterColor;
	
	// 불러온 이미지 파일
	BufferedImage originalImage = null;
	
	// 이미지 패널과 그 JLabel
	DrawPanel imagePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
//					frame.setVisible(true);
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
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 10, 847, 649);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 200, 200));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(6, 6, 112, 615);
		contentPane.add(panel);
		
		JButton openFile = new JButton("Open");
		openFile.setBounds(9, 5, 97, 47);
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChoose = new JFileChooser();
				fileChoose.setCurrentDirectory(new File("/Users/yeahn/Desktop/2020-Summer/Image_Processor/image"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				fileChoose.setFileFilter(filter);
				fileChoose.showOpenDialog(null);
				File file = fileChoose.getSelectedFile();
				openFile(file);
			}
		});
		panel.setLayout(null);
		panel.add(openFile);
		
		JButton filter = new JButton("Filter");
		filter.setBounds(9, 64, 97, 47);
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int y = 0; y < originalImage.getHeight(); y++) {
					   for(int x = 0; x < originalImage.getWidth(); x++) {
					       Color colour = new Color(originalImage.getRGB(x, y));
//					       Choose one from below
//					       int Y = (int) (0.299 * colour.getRed() + 0.587 * colour.getGreen() + 0.114 * colour.getBlue());
					       int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
					       originalImage.setRGB(x, y, new Color(Y, Y, Y).getRGB());
					   }
					}
				  
				imagePanel.repaint();
				
			}
		});
		panel.add(filter);
		
		JButton addImage = new JButton("Add Image");
		addImage.setBounds(9, 123, 97, 47);
		panel.add(addImage);
		
		JButton save = new JButton("Save");
		save.setBounds(9, 182, 97, 47);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(save);
		
		imagePanel = new DrawPanel();
		imagePanel.setBounds(130, 6, 711, 615);
		imagePanel.setBackground(Color.white);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
	}
	
	class DrawPanel extends JPanel{

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			int x=0, y=0;
			if(originalImage != null) {
				x = originalImage.getWidth();
				y = originalImage.getHeight();
			}
			
			int newX = x-711;
			int newY = y-615;
			if(x > 711 && y > 615) {
				this.setSize(711+newX, 615+newY);
				frame.setSize(847+newX, 649+newY);
				frame.repaint();
			}
			else if(x > this.getWidth()) {
				this.setBounds(130, 6, 711+newX, 615);
				frame.setBounds(0, 10, 847+newX, 649);
				frame.repaint();
			}
			else if(y > this.getHeight()) {
				this.setBounds(130, 6, 711, 615+newY);
				frame.setBounds(0, 10, 847, 649+newY);
				frame.repaint();
			}
			
			g.drawImage(originalImage, 0, 0, this);
			
		}
		
	}
	
	private void openFile(File file) {
		try {
			originalImage = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Can't open file");
		}
		imagePanel.repaint();
	}
}





