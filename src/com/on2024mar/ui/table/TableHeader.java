package com.on2024mar.ui.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import com.on2024mar.constants.AppColor;

public class TableHeader extends JLabel {

	private static final long serialVersionUID = -3965439052709745550L;

	public TableHeader(String text) {
		super(text);
		setOpaque(true);

		setBackground(AppColor.LIGHT);
		setFont(new Font("sansserif", 1, 12));
		setForeground(AppColor.DARK);
		setBorder(new EmptyBorder(10, 5, 10, 5));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(230, 230, 230));
		g2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
	}

}
