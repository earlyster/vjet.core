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
vjo.ctype('vjo.vjet.Sample1')
//> needs(vjo.vjet.Sample2)
.props({
        //>public void foo() 
        foo : function(){
                var abc;  //<vjo.vjet.Sample2
        }
})
.endType();
