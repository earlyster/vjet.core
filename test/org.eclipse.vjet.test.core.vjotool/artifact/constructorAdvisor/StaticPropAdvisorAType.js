/*******************************************************************************
 * Copyright (c) 2012 eBay Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     eBay Inc. - initial API and implementation
 *******************************************************************************/
vjo.ctype('constructorAdvisor.StaticPropAdvisorAType') //<public abstract
.props({
  saprop1 : 25,
  saprop2 : "Test",

  saFunction1 :  function () {
 	
  },
  
  main: function() {
			var x = new Array();
			alert("Hi");
	},

  saFunction2 :  function () {
	
  }
})
.protos({
  paprop2 : "Test",

  paFunction1 :  function () {
	
  },
  
  //> public void constructs 
	 constructs : function(){
 
 	} 
})
.endType();