package com.on2024mar.ui;

import java.awt.Cursor;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.on2024mar.constants.AppColor;

public class CenterTabbedPane extends JTabbedPane {

	private static final long serialVersionUID = 1226905751960521197L;

	CenterTabbedPane centerTabbedPane;

	public CenterTabbedPane() {

		this.setFocusable(false);
		this.setBorder(null);
		this.setOpaque(false);
		this.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.setBackground(AppColor.LIGHT);
		this.setFocusable(false);
		this.setOpaque(false);
		this.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

			}
		});
		centerTabbedPane = this;

	}

	protected JLabel getLabel(String title, String icon) {
		JLabel label = new JLabel(title + "  ");
		label.setForeground(AppColor.PRIMARY);
		label.setBackground(AppColor.LIGHT);
		label.setIconTextGap(7);
		try {
			label.setIcon(new ImageIcon(ImageIO.read(getClass().getResource(icon))));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return label;
	}

	public void addTabWithImage(String name, String imagePath, JPanel panel) {
		int tabIndex = this.getTabCount();
		this.add(panel, tabIndex);
		this.setTabComponentAt(tabIndex, getLabel(name, imagePath));

	}

	public static CenterTabbedPane getCenterTabbedPaneInstance() {
		return null;
	}

}
