package com.on2024mar.ui.component;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.Element;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.WrappedPlainView;

public class LineWrapEditorKit extends StyledEditorKit {
	private static final long serialVersionUID = 8082558492830330418L;

	@Override
	public ViewFactory getViewFactory() {
		return new LineWrapViewFactory();
	}
}

class LineWrapViewFactory implements ViewFactory {
	public View create(Element elem) {
		String kind = elem.getName();
		if (kind != null && kind.equals(AbstractDocument.ContentElementName)) {
			return new WrappedPlainView(elem);
		} else if (kind != null && kind.equals(AbstractDocument.ParagraphElementName)) {
			return new ParagraphView(elem);
		} else if (kind != null && kind.equals(AbstractDocument.SectionElementName)) {
			return new BoxView(elem, View.Y_AXIS);
		} else {
			return new LabelView(elem);
		}
	}
}