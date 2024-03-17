package com.on2024mar.fileService;

import java.io.File;
import java.util.ArrayList;

public interface FileService {

	Long getSizeOfFile(File file);

	Long getSizeOfFiles(ArrayList<String> paths);

	ArrayList<File> hiddenList(ArrayList<String> paths);

	String getOwner(File file);

	String getPermissionString(File file);

	String getCreationDate(File file);

	String getLastModifiedDate(File file);

	String getExtension(File file);

}
