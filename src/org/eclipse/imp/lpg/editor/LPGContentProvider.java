package org.eclipse.safari.jikespg.editor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.uide.core.ILanguageService;
import org.eclipse.uide.editor.OutlineInformationControl;
import org.eclipse.uide.editor.OutlineInformationControl.OutlineContentProviderBase;
import org.eclipse.safari.jikespg.parser.JikesPGParser;
import org.eclipse.safari.jikespg.parser.JikesPGParser.ASTNode;
import org.eclipse.safari.jikespg.parser.JikesPGParser.ASTNodeToken;
import org.eclipse.safari.jikespg.parser.JikesPGParser.AbstractVisitor;
import org.eclipse.safari.jikespg.parser.JikesPGParser.JikesPG_itemList;
import org.eclipse.safari.jikespg.parser.JikesPGParser.NoticeSeg;
import org.eclipse.safari.jikespg.parser.JikesPGParser.action_segment;
import org.eclipse.safari.jikespg.parser.JikesPGParser.action_segmentList;
import org.eclipse.safari.jikespg.parser.JikesPGParser.include_segment;
import org.eclipse.safari.jikespg.parser.JikesPGParser.optTerminalAlias;
import org.eclipse.safari.jikespg.parser.JikesPGParser.optionList;
import org.eclipse.safari.jikespg.parser.JikesPGParser.option_spec;
import org.eclipse.safari.jikespg.parser.JikesPGParser.option_value0;
import org.eclipse.safari.jikespg.parser.JikesPGParser.rhsList;
import org.eclipse.safari.jikespg.parser.JikesPGParser.terminal_symbol0;

public class JikesPGContentProvider extends OutlineContentProviderBase implements ILanguageService {
    public JikesPGContentProvider() {
	this(false, null);
    }

    /**
     * Creates a new <code>JikesPGContentProvider</code>.
     *
     * @param provideMembers if <code>true</code> members below compilation units 
     * and class files are provided. 
     */
    public JikesPGContentProvider(boolean provideMembers, OutlineInformationControl oic) {
	super(oic, true);
    }

    /* (non-Javadoc)
     * Method declared on ITreeContentProvider.
     */
    public Object[] getChildren(Object element) {
	ASTNode node= (ASTNode) element;
	final List children= node.getChildren();
	final List collapsingAdditions= new ArrayList();

	if (node instanceof option_spec)
	    return null;
	for(Iterator iter= children.iterator(); iter.hasNext(); ) {
	    ASTNode child= (ASTNode) iter.next();

	    // Filter out certain "useless" AST node types from the child list.
	    // This prunes out entities and all their sub-children from the outline.
	    if ((child instanceof ASTNodeToken && !(child instanceof terminal_symbol0) && !(child instanceof action_segment) && !(child instanceof include_segment)) ||
		child instanceof action_segmentList ||
		child instanceof option_value0 || child instanceof optTerminalAlias || child instanceof option_spec ||
		child instanceof rhsList || child instanceof JikesPG_itemList || child instanceof optionList)
		iter.remove();
	    if (child instanceof JikesPG_itemList || child instanceof option_spec || child instanceof action_segmentList ||
	        child instanceof optionList) { // collapse this entity by replacing it with its children
		collapsingAdditions.addAll(child.getChildren());
	    }
	}
	children.addAll(collapsingAdditions);
	return children.toArray();
    }

    /* (non-Javadoc)
     * Method declared on ITreeContentProvider.
     */
    public Object getParent(Object element) {
	ASTNode node= (ASTNode) element;

	return node.getParent();
    }
}
