/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.vjet.dsf.jsnative;

import org.eclipse.vjet.dsf.jsnative.anno.Alias;
import org.eclipse.vjet.dsf.jsnative.anno.DOMSupport;
import org.eclipse.vjet.dsf.jsnative.anno.DomLevel;
import org.eclipse.vjet.dsf.jsnative.anno.JsMetatype;
import org.eclipse.vjet.dsf.jsnative.anno.Property;

/**
 * http://www.w3.org/TR/REC-html40/struct/tables.html#edef-COLGROUP
 */
@Alias("HTMLColgroupElement")
@DOMSupport(DomLevel.ONE)
@JsMetatype
public interface HtmlColgroup extends HtmlElement {

	@Property String getAlign();
	@Property void setAlign(String align);

	@Property String getChar();
	@Property void setChar(String ch);

	@Property String getCharoff();
	@Property void setCharoff(String chOff);

	@Property int getSpan();
	@Property void setSpan(int span);

	@Property String getVAlign();
	@Property void setVAlign(String vAlign);

	@Property String getWidth();
	@Property void setWidth(String width);

}
