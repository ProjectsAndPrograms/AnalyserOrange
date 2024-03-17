package com.on2024mar.ui.scrollbar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ModernScrollBarUI extends BasicScrollBarUI {

	private final int THUMB_SIZE = 80;

	@Override
	protected Dimension getMaximumThumbSize() {
		if (scrollbar.getOrientation() == JScrollBar.HORIZONTAL) {
			return new Dimension(THUMB_SIZE, 0);
		} else {
			return new Dimension(0, THUMB_SIZE);
		}
	}

	@Override
	protected Dimension getMinimumThumbSize() {
		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			return new Dimension(0, THUMB_SIZE);
		} else {
			return new Dimension(THUMB_SIZE, 0);
		}
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return new ScrollBarButton();
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		return new ScrollBarButton();
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int x = thumbBounds.x;
		int y = thumbBounds.y;
		int width = thumbBounds.width;
		int height = thumbBounds.height;

		if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
			y += 8;
			height -= 16;
		} else {
			x += 8;
			width -= 16;
		}
		g2d.setColor(scrollbar.getForeground());
		g2d.fillRoundRect(x, y, width, height, 1, 1);
	}

	private class ScrollBarButton extends JButton {
		private static final long serialVersionUID = 2135887150252598038L;

		public ScrollBarButton() {
			setBorder(BorderFactory.createEmptyBorder());
		}

		@Override
		public void paint(Graphics g) {

		}
	}

}
