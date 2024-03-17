package com.on2024mar.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.on2024mar.constants.AppColor;
import com.on2024mar.ui.CenterTabbedPane;
import com.on2024mar.ui.FilesPanel;
import com.on2024mar.ui.FoldersPanel;
import com.on2024mar.ui.MainFrame;
import com.on2024mar.ui.OverviewPanel;
import com.on2024mar.ui.component.BottomLoadingPanel;
import com.on2024mar.ui.sidebar.SidebarPanel;
import com.on2024mar.util.CheckedPaths;

public class AnalyseActionHandler implements ActionListener {

	public static BottomLoadingPanel blp;
	CenterTabbedPane centerTabbedPane;
	public static boolean running = true;

	public AnalyseActionHandler() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Thread thread1 = new Thread(new Runnable() {

			@Override
			public synchronized void run() {

				try {

					blp = new BottomLoadingPanel();
					JPanel sidebarPanelCopy = SidebarPanel.sidebarPanelCopy;
					sidebarPanelCopy.add(blp, BorderLayout.SOUTH);
					SidebarPanel.analyseBtn.setEnabled(false);
					SidebarPanel.folderChooserBtn.setEnabled(false);
					sidebarPanelCopy.revalidate();
					sidebarPanelCopy.repaint();

					ArrayList<String> directories = new ArrayList<>();
					ArrayList<String> files = new ArrayList<>();

					for (String pathString : CheckedPaths.getPathsArray()) {
						File file = new File(pathString);
						if (!file.isHidden() && file.exists()) {
							if (file.isDirectory()) {
								directories.add(file.getAbsolutePath());
							} else {
								files.add(file.getAbsolutePath());
							}
						}
					}

					centerTabbedPane = new CenterTabbedPane();
					centerTabbedPane.addTabWithImage("Summery", "/com/on2024mar/icon/search1.png",
							new OverviewPanel(directories, files));

					if (!running) {
						running = true;
						return;
					}

					centerTabbedPane.addTabWithImage("Folders", "/com/on2024mar/icon/small-folder.png",
							new FoldersPanel(directories, files));
					if (!running) {
						running = true;
						return;
					}
					centerTabbedPane.addTabWithImage("Files", "/com/on2024mar/icon/small-file.png",
							new FilesPanel(directories, files));
					if (!running) {
						running = true;
						return;
					}

					centerTabbedPane.getSelectedComponent().setForeground(AppColor.LIGHT);

					if (running) {
						SwingUtilities.invokeLater(() -> {
							MainFrame.splitPane.setRightComponent(centerTabbedPane);
						});
					} else {
						centerTabbedPane = null;
					}

				} catch (NullPointerException e) {

				}
			}

		});

		Thread thread2 = new Thread(new Runnable() {

			@Override
			public synchronized void run() {

				try {

					while (thread1.isAlive()) {

					}
					JPanel sidebarPanelCopy = SidebarPanel.sidebarPanelCopy;
					sidebarPanelCopy.remove(blp);
					SidebarPanel.analyseBtn.setEnabled(true);
					SidebarPanel.folderChooserBtn.setEnabled(true);

					BottomLoadingPanel.btn.setBackground(AppColor.DANGER);
					BottomLoadingPanel.btn.setEnabled(true);

					sidebarPanelCopy.revalidate();
					sidebarPanelCopy.repaint();

				} catch (NullPointerException e) {
				}
			}

		});

		thread1.setName("TabCreaterThread");
		thread2.setName("loadingThread");

		thread1.start();
		thread2.start();
	}

}
