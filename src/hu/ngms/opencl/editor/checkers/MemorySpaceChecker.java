package hu.ngms.opencl.editor.checkers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.cdt.codan.core.cxx.model.AbstractIndexAstChecker;
import org.eclipse.cdt.core.dom.ast.IASTCompoundStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTDoStatement;
import org.eclipse.cdt.core.dom.ast.IASTExpressionStatement;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTIfStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTSimpleDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IASTWhileStatement;
import org.eclipse.cdt.core.parser.IToken;

/**
 * Checks for memory space usage-related errors.
 * 
 * @author sragli@ngms.hu
 *
 */
public class MemorySpaceChecker extends AbstractIndexAstChecker {
	
	private Map<String, String> declarations;
	private int level;

	/**
	 * Note that this is/must be the same checker-id as defined in plugin.xml
	 */
	public static final String CHECKER_ID = "hu.ngms.opencl.editor.checkers.memory_space";

	@Override
	public void processAst(IASTTranslationUnit ast) {
		declarations = new ConcurrentHashMap<String, String>();

		for (IASTDeclaration decl : ast.getDeclarations()) {
			System.out.println("NODE: " + decl.getOriginalNode().toString());

			if (decl instanceof IASTFunctionDefinition) {
				IASTFunctionDefinition funcDecl = (IASTFunctionDefinition) decl;
				checkFunctions(funcDecl.getBody());
			} else if (decl instanceof IASTSimpleDeclaration) {
				try {
					checkDefinition(decl.getOriginalNode());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void checkDefinition(IASTNode node) throws Exception {
		IToken token = node.getLeadingSyntax();
		boolean skipped = false;
		while (token != null) {
			String kw = token.toString();
			if (kw.endsWith("global") || kw.equals("NULL")) {
				skipped = true;
			}
			token = token.getNext();
		}
		if (!skipped) {
			reportProblem("hu.ngms.opencl.editor.checkers.global_declaration_error", node);
		}
	}

	private void checkFunctions(IASTNode... children) {
		for (IASTNode node: children) {
			for (int i = 0; i < level; i++) {
				System.out.print("  ");
			}
			level++;
			if (node instanceof IASTDeclarationStatement) {
				System.out.println("DECL: " + node.toString());
			} else if (node instanceof IASTIfStatement) {
				System.out.println("IF: " + node.toString());
			} else if (node instanceof IASTWhileStatement || node instanceof IASTForStatement || node instanceof IASTDoStatement) {
				System.out.println("ITERATION: " + node.toString());
			} else if (node instanceof IASTCompoundStatement) {
				System.out.println("COMPOUND STMT: " + node.toString());
			} else if (node instanceof IASTExpressionStatement) {
				System.out.println("EXPR: " + node.toString());
			} else if (node instanceof IASTDeclarator) {
				if (!declarations.containsKey(((IASTDeclarator) node).getName())) {
				}
				reportProblem("hu.ngms.opencl.editor.checkers.assignment_to_wrong_memory_space", node);
			}
			checkFunctions(node.getChildren());
		}
		level--;
	}

}
