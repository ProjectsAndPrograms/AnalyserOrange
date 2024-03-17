package com.on2024mar.ui.scrollbar;

import java.awt.Dimension;

import javax.swing.JScrollBar;

import com.on2024mar.constants.AppColor;

public class ScrollBarCustom extends JScrollBar {

	private static final long serialVersionUID = 8470431091565806853L;

	public ScrollBarCustom() {
		setPreferredSize(new Dimension(5, 5));
		setForeground(AppColor.DARK);
		setUnitIncrement(20);
		setUI(new ModernScrollBarUI());
		setOpaque(false);
	}

}
