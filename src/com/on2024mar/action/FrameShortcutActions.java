package com.on2024mar.action;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import com.on2024mar.ui.MainFrame;
import com.on2024mar.ui.StartupCenterPanel;
import com.on2024mar.ui.sidebar.SidebarPanel;

public class FrameShortcutActions {

	public static void enableFrameShortcutActions() {

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
			if (e.getID() == KeyEvent.KEY_PRESSED && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_O) {
				SelectFolderAction.openFolderAction();
				return true;
			} else if (e.getID() == KeyEvent.KEY_PRESSED && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
				MainFrame.setSidebarCopy(null);
				MainFrame.addToMainFrame(new SidebarPanel());
				MainFrame.splitPane.setRightComponent(new StartupCenterPanel());
				return true;
			} else if (e.getID() == KeyEvent.KEY_PRESSED && e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q) {
				System.exit(0);
				return true;
			}
			return false;
		});

	}

}
