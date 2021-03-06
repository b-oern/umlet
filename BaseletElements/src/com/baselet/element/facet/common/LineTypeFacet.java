package com.baselet.element.facet.common;

import java.util.Arrays;
import java.util.List;

import com.baselet.control.enums.LineType;
import com.baselet.element.facet.FirstRunKeyValueFacet;
import com.baselet.element.facet.PropertiesParserState;

public class LineTypeFacet extends FirstRunKeyValueFacet {

	public static final LineTypeFacet INSTANCE = new LineTypeFacet();

	private LineTypeFacet() {}

	@Override
	public KeyValue getKeyValue() {
		return new KeyValue("lt",
				new ValueInfo(LineType.SOLID.getValue(), "solid lines"),
				new ValueInfo(LineType.DASHED.getValue(), "dashed lines"),
				new ValueInfo(LineType.DOTTED.getValue(), "dotted lines"));
	}

	private static final List<LineType> supportedTypes = Arrays.asList(LineType.SOLID, LineType.DASHED, LineType.DOTTED);

	@Override
	public void handleValue(String value, PropertiesParserState state) {
		LineType lt = null;
		for (LineType s : supportedTypes) {
			if (s.getValue().equals(value)) {
				lt = s;
			}
		}
		if (lt == null) {
			throw new RuntimeException(); // will be translated to usage message
		}
		state.getDrawer().setLineType(lt);
	}

}
