package com.on2024mar.ui.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.on2024mar.constants.AppColor;
import com.on2024mar.ui.scrollbar.ScrollBarCustom;

public class Table extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8360732343644841199L;

	private int hoveredRow = -1;

	public Table() {
		super();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("FILE TYPE");
		model.addColumn("FILE COUNT");
		model.addColumn("LINE COUNT");
		model.addColumn("WORD COUNT");
		model.addColumn("SIZE");

		this.setModel(model);

		setRowSelectionAllowed(true);
		setEnabled(false);
		setShowHorizontalLines(true);
		setShowVerticalLines(false);
		setIntercellSpacing(new java.awt.Dimension(0, 0));
		setGridColor(new Color(230, 230, 230));
		setRowHeight(40);

		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setDefaultRenderer(new CustomTableHeaderRenderer());
		setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setBorder(noFocusBorder);
				com.setForeground(new Color(102, 102, 102));
				if (isSelected) {
					com.setBackground(AppColor.WARNING);
				} else {
					com.setBackground(AppColor.LIGHT);
				}
				this.setHorizontalAlignment(JLabel.CENTER);
				return com;
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				Point p = e.getPoint();
				hoveredRow = rowAtPoint(p);
				setRowSelectionInterval(hoveredRow, hoveredRow);
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				clearSelection();
				repaint();
			}

		});

	}

	@Override
	public TableCellEditor getCellEditor(int row, int column) {
		if (column == 4) {
			return super.getCellEditor(row, column);
		} else {
			return super.getCellEditor(row, column);
		}
	}

	public void addRow(Object[] row) {
		DefaultTableModel model = (DefaultTableModel) getModel();
		model.addRow(row);
	}

	public void fixTable(JScrollPane scroll) {
		scroll.getViewport().setBackground(AppColor.LIGHT);
		scroll.setVerticalScrollBar(new ScrollBarCustom());
		JPanel p = new JPanel();
		p.setBackground(AppColor.WHITE);

		scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		scroll.setBorder(new EmptyBorder(5, 10, 5, 10));

	}

	static class CustomTableHeaderRenderer implements TableCellRenderer {
		private final JLabel label;

		public CustomTableHeaderRenderer() {
			label = new JLabel();
			label.setOpaque(true);
			label.setIcon(new ImageIcon("images/folder.png"));
			label.setBackground(new Color(200, 200, 200));
			label.setFont(new Font("SansSerif", Font.BOLD, 12));
			label.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
			label.setForeground(Color.BLACK);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			TableHeader header = new TableHeader(value + "");

			header.setHorizontalAlignment(JLabel.CENTER);
			return header;
		}
	}

}
