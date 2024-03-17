package com.on2024mar.constants;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AppInitConstants {
	public static Font DEFAULT_LINK_FONT = new Font("sansserif", Font.PLAIN, 15);
	public static Font UNDERLILNE_LINK_FONT;

	public static ArrayList<String> IMAGE_EXTENSIONS;

	public AppInitConstants() {

		Font font = DEFAULT_LINK_FONT;
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		UNDERLILNE_LINK_FONT = font.deriveFont(attributes);

		IMAGE_EXTENSIONS = new ArrayList<String>();
		IMAGE_EXTENSIONS.addAll(Arrays.asList("jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp"));
		IMAGE_EXTENSIONS.addAll(Arrays.asList("svg", "ico", "psd", "ai", "eps", "xcf", "raw", "cr2", "dng", "orf",
				"arw", "nef", "rw2", "sr2", "pef", "raf", "mos", "nrw", "3fr", "srf", "mrw", "rwl", "kdc", "fff", "dcr",
				"mef", "erf", "crw", "x3f", "raf", "mfw", "bay", "pdb"));

	}

}
