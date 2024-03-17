package com.on2024mar.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.on2024mar.constants.AppColor;
import com.on2024mar.ui.component.TransparentImageIcon;

public class StartupCenterPanel extends JPanel {

	private static final long serialVersionUID = -2525863176193659041L;

	public StartupCenterPanel() {

		this.setLayout(new BorderLayout());
		this.setBackground(null);

		JPanel panel = new JPanel();

		panel.setLayout(new GridBagLayout());
		panel.setBackground(null);

		ImageIcon icon = new ImageIcon(getClass().getResource("/com/on2024mar/icon/app_logo.png"));
		Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

		TransparentImageIcon imageT = new TransparentImageIcon(image, 0.4f);

		JLabel label = new JLabel(imageT);
		label.setVerticalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc = new GridBagConstraints();

		label.setFont(new Font("sansserif", Font.PLAIN, 18));
		label.setForeground(AppColor.DARKGREY);
		label.setText("<html>Open &nbsp; Ctrl + O &nbsp; &nbsp; &nbsp; &nbsp; Browse &nbsp;  ctrl + click<br>"
				+ "New &nbsp;&nbsp;&nbsp;Ctrl + N &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; Quit &nbsp;  &nbsp;&nbsp;  &nbsp; ctrl + Q <br>"
				+ "</html>");

		label.setMinimumSize(new Dimension(400, 400));
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.BOTTOM);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(label);

		this.add(panel, BorderLayout.CENTER);
	}

}
