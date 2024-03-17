package com.on2024mar.action;

import javax.swing.tree.TreePath;

import com.on2024mar.ui.sidebar.FolderPath;
import com.on2024mar.ui.sidebar.JCheckBoxTree;
import com.on2024mar.ui.sidebar.JCheckBoxTree.CheckChangeEvent;
import com.on2024mar.ui.sidebar.JCheckBoxTree.CheckChangeEventListener;
import com.on2024mar.ui.sidebar.SidebarPanel;
import com.on2024mar.util.CheckedPaths;

public class CheckedEventHandler implements CheckChangeEventListener {

	private JCheckBoxTree checkboxTree;
	private FolderPath folderpath;

	public CheckedEventHandler(JCheckBoxTree checkboxTree, FolderPath folderpath) {
		this.checkboxTree = checkboxTree;
		this.folderpath = folderpath;
	}

	@Override
	public void checkStateChanged(CheckChangeEvent event) {
		TreePath[] paths = checkboxTree.getCheckedPaths();
		String[] absolutePathsArray = new String[paths.length];

		int i = 0;
		for (TreePath tp : paths) {
			String[] arr = new String[tp.getPath().length];

			Object[] pathWords = tp.getPath();
			for (int j = 0; j < pathWords.length; j++) {
				arr[j] = pathWords[j] + "";
			}
			absolutePathsArray[i] = folderpath.getFilePathFromNodeArray(arr);
			i++;
		}

		CheckedPaths.setPathsArray(absolutePathsArray);
		CheckedPaths.setCheckedTreePaths(paths);

		// Enable analyse button
		if (SidebarPanel.analyseBtn != null) {
			if (absolutePathsArray.length > 0) {
				SidebarPanel.analyseBtn.setEnabled(true);
			} else {
				SidebarPanel.analyseBtn.setEnabled(false);
			}
			SidebarPanel.getSidebarPanel().revalidate();
			SidebarPanel.getSidebarPanel().repaint();
		}
	}

}
