package com.on2024mar.ui.component;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class TransparentImageIcon extends ImageIcon {
	private static final long serialVersionUID = 4553262205753058123L;
	private float alpha;

	public TransparentImageIcon(Image image, float alpha) {
		super(image);
		this.alpha = alpha;
	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		super.paintIcon(c, g2, x, y);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return super.getIconWidth();
	}

	@Override
	public int getIconHeight() {
		return super.getIconHeight();
	}
}
