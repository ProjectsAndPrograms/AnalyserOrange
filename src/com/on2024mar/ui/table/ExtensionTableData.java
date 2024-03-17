package com.on2024mar.ui.table;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.on2024mar.constants.AppInitConstants;
import com.on2024mar.fileService.FileService;
import com.on2024mar.fileService.TextFileService;
import com.on2024mar.fileService.Impl.FileServiceImpl;
import com.on2024mar.fileService.Impl.TextFileServiceImpl;
import com.on2024mar.util.SizeUnitFactory;

public class ExtensionTableData {

	ArrayList<String> extensions;
	HashMap<String, HashMap<String, String>> extensionData;
	FileService fileService;
	TextFileService textFileService;

	public ExtensionTableData(ArrayList<String> files) {
		extensions = new ArrayList<>();
		fileService = new FileServiceImpl();
		textFileService = new TextFileServiceImpl();
		extensionData = new HashMap<>();
		constuctExtensionData(files);
	}

	public HashMap<String, HashMap<String, String>> getExtensionsTableData() {
		return extensionData;
	}

	public ArrayList<String> getExtensions() {
		return extensions;
	}

	void constuctExtensionData(ArrayList<String> files) {
		for (String string : files) {

			File file = new File(string);
			if (file.exists()) {
				string = file.getAbsolutePath();

				if (string.contains(".")) {
					String substring = string.toLowerCase().substring(string.lastIndexOf(".") + 1);
					if (!extensions.contains(substring)) {

						extensions.add(substring);
					}
				} else if (!(new File(string).isDirectory())) {
					System.out.println(new File(string).getAbsolutePath());
					if (string.contains(File.separator)) {
						String substring = string.substring(string.lastIndexOf(File.separator) + 1);
						if (!extensions.contains(substring)) {
							extensions.add(substring);
						}
					} else {
						extensions.add(string);
					}
				}
			}
		}

		for (String extension : extensions) {
			HashMap<String, String> data = new HashMap<>();
			long size = 0l;
			int fileCount = 0;
			long lineCount = 0l;
			long wordCount = 0l;
			long charCount = 0l;
			for (String string : files) {
				File file = new File(string);
				if (extension.equalsIgnoreCase(string.substring(string.lastIndexOf(".") + 1)) && !file.isHidden()) {
					size += file.length();
					fileCount++;
					lineCount += textFileService.getLineOfCode(file);
					Long words = textFileService.getWordCount(file);
					Long chars = textFileService.getCharacterCount(file);

					wordCount += (words == null) ? 0 : words;
					charCount += (chars == null) ? 0 : chars;
				}
			}

			data.put("size", SizeUnitFactory.getFormattedSize(size));
			data.put("fileCount", fileCount + "");

			if (AppInitConstants.IMAGE_EXTENSIONS.contains(extension)) {
				data.put("lineCount", "-");
				data.put("wordCount", "-");
				data.put("charCount", "-");
			} else {
				data.put("lineCount", lineCount + "");
				data.put("wordCount", wordCount + "");
				data.put("charCount", charCount + "");
			}

			extensionData.put(extension, data);
		}

	}

}
