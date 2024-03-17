package com.on2024mar.util;

import javax.swing.tree.TreePath;

public class CheckedPaths {

	private static String[] pathsArray;
	private static TreePath[] checkedTreePaths;

	public static String[] getPathsArray() {
		return pathsArray;
	}

	public static void setPathsArray(String[] arr) {
		pathsArray = arr;
	}

	public static TreePath[] getCheckedTreePaths() {
		return checkedTreePaths;
	}

	public static void setCheckedTreePaths(TreePath[] checkedTreePaths) {
		CheckedPaths.checkedTreePaths = checkedTreePaths;
	}

}
