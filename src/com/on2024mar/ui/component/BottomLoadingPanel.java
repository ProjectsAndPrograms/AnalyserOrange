package com.on2024mar.ui.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import com.on2024mar.action.AnalyseActionHandler;
import com.on2024mar.constants.AppColor;
import com.on2024mar.ui.sidebar.SidebarPanel;

public class BottomLoadingPanel extends JPanel {

	private static final long serialVersionUID = 7900359059257478131L;
	public static JButton btn;

	public BottomLoadingPanel() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		this.setBackground(AppColor.LIGHT);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 3, 3));
		panel.setBackground(AppColor.LIGHT);

		JLabel label = new JLabel(new ImageIcon(getClass().getResource("/com/on2024mar/icon/progressBar.gif")));
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));

		btn = new JButton();
		btn.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.red, 1),
				BorderFactory.createLineBorder(Color.white, 1)));
		btn.setBackground(AppColor.DANGER);
		btn.setPreferredSize(new Dimension(18, 18));
		btn.setUI(new CustomBasicButtonUI());
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AnalyseActionHandler.running = false;
				btn.setBackground(AppColor.DARKGREY);
				btn.setEnabled(false);

				JPanel sidebarPanelCopy = SidebarPanel.sidebarPanelCopy;
				sidebarPanelCopy.remove(AnalyseActionHandler.blp);

				sidebarPanelCopy.revalidate();
				sidebarPanelCopy.repaint();
			}
		});

		panel.add(label);
		panel.add(btn);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.add(panel);
	}

}

class CustomBasicButtonUI extends BasicButtonUI {
	@Override
	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();
		Dimension size = b.getSize();
		if (model.isPressed() && model.isArmed()) {
			g.setColor(AppColor.DANGER.darker());
		} else if (model.isRollover()) {
			g.setColor(AppColor.DANGER.brighter());
		} else {
			g.setColor(AppColor.DANGER);
		}
		g.fillRect(0, 0, size.width, size.height);

		super.paint(g, c);
	}
}
