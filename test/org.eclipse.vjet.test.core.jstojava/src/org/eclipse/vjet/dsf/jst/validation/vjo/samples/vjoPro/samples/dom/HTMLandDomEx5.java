/*******************************************************************************
 * Copyright (c) 2005-2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.vjet.dsf.jst.validation.vjo.samples.vjoPro.samples.dom;




import java.util.List;

import org.eclipse.vjet.dsf.jsgen.shared.ids.FieldProbIds;
import org.eclipse.vjet.dsf.jsgen.shared.validation.vjo.VjoSemanticProblem;
import org.eclipse.vjet.dsf.jst.validation.vjo.VjoValidationBaseTester;
import org.junit.Before;
import org.junit.Test;




/**
 * HTMLandDomEx5.java
 * 
 * @author <a href="mailto:liama@ebay.com">liama</a>
 * @since JDK 1.5
 */
//@Category( { P3, FAST, UNIT })
//@ModuleInfo(value="DsfPrebuild",subModuleId="JsToJava")
public class HTMLandDomEx5 extends VjoValidationBaseTester {

    @Before
    public void setUp() {
        expectProblems.clear();
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 24, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 25, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 26, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 37, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 38, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 39, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 50, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 51, 0));
        expectProblems
                .add(createNewProblem2(FieldProbIds.UndefinedField, 52, 0));
    }

    @Test
    //@Category( { P3, FAST, UNIT })
    //@Description("Test VJO Sample project, To validate false positive ")
    public void testHTMLandDomEx5() {
        List<VjoSemanticProblem> problems = getVjoSemanticProblem(
                VjoValidationBaseTester.VJLIB_FOLDER, "vjoPro.samples.dom.",
                "HTMLandDomEx5.js", this.getClass());
        assertProblemEquals(expectProblems, problems);
    }
}
