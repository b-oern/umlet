package com.baselet.element.facet.common;

import java.util.Arrays;
import java.util.List;

import com.baselet.control.basics.XValues;
import com.baselet.diagram.draw.DrawHandler;
import com.baselet.diagram.draw.DrawHandler.Layer;
import com.baselet.element.facet.Facet;
import com.baselet.element.facet.PropertiesParserState;
import com.baselet.gui.AutocompletionText;

public class SeparatorLineFacet extends Facet {

	public static final SeparatorLineFacet INSTANCE = new SeparatorLineFacet();

	protected SeparatorLineFacet() {}

	public static final String KEY = "--";

	private static final double Y_SPACE = 5;

	@Override
	public void handleLine(String line, PropertiesParserState state) {
		DrawHandler drawer = state.getDrawer();
		drawer.setLayer(Layer.Foreground); // should be always on top of background
		double linePos = state.getTextPrintPosition() - drawer.textHeightMax() + Y_SPACE / 2;
		XValues xPos = state.getXLimits(linePos);
		drawer.drawLine(xPos.getLeft() + 0.5, linePos, xPos.getRight() - 1, linePos);
		state.increaseTextPrintPosition(Y_SPACE);
		drawer.setLayer(Layer.Background);
	}

	@Override
	public boolean checkStart(String line, PropertiesParserState state) {
		return line.equals(KEY);
	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		return Arrays.asList(new AutocompletionText(KEY, "draw horizontal line"));
	}

}
