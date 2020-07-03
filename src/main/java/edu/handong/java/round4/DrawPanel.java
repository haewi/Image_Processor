package edu.handong.java.round4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

class DrawPanel extends JPanel {
	
	BufferedImage image;
	BufferedImage addImage;
	MainFrame main;
	
	int function=0;
	int mouseX=0, mouseY=0;
	

	public DrawPanel(MainFrame m) {
		main = m;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		function = main.function;
		
		int x=0, y=0;
		if(image != null) {
			x = image.getWidth();
			y = image.getHeight();
		}
		
		int newX = x-711;
		int newY = y-615;
		if(x > 711 && y > 615) {
			this.setSize(711+newX, 615+newY);
			main.frame.setSize(847+newX, 649+newY);
			main.frame.repaint();
		}
		else if(x > this.getWidth()) {
			this.setBounds(130, 6, 711+newX, 615);
			main.frame.setBounds(0, 10, 847+newX, 649);
			main.frame.repaint();
		}
		else if(y > this.getHeight()) {
			this.setBounds(130, 6, 711, 615+newY);
			main.frame.setBounds(0, 10, 847, 649+newY);
			main.frame.repaint();
		}
		else {
			this.setSize(711, 615);
			main.frame.setSize(847, 649);
			main.frame.repaint();
			
		}
		
		if(addImage != null){
			main.loadImage = main.followImage.getSubimage(0, 0, main.followImage.getWidth()-150, main.followImage.getHeight());
			main.changeImage(mouseX, mouseY);
		}
		g.drawImage(image, 0, 0, this);
		
	}
	
	public void setImage(BufferedImage img) {
		image = img;
	}
	
	public void setAddImage(BufferedImage addImage) {
		this.addImage = addImage;
	}
	
	public void setMouseX(int x) {
		this.mouseX = x;
	}

	public void setMouseY(int y) {
		this.mouseY = y;
	}
	
}