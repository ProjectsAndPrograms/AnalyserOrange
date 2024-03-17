package com.on2024mar.ui.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.on2024mar.constants.AppColor;
import com.on2024mar.constants.AppConstants;

public class DataPanel extends JPanel {

	private static final long serialVersionUID = 1937334900216933022L;
	GridBagConstraints gbc;

	public DataPanel(Map<String, String> hashMap) {
		this.setBackground(AppColor.LIGHT);
		this.setLayout(new BorderLayout());
		this.setMaximumSize(new Dimension(AppConstants.APP_START_WIDTH, 400));
		JPanel gridPanel = createGridPanel(hashMap);

		gridPanel.setBorder(BorderFactory.createEmptyBorder(2, 30, 2, 2));
		this.add(gridPanel, BorderLayout.WEST);
	}

	private JPanel createGridPanel(Map<String, String> hashMap) {
		JPanel gridPanel = new JPanel(new GridBagLayout());
		gridPanel.setBackground(AppColor.LIGHT);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 10, 0, 40);

		boolean flag = true;
		for (Map.Entry<String, String> entry : hashMap.entrySet()) {
			JPanel panel = createDataPanel(entry.getKey(), entry.getValue());
			if (flag) {
				gridPanel.add(panel, gbc);
			} else {
				gbc.gridx++;
				gridPanel.add(panel, gbc);
				gbc.gridx = 0;
				gbc.gridy++;
			}
			flag = !flag;
		}
		return gridPanel;
	}

	private JPanel createDataPanel(String label, String value) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(AppColor.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		JTextPane labelPane = new JTextPane();
		JTextPane valuePane = new JTextPane();
		labelPane.setText(label);
		valuePane.setText(value);

		labelPane.setBackground(AppColor.WHITE);
		labelPane.setForeground(AppColor.DARK);
		labelPane.setSelectedTextColor(AppColor.DARK);
		labelPane.setSelectionColor(AppColor.SECONDARY);
		labelPane.setEditable(false);
		labelPane.setFont(new Font("sansserif", Font.BOLD, 12));
		labelPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));

		valuePane.setForeground(AppColor.PRIMARY);
		valuePane.setBackground(AppColor.WHITE);
		valuePane.setSelectedTextColor(AppColor.DARK);
		valuePane.setSelectionColor(AppColor.SECONDARY);
		valuePane.setEditable(false);
		valuePane.setFont(new Font("sansserif", Font.BOLD, 12));

		panel.add(labelPane, BorderLayout.CENTER);
		panel.add(valuePane, BorderLayout.EAST);

		return panel;
	}

	public DataPanel addMargin(int top, int left, int bottom, int right) {
		this.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		this.revalidate();
		this.repaint();
		return this;

	}

}
