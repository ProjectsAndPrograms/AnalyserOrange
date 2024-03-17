package com.on2024mar.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.on2024mar.action.FrameShortcutActions;
import com.on2024mar.constants.AppConstants;
import com.on2024mar.constants.AppInitConstants;
import com.on2024mar.constants.AppUI;
import com.on2024mar.ui.sidebar.SidebarPanel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5442492764593232628L;
	static MainFrame mainFrame;
	public static Component sidebarCopy;
	public static JSplitPane splitPane;

	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(AppConstants.APP_NAME);
		this.setIconImage(new ImageIcon(getClass().getResource(AppConstants.APP_LOGO)).getImage());
		this.getContentPane().setLayout(new BorderLayout());
		this.setSize(AppConstants.APP_START_WIDTH, AppConstants.APP_START_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(600, 400));
		initAppUI();
		initAppInitialConstants();

		splitPane = new JSplitPane();
		splitPane.setDividerLocation(250);

		if (sidebarCopy == null) {
			sidebarCopy = new SidebarPanel();
			splitPane.setLeftComponent(sidebarCopy);
		}

		splitPane.setRightComponent(new StartupCenterPanel());

		getContentPane().add(splitPane);
		this.setVisible(true);

		FrameShortcutActions.enableFrameShortcutActions();

		mainFrame = this;
	}

	public static void setMainFrame(MainFrame mainFrame) {
		MainFrame.mainFrame = mainFrame;
	}

	public static MainFrame getMainFrame() {
		return mainFrame;
	}

	public static void addToMainFrame(Component component) {

		if (sidebarCopy != null) {
			splitPane.remove(sidebarCopy);
		}

		sidebarCopy = component;
		splitPane.setLeftComponent(sidebarCopy);
		mainFrame.revalidate();
		mainFrame.repaint();
	}

	void initAppUI() {
		new AppUI();
	}

	void initAppInitialConstants() {
		new AppInitConstants();
	}

	public static void setSidebarCopy(Component sidebarCopy) {
		MainFrame.sidebarCopy = sidebarCopy;
	}
}
