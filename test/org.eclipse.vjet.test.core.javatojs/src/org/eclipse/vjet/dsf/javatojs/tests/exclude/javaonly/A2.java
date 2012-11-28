/*******************************************************************************
 * Copyright (c) 2005-2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.vjet.dsf.javatojs.tests.exclude.javaonly;

import org.eclipse.vjet.dsf.javatojs.anno.AJavaOnly;



public class A2{
	
	@AJavaOnly
	public void method1(){
		B1 b = new B1();
	}
	
	public void method2(){		
	}

}
