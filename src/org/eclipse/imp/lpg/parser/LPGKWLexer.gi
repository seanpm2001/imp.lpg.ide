%Options fp=LPGKWLexer
%options single-productions
%options package=org.eclipse.imp.lpg.parser
%options template=KeywordTemplate.gi

%Include
    --
    -- Each upper case letter is mapped into is corresponding
    -- lower case counterpart. For example, if an 'A' appears
    -- in the input, it is mapped into Char_a just like 'a'.
    --
    KWLexerFoldedCaseMap.gi
%End

%Export
   ALIAS_KEY
   AST_KEY
   DEFINE_KEY
   DISJOINTPREDECESSORSETS_KEY
   DROPRULES_KEY
   DROPSYMBOLS_KEY
   EMPTY_KEY
   END_KEY
   ERROR_KEY
   EOL_KEY
   EOF_KEY 
   EXPORT_KEY
   GLOBALS_KEY
   HEADERS_KEY
   IDENTIFIER_KEY
   IMPORT_KEY
   INCLUDE_KEY
   KEYWORDS_KEY
   NAMES_KEY
   NOTICE_KEY
   OPTIONS_KEY
   RECOVER_KEY
   TERMINALS_KEY
   RULES_KEY
   START_KEY
   TRAILERS_KEY
   TYPES_KEY

%End

%Start
    Keyword
%End

%Notice
/.
// (C) Copyright IBM Corporation 2007
// 
// This file is part of the Eclipse IMP.
./
%End

%Rules
    Keyword ::= KeyPrefix a l i a s
        /.$BeginJava
            $setResult($_ALIAS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix a s t
        /.$BeginJava
            $setResult($_AST_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix d e f i n e
        /.$BeginJava
            $setResult($_DEFINE_KEY);
          $EndJava
        ./
     Keyword ::= KeyPrefix d i s j o i n t p r e d e c e s s o r s e t s
        /.$BeginJava
            $setResult($_DISJOINTPREDECESSORSETS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix d r o p r u l e s
        /.$BeginJava
            $setResult($_DROPRULES_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix d r o p s y m b o l s
        /.$BeginJava
            $setResult($_DROPSYMBOLS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix e m p t y
        /.$BeginJava
            $setResult($_EMPTY_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix e n d
        /.$BeginJava
            $setResult($_END_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix e r r o r
        /.$BeginJava
            $setResult($_ERROR_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix e o l
        /.$BeginJava
            $setResult($_EOL_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix e o f
        /.$BeginJava
            $setResult($_EOF_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix e x p o r t
        /.$BeginJava
            $setResult($_EXPORT_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix g l o b a l s
        /.$BeginJava
            $setResult($_GLOBALS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix h e a d e r s
        /.$BeginJava
            $setResult($_HEADERS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix i d e n t i f i e r
        /.$BeginJava
            $setResult($_IDENTIFIER_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix i m p o r t
        /.$BeginJava
            $setResult($_IMPORT_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix i n c l u d e
        /.$BeginJava
            $setResult($_INCLUDE_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix k e y w o r d s
        /.$BeginJava
            $setResult($_KEYWORDS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix n a m e s
        /.$BeginJava
            $setResult($_NAMES_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix n o t i c e
        /.$BeginJava
            $setResult($_NOTICE_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix t e r m i n a l s
        /.$BeginJava
            $setResult($_TERMINALS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix r e c o v e r
        /.$BeginJava
            $setResult($_RECOVER_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix r u l e s
        /.$BeginJava
            $setResult($_RULES_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix s t a r t 
        /.$BeginJava
            $setResult($_START_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix t r a i l e r s
        /.$BeginJava
            $setResult($_TRAILERS_KEY);
          $EndJava
        ./
    Keyword ::= KeyPrefix t y p e s
        /.$BeginJava
            $setResult($_TYPES_KEY);
          $EndJava
        ./
    KeyPrefix -> '$' | '%'
%End
