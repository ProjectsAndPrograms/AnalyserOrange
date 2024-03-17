package com.on2024mar.ui.sidebar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.on2024mar.action.AnalyseActionHandler;
import com.on2024mar.action.SelectFolderAction;
import com.on2024mar.constants.AppColor;
import com.on2024mar.ui.component.TransparentImageIcon;
import com.on2024mar.ui.scrollbar.ScrollBarCustom;

public class SidebarPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 249223219331049780L;
	public static JButton analyseBtn;
	public static JButton folderChooserBtn;
	public static JPanel sidebarPanelCopy;

	public SidebarPanel() {
		initSidbarPanel();
		Component c = createComponents();
		addEmptySidebar();
		this.add(c, BorderLayout.NORTH);

		sidebarPanelCopy = this;
	}

	public SidebarPanel(File file) {
		initSidbarPanel();
		Component c = createComponents();
		addSidebarFolderHirarchy(file);
		this.add(c, BorderLayout.NORTH);
		sidebarPanelCopy = this;
	}

	void initSidbarPanel() {
		this.setBorder(BorderFactory.createLineBorder(AppColor.DARKGREY, 2));
		this.setPreferredSize(new Dimension(250, 100));
		this.setLayout(new BorderLayout());
	}

	Component createComponents() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 35));
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		folderChooserBtn = new JButton("Select Folder");
		folderChooserBtn.setFont(new Font("Dialog", Font.PLAIN, 15));
		folderChooserBtn.setBackground(AppColor.SECONDARY);
		folderChooserBtn.setForeground(AppColor.DARK);
		folderChooserBtn.setBorder(null);
		folderChooserBtn.setFocusable(false);
		folderChooserBtn.addActionListener(new SelectFolderAction(panel));
		folderChooserBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		analyseBtn = new JButton("Analyse");
		analyseBtn.setFont(new Font("Dialog", Font.PLAIN, 15));
		analyseBtn.setBackground(AppColor.LIGHT_SUCCESS);
		analyseBtn.setForeground(AppColor.SUCCESS);
		analyseBtn.setBorder(null);
		analyseBtn.setFocusable(false);
		analyseBtn.setEnabled(false);
		analyseBtn.addActionListener(new AnalyseActionHandler());
		analyseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(folderChooserBtn);
		panel.add(analyseBtn);
		return panel;
	}

	void addEmptySidebar() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBackground(AppColor.LIGHT);

		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/com/on2024mar/icon/folder.png"));
		Image image = imageIcon.getImage();
		TransparentImageIcon transparentImage = new TransparentImageIcon(image, 0.5f);

		JLabel label = new JLabel(transparentImage);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		p.add(label, gbc);

		JScrollPane scrollpane = new JScrollPane(p);
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.add(scrollpane, BorderLayout.CENTER);
	}

	void addSidebarFolderHirarchy(File file) {

		JScrollPane scrollpane = new JScrollPane(new FolderSelectorTree(file).getFolderSelectorTree());
		scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBar(new ScrollBarCustom());
//		scrollpane.setHorizontalScrollBar(new ScrollBarCustomH());

		this.add(scrollpane, BorderLayout.CENTER);
	}

	public static JPanel getSidebarPanel() {
		return sidebarPanelCopy;
	}

}
