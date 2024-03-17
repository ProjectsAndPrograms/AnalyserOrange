package com.on2024mar.fileService;

import java.io.File;

public interface FolderService {

	File openFolderSelector();

	String getFolderCreationDate(String file);

	String getFolderModifiedDate(String file);

}
