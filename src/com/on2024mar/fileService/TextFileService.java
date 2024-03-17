package com.on2024mar.fileService;

import java.io.File;

public interface TextFileService {
	Long getLineOfCode(File file);

	Long getWordCount(File file);

	Long getCharacterCount(File file);

}
