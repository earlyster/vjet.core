/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.vjet.dsf.tests.jsast.parser;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.eclipse.vjet.dsf.jst.IJstMethod;
import org.eclipse.vjet.dsf.jst.IJstNode;
import org.eclipse.vjet.dsf.jst.IJstType;
import org.eclipse.vjet.dsf.jst.declaration.JstVars;
import org.eclipse.vjet.dsf.jst.expr.AssignExpr;
import org.eclipse.vjet.dsf.jst.term.JstIdentifier;
import org.eclipse.vjet.dsf.jst.token.IStmt;
import org.eclipse.vjet.dsf.jstojava.parser.VjoParser;
import org.eclipse.vjet.dsf.jstojava.report.ErrorReporter;
import org.eclipse.vjet.dsf.jstojava.translator.TranslateCtx;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



import org.eclipse.vjet.dsf.common.resource.ResourceUtil;

/**
 * Tests if explicit static in .props is ok
 * 
 * 
 *
 */
//@ModuleInfo(value="DsfPrebuild",subModuleId="JsToJava")
public class OptionalParamTest implements ICommentConstants {
	
	private static final String fileName = FOLDER + "/OptionalParam.vjo";
	
	private static IJstType jstType = null;
	private static TranslateCtx ctx = null;
	
	@BeforeClass
	public static void setUpJst() throws Exception {
		VjoParser p = new VjoParser();
		ctx = new TranslateCtx();

		// get file
		File resource= new File(ResourceUtil.getResource(OptionalParamTest.class,
				                fileName).getFile());
		URL url = ResourceUtil.getResource(BadCommentTest.class,fileName);
		String fileAsString = JsPreGenHelper.url2String(url);
		jstType = p.parse(fileName, resource.getAbsolutePath(), fileAsString, ctx);
		assertNotNull(jstType);
	}
		
	@Test
	//@Category({P2,UNIT,FAST})
	//@Description("Verifies local function overloading supported")
	public void verifyMethodOverloadingWithOptionalParameters() {
		final IJstMethod bar = jstType.getStaticMethod("bar");
		verifyFunctionSignature(bar);
		
		assertTrue(bar.getBlock().getStmts().size() > 0);
		for(IStmt stmt: bar.getBlock().getStmts()){
			if(stmt instanceof JstVars){
				for(AssignExpr assignment: ((JstVars)stmt).getAssignments()){
					assertNotNull(assignment);
					assertNotNull(assignment.getLHS());
					if(assignment.getLHS() instanceof JstIdentifier){
						final JstIdentifier identifier = (JstIdentifier)assignment.getLHS();
						assertNotNull(identifier);
						final IJstNode jstBinding = identifier.getJstBinding();
						assertNotNull(jstBinding);
						assertTrue(jstBinding instanceof IJstMethod);
						final IJstMethod localFunction = (IJstMethod)jstBinding;
						verifyFunctionSignature(localFunction);
					}
				}
			}
		}
		
		
		ErrorReporter er = ctx.getErrorReporter();
		assertFalse(er.hasErrors());
	}

	private void verifyFunctionSignature(IJstMethod bar) {
		assertNotNull(bar);
		assertTrue(bar.isDispatcher());
		assertNotNull(bar.getOverloaded());
		assertEquals(2, bar.getOverloaded().size());
		assertNotNull(bar.getOverloaded().get(0));
		assertNotNull(bar.getOverloaded().get(1));
		assertEquals(0, bar.getOverloaded().get(0).getArgs().size());
		assertEquals(1, bar.getOverloaded().get(1).getArgs().size());
		assertEquals("String", bar.getOverloaded().get(1).getArgs().get(0).getType().getSimpleName());
	}
	
	
	@AfterClass
	public static void tearDownJst() throws Exception {
		ctx = null;
		jstType = null;
	}
}
