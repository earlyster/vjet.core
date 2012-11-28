/*******************************************************************************
 * Copyright (c) 2005, 2012 eBay Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.vjet.dsf.common.statistics;


public class BizOpHistogram extends BaseHistogram {

   public BizOpHistogram() {
      super();
   }

   public String getName() {
      return V4StatisticsUtil.BIZOP_HISTOGRAM_NAME;
   }

   public String getHistogramName() {
      return V4StatisticsUtil.BIZOP_HISTOGRAM_NAME;
   }

   public String getSubObjName() {
      return V4StatisticsUtil.BIZOP;
   }

   public static BizOpHistogram getHistogram() {
      return (BizOpHistogram) V4StatisticsContainer.getInstance().get(
            V4StatisticsUtil.BIZOP_HISTOGRAM_NAME);
   }
}
