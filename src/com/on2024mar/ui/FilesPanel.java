package com.on2024mar.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import com.on2024mar.constants.AppColor;
import com.on2024mar.constants.AppInitConstants;
import com.on2024mar.fileService.FileService;
import com.on2024mar.fileService.TextFileService;
import com.on2024mar.fileService.Impl.FileServiceImpl;
import com.on2024mar.fileService.Impl.TextFileServiceImpl;
import com.on2024mar.ui.component.DataPanel;
import com.on2024mar.ui.component.HeadingPanel;
import com.on2024mar.ui.scrollbar.ScrollBarCustom;
import com.on2024mar.util.SizeUnitFactory;

public class FilesPanel extends JPanel {

	private static final long serialVersionUID = -6733421729930941989L;

	private final String FILE_ICON_PATH = "/com/on2024mar/icon/small-file.png";
	private static JPanel mainPanel;
	JTextArea bottomFillerArea;

	private FileService fileService;
	private TextFileService textFileService;
	private JScrollPane mainPanelScrollPane;

	public FilesPanel(ArrayList<String> directories, ArrayList<String> files) {

		this.setLayout(new BorderLayout());
		this.setBackground(AppColor.LIGHT);
		fileService = new FileServiceImpl();
		textFileService = new TextFileServiceImpl();

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(AppColor.LIGHT);

		mainPanelScrollPane = new JScrollPane(mainPanel);
		mainPanelScrollPane.setHorizontalScrollBar(new ScrollBarCustom());
		mainPanelScrollPane.setVerticalScrollBar(new ScrollBarCustom());
		mainPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanelScrollPane.setViewportBorder(null);
		mainPanelScrollPane.getViewport().setScrollMode(JViewport.WHEN_IN_FOCUSED_WINDOW);
		mainPanelScrollPane.setBorder(null);

		mainPanel.add(new HeadingPanel("FILES").addMarginOnHeading(10, 0, 10, 0));

		this.add(mainPanelScrollPane, BorderLayout.CENTER);

		addTextAreaToBottom();

		for (String filePath : files) {

			File file = new File(filePath);

			if (!file.exists()) {
				continue;
			}

			LinkedHashMap<String, String> map = new LinkedHashMap<>();

			Long fileSize = fileService.getSizeOfFile(file);
			String owner = fileService.getOwner(file);
			String permissions = fileService.getPermissionString(file);
			String createDate = fileService.getCreationDate(file);
			String modifyDate = fileService.getLastModifiedDate(file);

			map.put("Size", fileSize == null ? "-" : (SizeUnitFactory.getFormattedSize(fileSize)));
			map.put("Owner", owner == null ? "Unknown" : (owner + ""));
			map.put("Permissions", permissions == null ? "Unknown" : (permissions + ""));
			map.put("Creation Date", createDate == null ? "Unknown" : createDate);
			map.put("Modify Date", modifyDate == null ? "Unknown" : modifyDate);

			String extension = fileService.getExtension(file);
			if (extension != null && AppInitConstants.IMAGE_EXTENSIONS.contains(extension)) {
				ImageIcon icon = new ImageIcon(file.getAbsolutePath());
				map.put("Image type", "image/" + extension);
				map.put("Width", icon.getIconWidth() + " pixels");
				map.put("Height", icon.getIconHeight() + " pixels");

			} else {
				Long lineOfCode = textFileService.getLineOfCode(file);
				Long wordCount = textFileService.getWordCount(file);
				Long charCount = textFileService.getCharacterCount(file);
				map.put("Line Count", lineOfCode == null ? "-" : (lineOfCode + ""));
				map.put("Word Count", wordCount == null ? "-" : (wordCount + ""));
				map.put("Character Count", charCount == null ? "-" : (charCount + ""));
			}

			addFile(file.getAbsolutePath(), map);
		}

	}

	public void addFile(String folderPath, Map<String, String> hashmap) {
		mainPanel.add(new HeadingPanel(folderPath, FILE_ICON_PATH, 15));
		mainPanel.add(new DataPanel(hashmap).addMargin(0, 0, 10, 0));
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

	void scrollToTop() {
		SwingUtilities.invokeLater(() -> {
			mainPanelScrollPane.getViewport().setViewPosition(new Point(0, 0));
		});
	}

}
