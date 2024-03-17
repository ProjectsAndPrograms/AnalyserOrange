package com.on2024mar.ui.sidebar;

import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.on2024mar.action.CheckedEventHandler;
import com.on2024mar.constants.AppColor;

public class FolderSelectorTree {

	public static JCheckBoxTree checkboxTree;

	public FolderSelectorTree(File file) {

		FolderPath filePath = new FolderPath(file.getAbsolutePath());

		checkboxTree = new JCheckBoxTree();
		checkboxTree.setBackground(AppColor.LIGHT);
		checkboxTree.setFolderPath(filePath);
		checkboxTree.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		checkboxTree.setFont(new Font("Dialog", Font.PLAIN, 13));
		checkboxTree.addCheckChangeEventListener(new CheckedEventHandler(checkboxTree, filePath));

		checkboxTree.setModel(folderHirarchyTreeModel(file));
		checkboxTree.putClientProperty("JTree.LineStyle", "Angled");
	}

	public static TreeModel folderHirarchyTreeModel(File file) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
		for (File f : file.listFiles()) {
			createFolderHirarchy(f, root);
		}
		return new DefaultTreeModel(root);
	}

	public static void createFolderHirarchy(File file, DefaultMutableTreeNode parentNode) throws NullPointerException {

		if (!file.isHidden() && file.exists()) {
			if (file.isDirectory() && file != null) {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());

				parentNode.add(node);
				for (File subfile : file.listFiles()) {
					createFolderHirarchy(subfile, node);
				}
			} else {
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());

				parentNode.add(node);
			}
		}

	}

	public JTree getFolderSelectorTree() {
		return checkboxTree;
	}
}
