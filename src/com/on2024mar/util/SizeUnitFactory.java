package com.on2024mar.util;

public class SizeUnitFactory {

	public static String getFormattedSize(long bytes) {
		String[] units = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
		int unitIndex = 0;
		double size = bytes;

		while (size >= 1024 && unitIndex < units.length - 1) {
			size /= 1024;
			unitIndex++;
		}

		return String.format("%.2f %s", Math.floor(size * 100) / 100, units[unitIndex]);

	}

}
