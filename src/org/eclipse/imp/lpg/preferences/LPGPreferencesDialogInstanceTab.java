/*******************************************************************************
* Copyright (c) 2007 IBM Corporation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Robert Fuhrer (rfuhrer@watson.ibm.com) - initial API and implementation

*******************************************************************************/

package org.eclipse.imp.lpg.preferences;

import java.util.List;
import java.util.ArrayList;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.imp.preferences.*;
import org.eclipse.imp.preferences.fields.*;

//		 TODO:  Import additional classes for specific field types from
//		 org.eclipse.uide.preferences.fields

/**
 * The instance level preferences tab.
 */


public class LPGPreferencesDialogInstanceTab extends InstancePreferencesTab {

	public LPGPreferencesDialogInstanceTab(IPreferencesService prefService) {
		super(prefService, false);
	}

	/**
	 * Creates specific preference fields with settings appropriate to
	 * the instance preferences level.
	 *
	 * @return    An array that contains the created preference fields
	 *
	 */
	@Override
	protected FieldEditor[] createFields(TabbedPreferencesPage page, Composite parent)
	{
		List fields = new ArrayList();

		BooleanFieldEditor UseDefaultExecutable = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "UseDefaultExecutable", "UseDefaultExecutable", "",
			parent,
			true, true,
			true, true,
			false, false,
			true);
		Link UseDefaultExecutableDetailsLink = fPrefUtils.createDetailsLink(parent, UseDefaultExecutable, UseDefaultExecutable.getChangeControl().getParent(), "Details ...");

		fields.add(UseDefaultExecutable);


		FileFieldEditor ExecutableToUse = fPrefUtils.makeNewFileField(
			page, this, fPrefService,
			"instance", "ExecutableToUse", "ExecutableToUse", "",
			parent,
			true, true,
			false, "Unspecified",
			true, "",
			true);
		Link ExecutableToUseDetailsLink = fPrefUtils.createDetailsLink(parent, ExecutableToUse, ExecutableToUse.getTextControl().getParent(), "Details ...");

		fields.add(ExecutableToUse);


		fPrefUtils.createToggleFieldListener(UseDefaultExecutable, ExecutableToUse, false);
		boolean isEnabledExecutableToUse = !UseDefaultExecutable.getBooleanValue();
		ExecutableToUse.getTextControl().setEditable(isEnabledExecutableToUse);
		ExecutableToUse.getTextControl().setEnabled(isEnabledExecutableToUse);
		ExecutableToUse.setEnabled(isEnabledExecutableToUse, ExecutableToUse.getParent());


		BooleanFieldEditor UseDefaultIncludePath = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "UseDefaultIncludePath", "UseDefaultIncludePath", "",
			parent,
			true, true,
			true, false,
			false, false,
			true);
		Link UseDefaultIncludePathDetailsLink = fPrefUtils.createDetailsLink(parent, UseDefaultIncludePath, UseDefaultIncludePath.getChangeControl().getParent(), "Details ...");

		fields.add(UseDefaultIncludePath);


		DirectoryListFieldEditor IncludePathToUse = fPrefUtils.makeNewDirectoryListField(
			page, this, fPrefService,
			"instance", "IncludePathToUse", "IncludePathToUse", "",
			parent,
			true, true,
			true, ".",
			true, "",
			true);
		Link IncludePathToUseDetailsLink = fPrefUtils.createDetailsLink(parent, IncludePathToUse, IncludePathToUse.getTextControl().getParent(), "Details ...");

		fields.add(IncludePathToUse);


		fPrefUtils.createToggleFieldListener(UseDefaultIncludePath, IncludePathToUse, false);
		boolean isEnabledIncludePathToUse = !UseDefaultIncludePath.getBooleanValue();
		IncludePathToUse.getTextControl().setEditable(isEnabledIncludePathToUse);
		IncludePathToUse.getTextControl().setEnabled(isEnabledIncludePathToUse);
		IncludePathToUse.setEnabled(isEnabledIncludePathToUse, IncludePathToUse.getParent());


		StringFieldEditor SourceFileExtensions = fPrefUtils.makeNewStringField(
			page, this, fPrefService,
			"instance", "SourceFileExtensions", "SourceFileExtensions", "",
			parent,
			true, true,
			true, "g,lpg,gra",
			true, "",
			true);
		Link SourceFileExtensionsDetailsLink = fPrefUtils.createDetailsLink(parent, SourceFileExtensions, SourceFileExtensions.getTextControl().getParent(), "Details ...");

		fields.add(SourceFileExtensions);


		StringFieldEditor IncludeFileExtensions = fPrefUtils.makeNewStringField(
			page, this, fPrefService,
			"instance", "IncludeFileExtensions", "IncludeFileExtensions", "",
			parent,
			true, true,
			false, "Unspecified",
			true, "",
			true);
		Link IncludeFileExtensionsDetailsLink = fPrefUtils.createDetailsLink(parent, IncludeFileExtensions, IncludeFileExtensions.getTextControl().getParent(), "Details ...");

		fields.add(IncludeFileExtensions);


		BooleanFieldEditor EmitDiagnostics = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "EmitDiagnostics", "EmitDiagnostics", "",
			parent,
			true, true,
			true, false,
			false, false,
			true);
		Link EmitDiagnosticsDetailsLink = fPrefUtils.createDetailsLink(parent, EmitDiagnostics, EmitDiagnostics.getChangeControl().getParent(), "Details ...");

		fields.add(EmitDiagnostics);


		BooleanFieldEditor GenerateListings = fPrefUtils.makeNewBooleanField(
			page, this, fPrefService,
			"instance", "GenerateListings", "GenerateListings", "",
			parent,
			true, true,
			true, false,
			false, false,
			true);
		Link GenerateListingsDetailsLink = fPrefUtils.createDetailsLink(parent, GenerateListings, GenerateListings.getChangeControl().getParent(), "Details ...");

		fields.add(GenerateListings);

		FieldEditor[] fieldsArray = new FieldEditor[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			fieldsArray[i] = (FieldEditor) fields.get(i);
		}
		return fieldsArray;
	}
}
