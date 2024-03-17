package com.on2024mar.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import com.on2024mar.action.PathOpenAction;
import com.on2024mar.constants.AppColor;
import com.on2024mar.fileService.FileService;
import com.on2024mar.fileService.Impl.FileServiceImpl;
import com.on2024mar.ui.component.DataPanel;
import com.on2024mar.ui.component.HeadingPanel;
import com.on2024mar.ui.scrollbar.ScrollBarCustom;
import com.on2024mar.ui.table.ExtensionTableData;
import com.on2024mar.ui.table.Table;
import com.on2024mar.util.SelectedRootDirectory;

public class OverviewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2063451708739298788L;

	private static OverviewPanel copyPanel;
	private static JPanel mainPanel;
	private JScrollPane mainPanelScrollPane;
	private FileService fileService;

	public OverviewPanel(ArrayList<String> directories, ArrayList<String> files) {

		this.setLayout(new BorderLayout());
		this.setBackground(AppColor.LIGHT);
		fileService = new FileServiceImpl();

		createOverviewPanel(directories, files);
		copyPanel = this;
	}

	private void createOverviewPanel(ArrayList<String> directories, ArrayList<String> files) {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(AppColor.LIGHT);

		mainPanelScrollPane = new JScrollPane(mainPanel);
		mainPanelScrollPane.setHorizontalScrollBar(new ScrollBarCustom());
		mainPanelScrollPane.setVerticalScrollBar(new ScrollBarCustom());
		mainPanelScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mainPanelScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		mainPanel
				.add(new HeadingPanel("SUMMERY", "/com/on2024mar/icon/chart.png", 20).addMarginOnHeading(10, 0, 10, 0));

		addRootDirectoryHeading();
		addRootData(directories, files);
		createOverviewTable(files);
		fillEmptyGapAtBottom();

		this.add(mainPanelScrollPane, BorderLayout.CENTER);
		mainPanelScrollPane.getViewport().setScrollMode(JViewport.WHEN_IN_FOCUSED_WINDOW);
		SwingUtilities.invokeLater(() -> {
			mainPanelScrollPane.getViewport().setViewPosition(new Point(0, 0));
		});
	}

	public static OverviewPanel getCenterPanel() {
		return copyPanel;
	}

	private void addRootDirectoryHeading() {
		File selectedRootDir = new File(SelectedRootDirectory.getSelectedRootDirectory());
		HeadingPanel rootDirHeading;
		if (selectedRootDir.exists()) {
			rootDirHeading = new HeadingPanel(selectedRootDir.getAbsolutePath());
		} else {
			rootDirHeading = new HeadingPanel(makeErrorMessage("Root heading not found..!"));
		}
		rootDirHeading.getTextArea().addMouseListener(new PathOpenAction(rootDirHeading.getTextArea()));
		rootDirHeading.getTextArea().addKeyListener(new PathOpenAction(rootDirHeading.getTextArea()));
		rootDirHeading.getTextArea().addMouseMotionListener(new PathOpenAction(rootDirHeading.getTextArea()));

		mainPanel.add(rootDirHeading);
	}

	private void createOverviewTable(ArrayList<String> files) {
		mainPanel.add(new HeadingPanel("Overview table").addMarginOnHeading(15, 0, 5, 0));
		if (files.size() > 0) {
			mainPanel.add(getExtensionsTable(files));
		} else {
			mainPanel.add(new HeadingPanel("No Files Available", "", 18).addMarginOnHeading(10, 10, 10, 0));
		}
	}

	private void addRootData(ArrayList<String> directories, ArrayList<String> files) {

		Map<String, String> map = getDataMap(directories, files);

		if (map != null) {
			mainPanel.add(new DataPanel(map));
		}
	}

	private void fillEmptyGapAtBottom() {
		JTextArea bottomFillerArea = new JTextArea();
		bottomFillerArea.setBackground(AppColor.LIGHT);
		bottomFillerArea.setEditable(false);
		bottomFillerArea.setFocusable(false);
		bottomFillerArea.setBorder(null);
		bottomFillerArea.setOpaque(true);
		mainPanel.add(bottomFillerArea);
	}

	private JScrollPane getExtensionsTable(ArrayList<String> files) {

		Table table = new Table();

		ExtensionTableData extensionTableData = new ExtensionTableData(files);
		HashMap<String, HashMap<String, String>> extensionsTableData = extensionTableData.getExtensionsTableData();

		for (Entry<String, HashMap<String, String>> entry : extensionsTableData.entrySet()) {
			String key = entry.getKey();
			HashMap<String, String> data = entry.getValue();

			table.addRow(new Object[] { key, data.get("fileCount"), data.get("lineCount"), data.get("wordCount"),
					data.get("size") });
		}

		JScrollPane scrollpane = new JScrollPane(table) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3026226260105766887L;

			@Override
			public Dimension getPreferredSize() {
				int height = (int) (table.getPreferredSize().getHeight()) + 55;
				return new Dimension((int) table.getPreferredSize().getWidth(), height);
			}
		};
		scrollpane.setPreferredSize(new Dimension(200, 300));
		scrollpane.setBackground(AppColor.LIGHT);
		scrollpane.setForeground(AppColor.LIGHT);
		scrollpane.setEnabled(false);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpane.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				JScrollBar parentVerticalScrollBar = mainPanelScrollPane.getVerticalScrollBar();
				parentVerticalScrollBar.setValue(parentVerticalScrollBar.getValue()
						+ e.getWheelRotation() * parentVerticalScrollBar.getUnitIncrement());
			}
		});
		scrollpane.setBorder(BorderFactory.createLineBorder(AppColor.LIGHT, 10));
		return scrollpane;
	}

	private String makeErrorMessage(String msg) {
		return msg + "  occured in /codeAnalyser/src/com/on2024mar/ui/OverviewPanel.java";
	}

	public Map<String, String> getDataMap(ArrayList<String> directories, ArrayList<String> files) {

		if (directories == null && files == null) {
			return null;
		}

		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("Directory count", directories.size() + "");
		map.put("File count", files.size() + "");
		map.put("Hidden Files", fileService.hiddenList(files).size() + "");
		map.put("Hidden Folder", fileService.hiddenList(directories).size() + "");

		Long totalSize = fileService.getSizeOfFiles(files);
		map.put("Size", totalSize == null ? "0" : totalSize + "");

		String selectedRootDirectory = SelectedRootDirectory.getSelectedRootDirectory();
		if (selectedRootDirectory != null) {
			File file = new File(selectedRootDirectory);
			String owner = file.exists() ? fileService.getOwner(file) : "Unknown";
			map.put("Owner", owner);
		}

		return map;
	}

}
