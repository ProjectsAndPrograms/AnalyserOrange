package com.on2024mar.fileService.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import com.on2024mar.fileService.FolderService;

public class FolderServiceImpl implements FolderService {

	@Override
	public File openFolderSelector() {
		JFileChooser filechooser = new JFileChooser();
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		filechooser.setCurrentDirectory(new File(File.separator));
		int returnValue = filechooser.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = filechooser.getSelectedFile();
			return file;
		}
		return null;
	}

	@Override
	public String getFolderCreationDate(String file) {

		try {
			Path path = Paths.get(file);
			BasicFileAttributes view = Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();

			Date date = new Date(view.creationTime().toMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			return sdf.format(date);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getFolderModifiedDate(String file) {

		try {
			Path path = Paths.get(file);
			BasicFileAttributes view = Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();

			Date date = new Date(view.lastModifiedTime().toMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			return sdf.format(date);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
