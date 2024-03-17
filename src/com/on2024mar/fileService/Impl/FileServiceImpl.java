package com.on2024mar.fileService.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.on2024mar.fileService.FileService;

public class FileServiceImpl implements FileService {

	@Override
	public Long getSizeOfFile(File file) {
		if (file != null && file.exists()) {
			try {
				return Files.size(Paths.get(file.getAbsolutePath()));
			} catch (IOException e) {
				return null;
			}
		}

		return null;
	}

	@Override
	public Long getSizeOfFiles(ArrayList<String> paths) {
		if (paths == null) {
			return null;
		}
		long size = 0l;
		for (String filePath : paths) {
			File file = new File(filePath);
			if (file.exists()) {
				size += file.length();
			}
		}

		return size;
	}

	@Override
	public ArrayList<File> hiddenList(ArrayList<String> paths) {

		if (paths == null) {
			return null;
		}
		ArrayList<File> hiddenList = new ArrayList<>();
		for (String filePath : paths) {
			File file = new File(filePath);
			if (file.isHidden()) {
				hiddenList.add(file);
			}
		}

		return hiddenList;
	}

	@Override
	public String getOwner(File file) {
		if (!file.exists()) {
			return null;
		}

		try {
			Path path = Paths.get(file.getAbsolutePath());
			FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
			UserPrincipal owner = ownerAttributeView.getOwner();

			return owner.getName();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	@Override
	public String getPermissionString(File file) {
		if (!file.exists()) {
			return null;
		}

		String opertingsytem = System.getProperty("os.name").toLowerCase();

		if (opertingsytem.contains("linux")) {
			try {
				Path path = Paths.get(file.getAbsolutePath());
				Set<PosixFilePermission> posixPermissions = Files.getPosixFilePermissions(path);
				StringBuilder permissionString = new StringBuilder();

				for (PosixFilePermission permission : PosixFilePermission.values()) {

					if (posixPermissions.contains(permission)) {

						switch (permission) {
						case OWNER_READ:
						case GROUP_READ:
						case OTHERS_READ:
							permissionString.append('r');
							break;
						case OWNER_WRITE:
						case GROUP_WRITE:
						case OTHERS_WRITE:
							permissionString.append('w');
							break;
						case OWNER_EXECUTE:
						case GROUP_EXECUTE:
						case OTHERS_EXECUTE:
							permissionString.append('x');
							break;
						}
					} else {

						permissionString.append('-');
					}
				}

				return permissionString.toString();

			} catch (Exception e) {
				System.out.println(
						"Exception occure : /AnalyserOrange/src/com/on2024mar/fileService/Impl/FileServiceImpl.java");
//				e.printStackTrace();
			}
		}

		if (file.exists()) {
			String permission = "";
			if (file.canRead()) {
				permission += "r";
			} else {
				permission += "-";
			}

			if (file.canWrite()) {
				permission += "w";
			} else {
				permission += "-";
			}
			if (file.canExecute()) {
				permission += "x";
			} else {
				permission += "-";
			}
			return permission;
		}

		return null;
	}

	@Override
	public String getCreationDate(File file) {
		try {
			Path path = Paths.get(file.getAbsolutePath());
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
	public String getLastModifiedDate(File file) {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			BasicFileAttributes view = Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();

			Date date = new Date(view.lastModifiedTime().toMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			return sdf.format(date);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getExtension(File file) {
		String filePath = file.getAbsolutePath();
		if (filePath.contains(".")) {
			return (filePath.substring(filePath.lastIndexOf(".") + 1)).toLowerCase();
		}
		return null;
	}

}
