package edu.handong.java.round4;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanel extends JPanel{
	
	BufferedImage image;
	JFrame frame;
	

	public DrawPanel(JFrame f) {
		frame = f;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int x=0, y=0;
		if(image != null) {
			x = image.getWidth();
			y = image.getHeight();
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
		g.drawImage(image, 0, 0, this);
	}
	
	public void setImage(BufferedImage img) {
		image = img;
	}
	
}