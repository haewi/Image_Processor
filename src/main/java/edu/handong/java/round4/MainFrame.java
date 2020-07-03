package edu.handong.java.round4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;

public class MainFrame {
	
	MainFrame main = this;
	
	
	private JFrame frame = new JFrame();
	
	private JPanel contentPane;
	JFrame filterColor;
	
	// 불러온 이미지 파일
	BufferedImage loadImage = null;
	BufferedImage originalImage = null;
	BufferedImage followImage = null;
	String fileName;
	
	// 이미지 패널
	DrawPanel imagePanel;

	// previous brightness
	int preB=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
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
				if(fileChoose.getSelectedFile() != null) {
					File file = fileChoose.getSelectedFile();
					fileName = file.getName();
					openFile(file);
				}
			}
		});
		panel.setLayout(null);
		panel.add(openFile);
		
		JButton filter = new JButton("Filter");
		filter.setBounds(9, 64, 97, 47);
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loadImage == null) return;
				grayFilter();
			}
		});
		panel.add(filter);
		
		JButton addImage = new JButton("Add Image");
		addImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		addImage.setBounds(9, 123, 97, 47);
		panel.add(addImage);
		
		JButton brightness = new JButton("Brightness");
		brightness.setBounds(9, 182, 97, 47);
		brightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loadImage == null) return;
				new Brightness(main);
			}
		});
		panel.add(brightness);
		
		JButton magnifier = new JButton("Magnify");
		magnifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Magnify");
			}
		});
		magnifier.setBounds(9, 241, 97, 47);
		panel.add(magnifier);
		
		JButton before = new JButton("Before");
		before.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				if(originalImage == null) return;
				imagePanel.setImage(originalImage);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);
				if(loadImage == null) return;
				imagePanel.setImage(loadImage);
			}
			
		});
		before.setBounds(9, 300, 97, 47);
		panel.add(before);
		
		JButton save_3 = new JButton("Save");
		save_3.setBounds(9, 359, 97, 47);
		panel.add(save_3);
		
		JButton save_4 = new JButton("Save");
		save_4.setBounds(9, 418, 97, 47);
		panel.add(save_4);
		
		JButton save_4_1 = new JButton("Save");
		save_4_1.setBounds(9, 477, 97, 47);
		panel.add(save_4_1);
		
		JButton save = new JButton("Save");
		save.setBounds(9, 536, 97, 47);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveImage();
			}
		});
		panel.add(save);
		
		imagePanel = new DrawPanel(frame);
		imagePanel.setBounds(130, 6, 711, 615);
		imagePanel.setBackground(Color.white);
		contentPane.add(imagePanel);
		imagePanel.setLayout(null);
	}
	
	private void openFile(File file) {
		try {
			loadImage = ImageIO.read(file);
			originalImage = ImageIO.read(file);
			followImage = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("Can't open file");
		}
		imagePanel.setImage(loadImage);
		imagePanel.repaint();
	}
	
	private void saveImage() {
		try {
			String newFile = "/Users/yeahn/Desktop/2020-Summer/Image_Processor/outputImage/output_" + fileName;
			ImageIO.write(loadImage, "jpg", new File(newFile));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void grayFilter() {
		for(int y = 0; y < loadImage.getHeight(); y++) {
			for(int x = 0; x < loadImage.getWidth(); x++) {
				Color colour = new Color(loadImage.getRGB(x, y));
//				Choose one from below
//				int Y = (int) (0.299 * colour.getRed() + 0.587 * colour.getGreen() + 0.114 * colour.getBlue());
				int Y = (int) (0.2126 * colour.getRed() + 0.7152 * colour.getGreen() + 0.0722 * colour.getBlue());
				loadImage.setRGB(x, y, new Color(Y, Y, Y).getRGB());
				followImage.setRGB(x, y, new Color(Y, Y, Y).getRGB());
			}
		}
	}
	
	public void changeBrightness(int bright) {
		if(loadImage == null) return;
		
		for(int y = 0; y < loadImage.getHeight(); y++) {
			for(int x = 0; x < loadImage.getWidth(); x++) {
				Color origin = new Color(followImage.getRGB(x, y));
				
				int r = Math.min(255, Math.max(0, origin.getRed()+bright)); // 0과 255 사이
				int g = Math.min(255, Math.max(0, origin.getGreen()+bright));
				int b = Math.min(255, Math.max(0, origin.getBlue()+bright));
				loadImage.setRGB(x, y, new Color(r, g, b).getRGB());
			}
		}
		preB = bright;
	}
}





