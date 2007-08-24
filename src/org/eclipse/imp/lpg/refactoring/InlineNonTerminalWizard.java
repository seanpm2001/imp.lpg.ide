/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
package org.eclipse.imp.lpg.refactoring;

import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

public class InlineNonTerminalWizard extends RefactoringWizard {
    public InlineNonTerminalWizard(InlineNonTerminalRefactoring refactoring, String pageTitle) {
	super(refactoring, DIALOG_BASED_USER_INTERFACE | PREVIEW_EXPAND_FIRST_NODE);
	setDefaultPageTitle(pageTitle);
    }

    protected void addUserInputPages() {
	InlineNonTerminalInputPage page= new InlineNonTerminalInputPage("Inline Non-Terminal");

	addPage(page);
    }

    public InlineNonTerminalRefactoring getInlineNonTerminalRefactoring() {
	return (InlineNonTerminalRefactoring) getRefactoring();
    }
}
