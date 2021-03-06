/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.vjet.dsf.jst.validation.vjo.access.scope.var;





import java.util.List;

import org.eclipse.vjet.dsf.jsgen.shared.ids.FieldProbIds;
import org.eclipse.vjet.dsf.jsgen.shared.ids.VarProbIds;
import org.eclipse.vjet.dsf.jsgen.shared.validation.vjo.VjoSemanticProblem;
import org.eclipse.vjet.dsf.jst.validation.vjo.VjoValidationBaseTester;
import org.junit.Before;
import org.junit.Test;



/**
 * Access var correct 1tester
 *
 * @author <a href="mailto:liama@ebay.com">liama</a>
 * @since JDK 1.5
 */
//@Category( { P1, FAST, UNIT })
public class AccessVar3Tester extends VjoValidationBaseTester {

    @Before
    public void setUp() {
        expectProblems.clear();
        expectProblems.add(createNewProblem(VarProbIds.UndefinedName, 17, 0));
        expectProblems.add(createNewProblem(FieldProbIds.UndefinedField, 18, 0));
        expectProblems.add(createNewProblem(FieldProbIds.UndefinedField, 19, 0));
        expectProblems.add(createNewProblem(VarProbIds.UndefinedName, 20, 0));
    }
    
    @Test
    //@Category( { P1, FAST, UNIT })
    //@Description("Test access different socpe's variable")
    public void testAccesVisible1() {
        List<VjoSemanticProblem> problems = getVjoSemanticProblem(
                "access.scope.var.","AccessVar3Tester.js", this
                        .getClass());
        assertProblemEquals(expectProblems, problems);
    }
    
}

