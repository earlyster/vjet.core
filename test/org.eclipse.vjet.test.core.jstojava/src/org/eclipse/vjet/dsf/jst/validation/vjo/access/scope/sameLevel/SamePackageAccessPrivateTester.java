/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/

package org.eclipse.vjet.dsf.jst.validation.vjo.access.scope.sameLevel;




import java.util.List;

import org.eclipse.vjet.dsf.jsgen.shared.ids.FieldProbIds;
import org.eclipse.vjet.dsf.jsgen.shared.ids.MethodProbIds;
import org.eclipse.vjet.dsf.jsgen.shared.validation.vjo.VjoSemanticProblem;
import org.eclipse.vjet.dsf.jst.validation.vjo.VjoValidationBaseTester;
import org.junit.Before;
import org.junit.Test;




/**
 * private
 *
 * @author <a href="mailto:liama@ebay.com">liama</a>
 * @since JDK 1.5
 */
//@ModuleInfo(value="DsfPrebuild",subModuleId="JsToJava")
//@Category( { P1, FAST, UNIT })
public class SamePackageAccessPrivateTester extends VjoValidationBaseTester {

    @Before
    public void setUp(){
        expectProblems.clear();
        expectProblems.add(createNewProblem(FieldProbIds.NotVisibleField, 10, 0));
        expectProblems.add(createNewProblem(FieldProbIds.NotVisibleField, 11, 0));
        expectProblems.add(createNewProblem(MethodProbIds.NotVisibleMethod, 12, 0));
    }

    @Test
    //@Category( { P1, FAST, UNIT })
    //@Description("Test access same package type's instance property wihch declared as private")
    public void testAccesVisible1() {
        List<VjoSemanticProblem> problems = getVjoSemanticProblem(
                "access.scope.privateModifier.",
                "PrivateUser1.js", this.getClass());
        assertProblemEquals(expectProblems, problems);
    }
}
