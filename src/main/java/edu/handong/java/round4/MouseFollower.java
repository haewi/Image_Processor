package edu.handong.java.round4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class MouseFollower extends MouseAdapter implements MouseListener {
	
	DrawPanel panel;
	
	public MouseFollower(DrawPanel p) {
		panel = p;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		
		if(panel.function == MainFrame.ADDIMAGE) {
			panel.mouseX = e.getX();
			panel.mouseY = e.getY();
			panel.addImage = null;
			panel.main.addImage = null;
			BufferedImage bi= panel.main.loadImage;
			panel.main.followImage = new BufferedImage(bi.getColorModel(), bi.copyData(null), bi.isAlphaPremultiplied(), null);
			panel.main.function = MainFrame.OPEN;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);
		
		if(panel.function == MainFrame.ADDIMAGE) {
			panel.mouseX = e.getX();
			panel.mouseY = e.getY();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		
		if(panel.function == MainFrame.ADDIMAGE) {
			panel.mouseX = e.getX();
			panel.mouseY = e.getY();
		}
		else if(panel.function == MainFrame.MAGNIFY) {
			panel.mouseX = e.getX();
			panel.mouseY = e.getY();
		}
	}

}
