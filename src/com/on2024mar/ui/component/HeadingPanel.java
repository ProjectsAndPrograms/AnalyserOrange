package com.on2024mar.ui.component;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.on2024mar.action.PathOpenAction;
import com.on2024mar.constants.AppColor;
import com.on2024mar.constants.AppConstants;
import com.on2024mar.constants.AppInitConstants;

public class HeadingPanel extends JPanel {

	private static final long serialVersionUID = -6725377604889683201L;
	private GridBagConstraints gbc;
	private int FONT_SIZE = 18;
	private JTextArea textArea;

	public HeadingPanel(String text) {
		this.setBackground(AppColor.LIGHT);
		this.setLayout(new GridBagLayout());
		this.setMaximumSize(new Dimension(AppConstants.APP_START_WIDTH, 200));

		gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 5, 0, 2);

		addDefaultImage();
		addTextArea(text, FONT_SIZE);

	}

	public HeadingPanel(String text, String imagePath, int fontSize) {
		this.setBackground(AppColor.LIGHT);

		this.setLayout(new GridBagLayout());
		this.setMaximumSize(new Dimension(AppConstants.APP_START_WIDTH, 200));
		gbc = new GridBagConstraints();

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 5, 0, 2);
		addDefinedImage(imagePath);
		addTextArea(text, fontSize);

	}

	private void addDefinedImage(String imagePath) {
		JLabel label = new JLabel(new ImageIcon(getClass().getResource(imagePath)));

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.01;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(label, gbc);
	}

	private void addDefaultImage() {
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/com/on2024mar/icon/paperpin1.png")));

		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setIcon(new ImageIcon(getClass().getResource("/com/on2024mar/icon/paperpin2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setIcon(new ImageIcon(getClass().getResource("/com/on2024mar/icon/paperpin1.png")));
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.05;
		gbc.fill = GridBagConstraints.VERTICAL;
		this.add(label, gbc);
	}

	private void addTextArea(String text, int fontSize) {
		textArea = new JTextArea();
		textArea.setText(text);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(this.getBackground());
		textArea.setFont(new Font("sansserif", Font.PLAIN, fontSize));
		textArea.setBorder(BorderFactory.createEmptyBorder());
		textArea.setBorder(BorderFactory.createLineBorder(AppColor.LIGHT, 3));
		textArea.setSelectionColor(AppColor.SECONDARY);
		textArea.setSelectedTextColor(AppColor.DARK);
		
		File file = new File(text);
		if(file.exists()) {
			textArea.setFont(AppInitConstants.DEFAULT_LINK_FONT);
			textArea.addKeyListener(new PathOpenAction(textArea));
			textArea.addMouseListener(new PathOpenAction(textArea));
			textArea.addMouseMotionListener(new PathOpenAction(textArea));
		}
		

		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(textArea, gbc);
	}

	public HeadingPanel addMarginOnHeading(int top, int left, int bottom, int right) {
		this.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		return this;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

}
