/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.vjet.vjo.tool.codecompletion.advisor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.vjet.dsf.jst.IJstMethod;
import org.eclipse.vjet.dsf.jst.IJstNode;
import org.eclipse.vjet.dsf.jst.IJstProperty;
import org.eclipse.vjet.dsf.jst.IJstType;
import org.eclipse.vjet.dsf.jst.declaration.JstAttributedType;
import org.eclipse.vjet.dsf.jst.declaration.JstFuncType;
import org.eclipse.vjet.dsf.jst.declaration.JstMixedType;
import org.eclipse.vjet.dsf.jst.declaration.JstObjectLiteralType;
import org.eclipse.vjet.dsf.jst.util.JstTypeHelper;
import org.eclipse.vjet.vjo.tool.codecompletion.CodeCompletionUtils;
import org.eclipse.vjet.vjo.tool.codecompletion.IVjoCcAdvisor;
import org.eclipse.vjet.vjo.tool.codecompletion.VjoCcCtx;

/**
 * Advise properties and methods from a called JstType. <br>
 * rule: 1 the constructor should be filter out from result.<br>
 * example1:<br>
 * function() { x<cursor> }<br>
 * example2:<br>
 * function() { x.x<cursor> }<br>
 * 
 * Needs attribute:<br>
 * ctx.actingType,<br>
 * ctx.actingToken,<br>
 * ctx.calledType<br>
 * 
 * ProposalData.data IJstProperty and IjstMethod. <b4>
 * 
 * @author jianliu
 * 
 */
public class VjoCcPropMethodProposalAdvisor extends AbstractVjoCcAdvisor
		implements IVjoCcAdvisor {
	public static final String ID = VjoCcPropMethodProposalAdvisor.class
			.getName();

	public void advise(VjoCcCtx ctx) {
		String token = ctx.getActingToken();
		IJstType callingType = ctx.getActingType();
		IJstType calledType = ctx.getCalledType();
		if(calledType==null && callingType == null){
			return;
		}
		if (calledType == null) {
			calledType = callingType;
		}
		boolean isNative = CodeCompletionUtils.isNativeType(calledType);
		IJstType tempCalledType = calledType;
		int[] levels = getCallLevel(callingType, calledType);
		List<String> tempString = new ArrayList<String>();
		// TODO test with other jst proxy types // fixing JstFuncType for now 
		// any function reference is defined by JstFuncType
		if(calledType instanceof JstFuncType){
			calledType = ((JstFuncType) calledType).getType();
		}
		
		List<? extends IJstMethod> methods = JstTypeHelper.getSignatureMethods(calledType, false, false);
		Iterator<? extends IJstMethod> it = methods.iterator();
		while (it.hasNext()) {
			IJstMethod method = it.next();
			if(!isReferenceByDot(method)){
				continue;
			}
			
			if (tempCalledType != method.getOwnerType()) {
				tempCalledType = method.getOwnerType();
				if (tempCalledType == null) {
					tempCalledType = calledType;
				}
				levels = getCallLevel(callingType, tempCalledType);
				isNative = CodeCompletionUtils.isNativeType(tempCalledType);
			}
			boolean exactMatch = exactMatch(method.getName().getName(), token);
			boolean basicMatch = exactMatch;
			if (!exactMatch) {
				basicMatch = basicMatch(method.getName().getName(), token);
			}
			if (exactMatch || basicMatch) {
				addMethod(method, levels, isNative, ctx, tempString, exactMatch);
			}
		}
		
		List<IJstProperty> properties = new ArrayList<IJstProperty>();
		if(calledType instanceof JstMixedType){
			properties = handleMixedType((JstMixedType)calledType);
		}else{
			properties = getTypeProperties(calledType);
		}
		
		Iterator<IJstProperty> it1 = properties.iterator();
		while (it1.hasNext()) {
			
			IJstProperty property = it1.next();
//			removed by huzhou@ebay.com, will handle from presenter
//			if(!isReferenceByDot(property)){
//				continue;
//			}
			if (tempCalledType != property.getOwnerType()) {
				tempCalledType = property.getOwnerType();
				levels = getCallLevel(callingType, tempCalledType);
				isNative = CodeCompletionUtils.isNativeType(tempCalledType);
			}
			boolean exactMatch = exactMatch(property.getName().getName(), token);
			boolean basicMatch = exactMatch;
			if (!exactMatch) {
				basicMatch = basicMatch(property.getName().getName(), token);
			}
			if (levelCheck(property.getModifiers(), levels)
					&& (exactMatch || basicMatch)) {
				appendData(ctx, property, (exactMatch && basicMatch));
			}
		}
	}

	private List<IJstProperty> handleMixedType(JstMixedType calledType) {
		List<IJstProperty> props = new ArrayList<IJstProperty>();
		for(IJstType type: calledType.getMixedTypes()){
			if(type instanceof JstAttributedType){
				JstAttributedType atype = ((JstAttributedType)type);
				IJstNode otypecandidate = atype.getJstBinding();
				if(otypecandidate!=null && otypecandidate instanceof JstObjectLiteralType){
					JstObjectLiteralType otype = (JstObjectLiteralType)otypecandidate;
					props.addAll(getTypeProperties(otype));
					
				}
				
			}
			if(type instanceof JstObjectLiteralType){
				JstObjectLiteralType otype = ((JstObjectLiteralType)type);
				if(otype!=null){
					props.addAll(getTypeProperties(otype));
				}
			}
			
		}
		return props;
	}

	private List<IJstProperty> getTypeProperties(IJstType calledType) {
		
		
		return calledType.getAllPossibleProperties(false, false);
	}

	private boolean isReferenceByDot(IJstMethod method) {
		if(method.getName()!=null){
			if(method.getName().getName().startsWith("\'" )){
				return false;
			}else if(method.getName().getName().startsWith("\"" )){
				return false;
			}
		}
		
		return true;
	}
	
	private void addMethod(IJstMethod method, int[] levels, boolean isNative,
			VjoCcCtx ctx, List<String> tempString, boolean exactMatch) {
		if (!method.isConstructor()
				&& levelCheck(method.getModifiers(), levels)
				&& (!isNative || !method.getName().getName().startsWith("_"))) {
			String str = CodeCompletionUtils.getMthodsStr(method);
			if (!(tempString.contains(str))) {
				tempString.add(str);
				appendData(ctx, method, exactMatch);
			}
		}

	}

	/**
	 * the level should be public, protected, and private. current type (this.),
	 * should show all the method and propertyes. superType: public, and
	 * protected Type in same package: public, default. others: public
	 * 
	 * @param ctx
	 * @return TODO: it should return int[] based on the relationship between
	 *         called type and calling type
	 */
	protected int[] getCallLevel(IJstType callingType, IJstType calledType) {
		return CodeCompletionUtils.getGeneralFieldCallLevel(callingType,
				calledType);
	}

}
