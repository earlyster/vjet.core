/*******************************************************************************
 * Copyright (c) 2005-2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.vjet.test.core.ecma.jst.validation;




import java.util.List;

import org.eclipse.vjet.dsf.jsgen.shared.ids.MethodProbIds;
import org.eclipse.vjet.dsf.jsgen.shared.validation.vjo.VjoSemanticProblem;
import org.junit.Before;
import org.junit.Test;




/**
 * Js15ExceptionsTests.java
 * 
 * @author <a href="mailto:liama@ebay.com">liama</a>
 * @since JDK 1.5
 */
//@Category( { P3, FAST, UNIT })
//@ModuleInfo(value="DsfPrebuild",subModuleId="JsToJava")
public class Js15ExceptionsTests extends VjoValidationBaseTester {

    @Before
    public void setUp() {
        expectProblems.clear();
        int headersize = 26;
		expectProblems.add(createNewProblem(
                MethodProbIds.WrongNumberOfArguments, 260+headersize, 0));
        expectProblems.add(createNewProblem(
                MethodProbIds.WrongNumberOfArguments, 271+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.ParameterMismatch,
                1002+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 61+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 99+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 558+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 559+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 560+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 561+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 562+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 563+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 564+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 574+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 616+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 1059+headersize, 0));
        expectProblems.add(createNewProblem(MethodProbIds.UnreachableStmt, 1104+headersize, 0));

    }

    @Test
    //@Category( { P3, FAST, UNIT })
    //@Description("Test DSF project, To validate false positive ")
    public void testJs15ExceptionsTests() {
        List<VjoSemanticProblem> problems = getVjoSemanticProblem(
                "vjet.dsf.jslang.feature.tests.", "Js15ExceptionsTests.js", this
                        .getClass());
        assertProblemEquals(expectProblems, problems);
    }
}
