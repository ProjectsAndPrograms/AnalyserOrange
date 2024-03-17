package com.on2024mar;

import javax.swing.SwingUtilities;

import com.on2024mar.ui.MainFrame;

public class Runner {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainFrame());
	}

}
