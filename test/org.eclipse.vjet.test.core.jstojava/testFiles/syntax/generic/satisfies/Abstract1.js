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
vjo.ctype('syntax.generic.satisfies.Abstract1') //< public abstract Abstract1<K,V>
//>needs(syntax.generic.Collection)
//>needs(syntax.generic.Comparator)
.props({
	
})
.protos({
	//> public abstract Comparator<? super K> comparator()
    comparator:function(){
    }
})
.endType();