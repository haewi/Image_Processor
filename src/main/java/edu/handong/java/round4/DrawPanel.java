package edu.handong.java.round4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
		
		g.drawImage(image, 0, 0, this);
		if(image == null) return;
		else if(function == MainFrame.OPEN) {
			int x=0, y=0;
			x = image.getWidth();
			y = image.getHeight();

			int newX = x-711;
			int newY = y-615;
			if(x > 711 && y > 615) {
				main.frame.setSize(847+newX, 649+newY);
				this.setSize(711+newX, 615+newY);
			}
			else if(x > this.getWidth()) {
				main.frame.setBounds(0, 10, 847+newX, 649);
				this.setBounds(130, 6, 711+newX, 615);
			}
			else if(y > this.getHeight()) {
				main.frame.setBounds(0, 10, 847, 649+newY);
				this.setBounds(130, 6, 711, 615+newY);
			}
			else {
				main.frame.setSize(847, 649);
				this.setSize(711, 615);

			}
		}
		else if(function == MainFrame.ADDIMAGE) {
			main.changeImage(mouseX, mouseY);
			this.repaint();
		}
		else if(function == MainFrame.MAGNIFY) {
			g.drawRect(mouseX-45, mouseY-45, 90, 90);
			if(mouseX>0 && mouseX<image.getWidth() && mouseY>0 && mouseY<image.getHeight()) {
				int startX = mouseX-15;
				int startY = mouseY-15;
				int width = 30;
				int height = 30;
				
				// 그려야할 subimage의 크기가 정사각형이 안되는 경우 (마우스가 이미지 밖으로 나가서 정사각형이 아닌 사각형일 때)
				if(mouseX<15) startX = 0;
				if(mouseY<15) startY = 0;
				if(mouseX>image.getWidth()-15) width = image.getWidth()-mouseX+15;
				if(mouseY>image.getHeight()-15) height = image.getHeight()-mouseY+15;
				
				BufferedImage sub = image.getSubimage(startX, startY, width, height);
				Image mag = sub.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
				g.drawImage(mag, mouseX-45, mouseY-45, null);
			}
			this.repaint();
		}
		
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