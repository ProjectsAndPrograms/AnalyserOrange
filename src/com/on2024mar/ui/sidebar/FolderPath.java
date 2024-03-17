package com.on2024mar.ui.sidebar;

import java.io.File;

import javax.swing.tree.TreeNode;

public class FolderPath {

	public static String filePath = "";

	public FolderPath(String filePath) {
		FolderPath.filePath = filePath;
	}

	public String getFilePathFromNodePath(TreeNode[] paths) {

		if (paths != null) {
			String answer = filePath;
			for (int i = 1; i < paths.length; i++) {
				answer += File.separator + paths[i].toString();
			}

			return answer;
		}
		return null;
	}

	public String getFilePathFromNodeArray(String[] array) {
		String answer = filePath;

		// remove first string because it is already contained by file-path
		for (int i = 1; i < array.length; i++) {
			answer += File.separator + array[i];
		}

		return answer;
	}

}
