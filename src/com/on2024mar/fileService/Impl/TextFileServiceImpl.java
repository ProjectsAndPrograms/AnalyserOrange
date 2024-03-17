package com.on2024mar.fileService.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

import com.on2024mar.fileService.TextFileService;

public class TextFileServiceImpl extends FileServiceImpl implements TextFileService {

	@Override
	public Long getLineOfCode(File file) {
		if (file != null && file.exists() && !file.isDirectory()) {
			try {

				BufferedReader br = new BufferedReader(new FileReader(file));
				if (br.lines() != null) {
					long number = (long) br.lines().count();
					br.close();
					return number;
				}
				br.close();
				return 0l;

			} catch (IOException e) {
				return 0l;
			}
		}
		return 0l;
	}

	@Override
	public Long getCharacterCount(File file) {
		try {
			FileReader reader = new FileReader(file);
			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			tokenizer.wordChars(35, 255);
			tokenizer.whitespaceChars(0, ' ');
			tokenizer.eolIsSignificant(true);

			long charCount = 0l;
			while ((tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
				switch (tokenizer.ttype) {
				case StreamTokenizer.TT_EOF:
					charCount++;
				case StreamTokenizer.TT_WORD:
					charCount += (tokenizer.sval) == null ? 0 : tokenizer.sval.length();
					break;
				default:
					charCount += (tokenizer.sval) == null ? 0 : tokenizer.sval.length();
					break;
				}
			}
			return charCount;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Long getWordCount(File file) {

		try {
			FileReader reader = new FileReader(file);
			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			tokenizer.wordChars(35, 255);
			tokenizer.whitespaceChars(0, ' ');
			tokenizer.eolIsSignificant(true);

			long wordCount = 0l;
			while ((tokenizer.nextToken()) != StreamTokenizer.TT_EOF) {
				switch (tokenizer.ttype) {
				case StreamTokenizer.TT_WORD:
					wordCount++;
					break;
				}
			}
			return wordCount;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
