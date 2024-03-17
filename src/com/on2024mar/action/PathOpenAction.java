package com.on2024mar.action;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.on2024mar.constants.AppColor;
import com.on2024mar.constants.AppConstants;
import com.on2024mar.constants.AppInitConstants;

public class PathOpenAction implements MouseListener, KeyListener, MouseMotionListener {

	JTextArea textArea;

	public PathOpenAction(JTextArea textArea) {
		this.textArea = textArea;
		this.textArea.setFocusable(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1 && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
			String text = textArea.getText();
			File file = new File(text);
			if (file.exists()) {
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					String msg = "<html>Unable to open this file - <br> <i>" + text + "</i> </html>";
					JOptionPane.showMessageDialog(null, msg, "Error While Opening!", JOptionPane.ERROR_MESSAGE,
							new ImageIcon(getClass().getResource(AppConstants.ERROR_ICON)));
				}
			} else {
				String msg = "<html>Unable to open file - <br><i>" + text + "  </i> </html>";
				JOptionPane.showMessageDialog(null, msg, "File not exist!", JOptionPane.ERROR_MESSAGE,
						new ImageIcon(getClass().getResource(AppConstants.ERROR_ICON)));
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
			enableHoverColor(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			enableHoverColor(false);

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		enableHoverColor(false);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 17) {
			enableHoverColor(false);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getSource() == textArea && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
			enableHoverColor(true);
		} else {

		}
	}

	void enableHoverColor(boolean change) {
		if (change) {
			textArea.setForeground(AppColor.PRIMARY);
			Font uNDERLILNE_LINK_FONT = AppInitConstants.UNDERLILNE_LINK_FONT;
			textArea.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			textArea.setFont(uNDERLILNE_LINK_FONT);
		} else {
			textArea.setForeground(AppColor.DARK);
			Font dEFAULT_LINK_FONT = AppInitConstants.DEFAULT_LINK_FONT;
			textArea.setCursor(Cursor.getDefaultCursor());
			textArea.setFont(dEFAULT_LINK_FONT);
		}
	}

}
