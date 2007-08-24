/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
package org.eclipse.imp.lpg.refactoring;

import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

public class MakeLeftRecursiveWizard extends RefactoringWizard {
    public MakeLeftRecursiveWizard(MakeLeftRecursiveRefactoring refactoring, String pageTitle) {
	super(refactoring, DIALOG_BASED_USER_INTERFACE | PREVIEW_EXPAND_FIRST_NODE);
	setDefaultPageTitle(pageTitle);
    }

    protected void addUserInputPages() {
	MakeLeftRecursiveInputPage page= new MakeLeftRecursiveInputPage("Make Left Recursive");

	addPage(page);
    }

    public MakeLeftRecursiveRefactoring getMakeLeftRecursiveRefactoring() {
	return (MakeLeftRecursiveRefactoring) getRefactoring();
    }
}
