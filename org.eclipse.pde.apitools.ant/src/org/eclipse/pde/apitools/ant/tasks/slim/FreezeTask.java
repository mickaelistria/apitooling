/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.pde.apitools.ant.tasks.slim;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.api.tools.internal.provisional.VisibilityModifiers;
import org.eclipse.pde.api.tools.internal.provisional.comparator.ApiComparator;
import org.eclipse.pde.api.tools.internal.provisional.comparator.IDelta;
import org.eclipse.pde.api.tools.internal.provisional.model.IApiBaseline;
import org.eclipse.pde.api.tools.internal.provisional.model.IApiComponent;
import org.eclipse.pde.apitools.ant.internal.DeltaReport;
import org.eclipse.pde.apitools.ant.internal.RootReport;
import org.eclipse.pde.apitools.ant.util.ApiToolsUtils;

/**
 * Ant task to retrieve all deprecation changes (addition or removal) between two api baselines
 */
public class FreezeTask extends AbstractDeltaComparisonTask {
	
	private static final String REPORT_XML_FILE_NAME = "apiFreeze.xml"; //$NON-NLS-1$

	@Override
	public String getReportFileName() {
		return REPORT_XML_FILE_NAME;
	}

	@Override
	public IDelta createDelta(IApiBaseline referenceBaseline,
			IApiBaseline profileBaseline) throws CoreException {
		return ApiComparator.compare(ApiToolsUtils.getResolvableScope(profileBaseline, debug), 
				referenceBaseline, VisibilityModifiers.API, true, null);
	}

	@Override
	public RootReport createReport(IDelta delta, IApiComponent[] components) {
		return new DeltaReport(delta, components, DeltaReport.OTHER);
	}
}
