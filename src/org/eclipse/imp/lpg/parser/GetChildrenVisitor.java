package org.eclipse.safari.jikespg.parser;

import org.eclipse.safari.jikespg.parser.JikesPGParser.*;

/**
 * A simple visitor that produces the immediate children of a given AST node.<br>
 */
public class GetChildrenVisitor extends AbstractVisitor implements Visitor {
    private final static Object[] NO_CHILDREN= new Object[0];

    Object[] fChildren= NO_CHILDREN;

    public void unimplementedVisitor(String s) { }

    public boolean visit(JikesPG n) {
	option_specList options= n.getoptions_segment();
        JikesPG_itemList itemList= n.getJikesPG_INPUT();
        int count= 1 + itemList.size();

        fChildren= new Object[count];
        fChildren[0]= options;
        for(int i= 0; i < itemList.size(); i++) {
            fChildren[i+1]= (ASTNode) itemList.getJikesPG_itemAt(i);
        }
        return false;
    }

    public boolean visit(GlobalsSeg n) {
        fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(DefineSeg n) {
        fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(HeadersSeg n) {
	fChildren= new Object[] { n.getheaders_segment() };
        return false;
    }

    public boolean visit(action_segment n) {
	fChildren= new Object[] { n.getBLOCK() };
        return false;
    }

    public boolean visit(action_segmentList n) {
	fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(alias_lhs_macro_name n) {
	fChildren= new Object[] { n.getMACRO_NAME() };
        return false;
    }

    public boolean visit(alias_rhs0 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(alias_rhs1 n) {
	fChildren= new Object[] { n.getMACRO_NAME() };
        return false;
    }

    public boolean visit(alias_rhs2 n) {
	fChildren= new Object[] { n.getERROR_KEY() };
        return false;
    }

    public boolean visit(alias_rhs3 n) {
	fChildren= new Object[] { n.getEOL_KEY() };
        return false;
    }

    public boolean visit(alias_rhs4 n) {
	fChildren= new Object[] { n.getEOF_KEY() };
        return false;
    }

    public boolean visit(alias_rhs5 n) {
	fChildren= new Object[] { n.getEMPTY_KEY() };
        return false;
    }

    public boolean visit(alias_rhs6 n) {
	fChildren= new Object[] { n.getIDENTIFIER_KEY() };
        return false;
    }

    public boolean visit(aliasSpec0 n) {
	fChildren= NO_CHILDREN;
        return false;
    }

    public boolean visit(aliasSpec1 n) {
	fChildren= new Object[] { n.getalias_rhs() };
        return false;
    }

    public boolean visit(aliasSpec2 n) {
	fChildren= new Object[] { n.getalias_rhs() };
        return false;
    }

    public boolean visit(aliasSpec3 n) {
	fChildren= new Object[] { n.getalias_rhs() };
        return false;
    }

    public boolean visit(aliasSpec4 n) {
	fChildren= new Object[] { n.getalias_rhs() };
        return false;
    }

    public boolean visit(aliasSpec5 n) {
	fChildren= new Object[] { n.getalias_rhs() };
        return false;
    }

    public boolean visit(AliasSeg n) {
	fChildren= new Object[] { n.getalias_segment() };
        return false;
    }

    public boolean visit(AstSeg n) {
	fChildren= new Object[] { n.getast_segment() };
        return false;
    }

    public boolean visit(defineSpec n) {
	fChildren= new Object[] { n.getmacro_name_symbol(), n.getmacro_segment() };
        return false;
    }

    public boolean visit(IdentifierSeg n) {
	fChildren= new Object[] { n.getidentifier_segment() };
        return false;
    }

    public boolean visit(ExportSeg n) {
	fChildren= new Object[] { n.getexport_segment() };
        return false;
    }

    public boolean visit(EofSeg n) {
	fChildren= new Object[] { n.geteof_segment() };
        return false;
    }

    public boolean visit(EolSeg n) {
	fChildren= new Object[] { n.geteol_segment() };
        return false;
    }

    public boolean visit(ErrorSeg n) {
	fChildren= new Object[] { n.geterror_segment() };
        return false;
    }

    public boolean visit(ImportSeg n) {
	fChildren= new Object[] { n.getimport_segment() };
        return false;
    }

    public boolean visit(IncludeSeg n) {
	fChildren= new Object[] { n.getinclude_segment() };
        return false;
    }

    public boolean visit(KeywordsSeg n) {
	fChildren= n.getkeywords_segment().getChildren().toArray();
        return false;
    }

    public boolean visit(keywordSpec n) {
	if (n.getname() != null)
	    fChildren= new Object[] { n.getterminal_symbol(), n.getname() };
	else
	    fChildren= new Object[] { n.getterminal_symbol() };
        return false;
    }

    public boolean visit(macro_segment n) {
	fChildren= new Object[] { n.getBLOCK() };
        return false;
    }

    public boolean visit(macro_name_symbol1 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(NamesSeg n) {
	fChildren= new Object[] { n.getnames_segment() };
        return false;
    }

    public boolean visit(nameSpecList n) {
	fChildren= n.getChildren().toArray();
	return false;
    }

    public boolean visit(nameSpec n) {
	fChildren= new Object[] { n.getname(), n.getname3() };
        return false;
    }

    public boolean visit(name0 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(name1 n) {
	fChildren= new Object[] { n.getMACRO_NAME() };
        return false;
    }

    public boolean visit(name2 n) {
	fChildren= new Object[] { n.getEMPTY_KEY() };
        return false;
    }

    public boolean visit(name3 n) {
	fChildren= new Object[] { n.getERROR_KEY() };
        return false;
    }

    public boolean visit(name4 n) {
	fChildren= new Object[] { n.getEOL_KEY() };
        return false;
    }

    public boolean visit(name5 n) {
	fChildren= new Object[] { n.getIDENTIFIER_KEY() };
        return false;
    }

    public boolean visit(nonTerm n) {
	fChildren= new Object[2 + (n.getarrayElement() != null ? 1 : 0) + (n.getclassName() != null ? 1 : 0)];
	int idx= 0;
	fChildren[idx++]= n.getSYMBOL();
	if (n.getclassName() != null)
	    fChildren[idx++]= n.getclassName();
	if (n.getarrayElement() != null)
	    fChildren[idx++]= n.getarrayElement();
	fChildren[idx++]= n.getrhsList();
        return false;
    }

    public boolean visit(nonTermList n) {
	fChildren= n.getArrayList().toArray();
        return false;
    }

    public boolean visit(option n) {
	fChildren= new Object[] { n.getSYMBOL(), n.getoption_value() };
        return false;
    }

    public boolean visit(optionList n) {
	fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(option_spec n) {
	fChildren= new Object[] { n.getoption_list() };
        return false;
    }

    public boolean visit(option_specList n) {
	fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(option_value1 n) {
	fChildren= new Object[] { n.getsymbol_list() };
        return false;
    }

    public boolean visit(option_value0 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(NoticeSeg n) {
	fChildren= new Object[] { n.getnotice_segment() };
        return false;
    }

    public boolean visit(rhs n) {
	fChildren= new Object[] { n.getsymWithAttrsList(), n.getopt_action_segment() };
        return false;
    }

    public boolean visit(rhsList n) {
	fChildren= n.getArrayList().toArray();
        return false;
    }

    public boolean visit(rules_segment n) {
	fChildren= new Object[] { n.getaction_segment_list(), n.getnonTermList() };
        return false;
    }

    public boolean visit(start_symbol1 n) {
	fChildren= new Object[] { n.getMACRO_NAME() };
        return false;
    }

    public boolean visit(StartSeg n) {
	fChildren= new Object[] { n.getstart_segment() };
        return false;
    }

    public boolean visit(SYMBOLList n) {
	fChildren= n.getArrayList().toArray();
        return false;
    }

    public boolean visit(symWithAttrsList n) {
	fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(symWithAttrs0 n) {
	fChildren= new Object[] { n.getEMPTY_KEY() };
        return false;
    }

    public boolean visit(symWithAttrs1 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(symWithAttrs2 n) {
	fChildren= new Object[] { n.getSYMBOL(), n.getMACRO_NAME() };
        return false;
    }

    public boolean visit(start_symbol0 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(terminal n) {
	fChildren= new Object[] { n.getterminal_symbol(), n.getoptTerminalAlias() };
        return false;
    }

    public boolean visit(terminal_symbol0 n) {
	fChildren= new Object[] { n.getSYMBOL() };
        return false;
    }

    public boolean visit(terminal_symbol1 n) {
	fChildren= new Object[] { n.getMACRO_NAME() };
        return false;
    }

    public boolean visit(terminalList n) {
	fChildren= n.getArrayList().toArray();
        return false;
    }

    public boolean visit(TypesSeg n) {
	fChildren= new Object[] { n.gettypes_segment() };
        return false;
    }

    public boolean visit(type_declarations n) {
	fChildren= new Object[] { n.getSYMBOL(), n.getbarSymbolList() };
        return false;
    }

    public boolean visit(type_declarationsList n) {
	fChildren= n.getChildren().toArray();
        return false;
    }

    public boolean visit(TrailersSeg n) {
	fChildren= new Object[] { n.gettrailers_segment() };
        return false;
    }

    public boolean visit(TerminalsSeg n) {
        terminalList termList= (terminalList) n.getterminals_segment();
        int N= termList.size();

        fChildren= new Object[N];
        for(int i=0; i < N; i++) {
            terminal term= (terminal) termList.getElementAt(i);
            fChildren[N-i-1]= term;
        }
        return false;
    }

    public boolean visit(RulesSeg n) {
        rules_segment rulesSegment= n.getrules_segment();
        nonTermList nonTermList= rulesSegment.getnonTermList();
        int N= nonTermList.size();

        fChildren= new Object[N];
        for(int i=0; i < N; i++) {
            nonTerm nt= (nonTerm) nonTermList.getElementAt(i);
            fChildren[N-i-1]= nt;
        }
        return false;
    }

    public Object[] getChildren() {
        return fChildren;
    }
}
