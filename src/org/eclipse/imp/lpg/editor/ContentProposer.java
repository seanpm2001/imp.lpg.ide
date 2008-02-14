/*
 * (C) Copyright IBM Corporation 2007
 * 
 * This file is part of the Eclipse IMP.
 */
/*
 * Created on Nov 1, 2005
 */
package org.eclipse.imp.lpg.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import lpg.runtime.IToken;

import org.eclipse.imp.editor.SourceProposal;
import org.eclipse.imp.lpg.parser.ASTUtils;
import org.eclipse.imp.lpg.parser.LPGParser.ASTNode;
import org.eclipse.imp.lpg.parser.LPGParser.Imacro_name_symbol;
import org.eclipse.imp.lpg.parser.LPGParser.JikesPG;
import org.eclipse.imp.lpg.parser.LPGParser.nonTerm;
import org.eclipse.imp.lpg.parser.LPGParser.option;
import org.eclipse.imp.lpg.parser.LPGParser.terminal;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.services.IContentProposer;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.contentassist.ICompletionProposal;

public class ContentProposer implements IContentProposer {

    public ICompletionProposal[] getContentProposals(IParseController controller, final int offset, ITextViewer textViewer) {
	JikesPG root= (JikesPG) controller.getCurrentAst();

        if (root == null)
            return new ICompletionProposal[0];

        ISourcePositionLocator locator= controller.getNodeLocator();
	ASTNode thisNode= (ASTNode) locator.findNode(root, offset);
        IToken thisLeftToken= thisNode.getLeftIToken();
        final String prefixToken= (offset >= thisLeftToken.getStartOffset() && offset < thisLeftToken.getEndOffset()) ? thisLeftToken.toString() : null;
        final String prefix= (prefixToken != null) ? prefixToken.substring(0, offset - thisLeftToken.getStartOffset()) : "";

        final List<ICompletionProposal> proposals= new ArrayList<ICompletionProposal>();

        if (thisNode.getParent() instanceof option) {
	    option opt= (option) thisNode.getParent();

	    if (thisNode == opt.getSYMBOL()) {
		proposals.addAll(computeOptionKeyProposals(prefix, offset));
	    }
        } else if (prefix.startsWith("%")) {
            proposals.addAll(computeSegmentCompletions(prefix, offset, root));
        } else if (prefix.startsWith("$")) {
            proposals.addAll(computeMacroCompletions(prefix, offset, root));
        } else {
            proposals.addAll(computeNonTerminalCompletions(prefix, offset, root));
            proposals.addAll(computeTerminalCompletions(prefix, offset, root));
        }
        return proposals.toArray(new ICompletionProposal[proposals.size()]);
    }

    private final static String[] SEGMENT_KEYS= {
        "define", "export", "globals", "headers", "identifiers", "include", "import",
        "keywords", "notice", "recover", "rules", "start", "terminals", "types"
    };

    private Collection<? extends ICompletionProposal> computeSegmentCompletions(String prefix, int offset, JikesPG root) {
        Collection<SourceProposal> result= new ArrayList<SourceProposal>();
        for(int i= 0; i < SEGMENT_KEYS.length; i++) {
            String key= SEGMENT_KEYS[i];
            if (prefix.length() == 1 || key.startsWith(prefix.substring(1).toLowerCase())) {
                String addlInfo= "%" + key + "\n" + "%End";
                result.add(new SourceProposal(key, addlInfo, prefix, new Region(offset, 0), addlInfo));
            }
        }
        return result;
    }

    private final static String[] OPTION_KEYS= {
	"action", "ast_directory", "ast_type", "attributes",
	"automatic_ast", "backtrack", "byte", "conflicts",
	"dat-directory", "dat-file", "dcl-file", "debug",
	"def-file", "edit", "error-maps", "escape", 
	"extends-parsetable", "export-terminals", "factory", "file-prefix",
	"filter", "first", "follow", "goto-default",
	"grm-file", "imp-file", "import-terminals", "include-directory",
	"lalr-level", "list", "margin", "max_cases",
	"names", "nt-check", "or-marker", "out_directory",
	"package", "parent_saved", "parsetable-interfaces", "prefix",
	"priority", "programming_language", "prs-file", "quiet",
	"read-reduce", "remap-terminals", "scopes", "serialize",
	"shift-default", "single-productions", "slr", "soft-keywords",
	"states", "suffix", "sym-file", "tab-file",
	"table", "template", "trace", "variables",
	"verbose", "visitor", "visitor-type", "warnings",
    	"xref"
    };

    private Collection<SourceProposal> computeOptionKeyProposals(String prefix, int offset) {
	Collection<SourceProposal> result= new ArrayList<SourceProposal>();
	for(int i= 0; i < OPTION_KEYS.length; i++) {
	    String key= OPTION_KEYS[i];
	    if (key.startsWith(prefix)) {
		result.add(new SourceProposal(key, key, prefix, offset));
            }
	}
	return result;
    }

    private List<ICompletionProposal> computeMacroCompletions(String prefix, int offset, JikesPG root) {
        List<ICompletionProposal> result= new ArrayList<ICompletionProposal>();
        List<Imacro_name_symbol> macros= ASTUtils.getMacros(root);

        for(Iterator iter= macros.iterator(); iter.hasNext(); ) {
            Imacro_name_symbol macro= (Imacro_name_symbol) iter.next();
            String macroName= macro.toString();

            if (macroName.startsWith(prefix)) {
                result.add(new SourceProposal(macroName, prefix, offset));
            }
        }
        return result;
    }

    private List<ICompletionProposal> computeNonTerminalCompletions(final String prefix, final int offset, JikesPG root) {
        List<ICompletionProposal> result= new ArrayList<ICompletionProposal>();
        List<nonTerm> nonTerms= ASTUtils.getNonTerminals(root);

        for(Iterator iter= nonTerms.iterator(); iter.hasNext(); ) {
            nonTerm nt= (nonTerm) iter.next();
            String ntRawName= nt.getruleNameWithAttributes().getSYMBOL().toString();
            int idx= ntRawName.indexOf('$');
            final String ntName= (idx >= 0) ? ntRawName.substring(0, idx) : ntRawName;

            if (ntName.startsWith(prefix)) {
                String addlInfo= nt.toString();
                result.add(new SourceProposal(ntName, ntName, prefix, new Region(offset, 0), addlInfo));
            }
        }
        return result;
    }

    private List<ICompletionProposal> computeTerminalCompletions(final String prefix, final int offset, JikesPG root) {
        List<ICompletionProposal> result= new ArrayList<ICompletionProposal>();
        List<terminal> terms= ASTUtils.getTerminals(root);

        for(Iterator iter= terms.iterator(); iter.hasNext(); ) {
            terminal t= (terminal) iter.next();
            String termRawName= t.getterminal_symbol().toString();
            int idx= termRawName.indexOf('$');
            final String termName= (idx >= 0) ? termRawName.substring(0, idx) : termRawName;

            if (termName.startsWith(prefix)) {
                result.add(new SourceProposal(termName, prefix, offset));
            }
        }
        return result;
    }
}
