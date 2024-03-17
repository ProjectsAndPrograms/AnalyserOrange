package com.on2024mar.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import com.on2024mar.action.PathOpenAction;
import com.on2024mar.constants.AppColor;
import com.on2024mar.fileService.FileService;
import com.on2024mar.fileService.FolderService;
import com.on2024mar.fileService.Impl.FileServiceImpl;
import com.on2024mar.fileService.Impl.FolderServiceImpl;
import com.on2024mar.ui.component.DataPanel;
import com.on2024mar.ui.component.HeadingPanel;
import com.on2024mar.ui.scrollbar.ScrollBarCustom;

public class FoldersPanel extends JPanel {

	private static final long serialVersionUID = 8984853098855074526L;
	private final String FOLDER_ICON_PATH = "/com/on2024mar/icon/small-folder.png";

	private static JPanel mainPanel;
	JTextArea bottomFillerArea;
	JScrollPane mainPanelScrollPane;
	private FolderService folderService;
	private FileService fileService;

	public FoldersPanel(ArrayList<String> directories, ArrayList<String> files) {

		this.setLayout(new BorderLayout());
		this.setBackground(AppColor.LIGHT);

		folderService = new FolderServiceImpl();
		fileService = new FileServiceImpl();

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(AppColor.LIGHT);

		mainPanelScrollPane = new JScrollPane(mainPanel);
		mainPanelScrollPane.setHorizontalScrollBar(new ScrollBarCustom());
		mainPanelScrollPane.setVerticalScrollBar(new ScrollBarCustom());
		mainPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanelScrollPane.setViewportBorder(null);
		mainPanelScrollPane.setBorder(null);

		mainPanel.add(new HeadingPanel("FOLDERS").addMarginOnHeading(10, 0, 10, 0));
		mainPanelScrollPane.getViewport().setScrollMode(JViewport.WHEN_IN_FOCUSED_WINDOW);

		LinkedHashMap<String, ArrayList<String>> nestedDirecotryMap = getNestedDirecotryMap(directories);

		this.add(mainPanelScrollPane, BorderLayout.CENTER);

		addTextAreaToBottom();

		for (String dir : directories) {

			File file = new File(dir);

			LinkedHashMap<String, String> map = new LinkedHashMap<>();
			ArrayList<String> nestedDirList = nestedDirecotryMap.get(dir);

			map.put("Directory Count", nestedDirList == null ? "Empty" : nestedDirList.size() + "");
			map.put("File Count", getFileCount(dir, files) + "");
			map.put("Creation Date", folderService.getFolderCreationDate(file.getAbsolutePath()));
			map.put("Modify Date", folderService.getFolderModifiedDate(file.getAbsolutePath()));
			map.put("Owner", fileService.getOwner(file));
			map.put("Permissions", fileService.getPermissionString(file));
			getFileCount(dir, files);
			addFolder(dir, map);
		}

	}

	private int getFileCount(String dir, ArrayList<String> files) {
		if (files != null) {
			int countFile = 0;
			for (String file : files) {
				if (dir.equals(file.substring(0, file.lastIndexOf(File.separator)))) {
					countFile++;
				}
			}
			return countFile;
		}
		return 0;
	}

	public LinkedHashMap<String, ArrayList<String>> getNestedDirecotryMap(ArrayList<String> directories) {

		LinkedHashMap<String, ArrayList<String>> directoryMap = createDirecotryMap(directories);
		for (Entry<String, ArrayList<String>> entry : directoryMap.entrySet()) {
			String entryKey = entry.getKey();
			ArrayList<String> entryValue = entry.getValue();

			if (entryValue.contains(entryKey)) {
				entryValue.remove(entryKey);
			}

		}

		return directoryMap;

	}

	public static LinkedHashMap<String, ArrayList<String>> createDirecotryMap(ArrayList<String> directoryList) {
		LinkedHashMap<String, ArrayList<String>> directoryMap = new LinkedHashMap<>();

		for (String directory : directoryList) {

			String[] parts = directory.split(Pattern.quote(File.separator));
			String rootDir = parts[0] + File.separator + parts[1];

			ArrayList<String> childDirs = directoryMap.get(rootDir);
			if (childDirs == null) {
				childDirs = new ArrayList<>();
				directoryMap.put(rootDir, childDirs);
			}

			childDirs.add(directory);

			for (int i = 2; i < parts.length; i++) {
				StringBuilder nestedPath = new StringBuilder(rootDir);
				for (int j = 2; j <= i; j++) {
					nestedPath.append(File.separator).append(parts[j]);
				}
				String nestedDir = nestedPath.toString();
				ArrayList<String> nestedDirs = directoryMap.get(nestedDir);
				if (nestedDirs == null) {
					nestedDirs = new ArrayList<>();
					directoryMap.put(nestedDir, nestedDirs);
				}
				if (!nestedDirs.contains(directory)) {
					nestedDirs.add(directory);
				}
			}
		}

		return directoryMap;
	}

	public void addFolder(String folderPath, Map<String, String> map) {

		HeadingPanel headingPanel = new HeadingPanel(folderPath, FOLDER_ICON_PATH, 15);
		JTextArea textArea = headingPanel.getTextArea();

		this.addKeyListener(new PathOpenAction(textArea));
		this.addMouseListener(new PathOpenAction(textArea));

		mainPanel.add(headingPanel);
		mainPanel.add(new DataPanel(map).addMargin(0, 0, 10, 0));
		mainPanel.remove(bottomFillerArea);
		addTextAreaToBottom();
		scrollToTop();
	}

	void addTextAreaToBottom() {
		bottomFillerArea = new JTextArea();
		bottomFillerArea.setBackground(AppColor.LIGHT);
		bottomFillerArea.setEditable(false);
		bottomFillerArea.setFocusable(false);
		bottomFillerArea.setBorder(null);
		bottomFillerArea.setOpaque(true);
		mainPanel.add(bottomFillerArea);

	}

	private void scrollToTop() {
		SwingUtilities.invokeLater(() -> {
			mainPanelScrollPane.getViewport().setViewPosition(new Point(0, 0));
		});
	}

}
