/*
 * Bootcamp 2 Solution
 * CPSC 5002, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
package bc2soln_debug_prob;

/**
 * This is a Java class to demonstrate debugging features.
 * @author bc2soln
 */
public class DebugDemo {
    /**
    * Test cases to run
    */
    private static final String[] testCases = {
        "xyz",					// expect xyz
//        "<b>foo</b>",			// expect foo
//        "\"<b>foo</b>\"",		// expect <b>foo<b> (since it's quoted)
//        "\'<b>foo</b>\'",		// expect <b>foo<b> (since it's quoted)
//        "\'<b>\"foo\"</b>\'",	// expect <b>"foo"<b> 
    };

    /**
     * Main entry point of the program
     * @param args not used
     */
    public static void main(String[] args) {
        for (String testCase : testCases) {
            System.out.print(testCase + " --> ");
            System.out.println(removeHtmlMarkup(testCase));
        }

    }


    /**
     * Strips the HTML tags from the strings and returns plain text result.
     * @see
     * <a href="https://www.ecma-international.org/ecma-262/5.1/#sec-7.8.4">
     *     StringLiteral Spec</a>
     * @param s HTML string to strip the tags from
     * @return Plain text string
     */
    private static String removeHtmlMarkup(String s) {
        boolean tag = false;
        boolean quote = false;
        String out = "";
        for (int i = 0; i <= s.length(); i++) {
            if (s.charAt(i) == '<' && !quote) {
                tag = true;
            } else if (s.charAt(i) == '>' && !quote) {
                tag = false;
            } else if (s.charAt(i) == '"' || s.charAt(i) == '\'' && tag) {
                quote = !quote;
            } else if (!tag) {
                out = out + s.charAt(i);
            }
        }
        return out;
    }
}
