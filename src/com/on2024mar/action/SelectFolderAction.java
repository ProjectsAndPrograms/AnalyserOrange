package com.on2024mar.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.on2024mar.fileService.FolderService;
import com.on2024mar.fileService.Impl.FolderServiceImpl;
import com.on2024mar.ui.MainFrame;
import com.on2024mar.ui.sidebar.SidebarPanel;
import com.on2024mar.util.SelectedRootDirectory;

public class SelectFolderAction implements ActionListener {

	JPanel selectFolderPanel;
	public static FolderService folderService;

	public SelectFolderAction(JPanel selectFolderPanel) {

		this.selectFolderPanel = selectFolderPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		openFolderAction();
	}

	public static void openFolderAction() {
		folderService = new FolderServiceImpl();

		File selectedFolder = folderService.openFolderSelector();
		if (selectedFolder != null && selectedFolder.exists()) {

			if (selectedFolder.isDirectory()) {
				SidebarPanel sidebar = new SidebarPanel(selectedFolder);
				sidebar.revalidate();
				sidebar.repaint();

				SelectedRootDirectory.setSelectedRootDirectory(selectedFolder.getAbsolutePath());
				MainFrame.addToMainFrame(sidebar);

			} else {
				JOptionPane.showMessageDialog(null, "Folder not availble!", "Exception", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}
