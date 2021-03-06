/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.vjet.dsf.jstojava.parser.error;

import org.eclipse.vjet.dsf.jstojava.parser.ProblemProcessor;
import org.eclipse.mod.wst.jsdt.core.compiler.CategorizedProblem;
import org.eclipse.mod.wst.jsdt.core.compiler.IProblem;

public class MissSemiColProblemProcessor extends ProblemProcessor {


	@Override
	public boolean accept(CategorizedProblem problem) {
		return (problem.getID() == IProblem.ParsingErrorInvalidToken) && containsArguments(problem, "}","VariableDeclarator");
	}

	@Override
	public char[] process(CategorizedProblem problem, char[] source) {
		if (source[problem.getSourceStart()] == '}')
		if (Character.isWhitespace(source[problem.getSourceStart() - 1])) {
			source[problem.getSourceStart() - 1] = ';';
		} 
	return source;
	}

}
