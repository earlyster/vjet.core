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
vjo.ctype('vjoPro.samples.classes.HelloWorld') //< public
.props({
s_init:false,
/**
* @return boolean
* @access public
*/
//> public boolean helloWorld()
helloWorld:function(){
alert("hello VJO");
return true;
}
})
.inits(function() {
vjoPro.samples.classes.HelloWorld.s_init = true;
})
.endType();
