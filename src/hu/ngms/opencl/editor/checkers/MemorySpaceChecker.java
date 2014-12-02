package hu.ngms.opencl.editor.checkers;

import org.eclipse.cdt.codan.core.cxx.model.AbstractIndexAstChecker;
import org.eclipse.cdt.core.dom.ast.IASTCastExpression;
import org.eclipse.cdt.core.dom.ast.IASTDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTDeclarationStatement;
import org.eclipse.cdt.core.dom.ast.IASTExpressionStatement;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDefinition;
import org.eclipse.cdt.core.dom.ast.IASTIfStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IASTWhileStatement;

/**
 * Checks for memory space usage-related errors, like variable assignments of or pointer
 * casts to different target memory space.
 * 
 * @author sragli@ngms.hu
 *
 */
public class MemorySpaceChecker extends AbstractIndexAstChecker {

	/**
	 * Note that this is/must be the same checker-id as defined in plugin.xml
	 */
	public static final String CHECKER_ID = "hu.ngms.opencl.editor.checkers.memory_space";

	@Override
	public void processAst(IASTTranslationUnit ast) {
		IASTDeclaration firstDecl = ast.getDeclarations()[0];
		if (firstDecl instanceof IASTFunctionDefinition) {
			System.out.println(CHECKER_ID + ": " + firstDecl);
			IASTFunctionDefinition funcDecl = (IASTFunctionDefinition) firstDecl;
			check(funcDecl.getBody());
		}
	}

	private void check(IASTStatement body) {
		check(body.getChildren());
	}

	private void check(IASTNode[] children) {
		for (IASTNode node: children) {
			System.out.println("NODE: " + node);
			if (node instanceof IASTDeclarationStatement) {
				IASTDeclaration[] decls = node.getTranslationUnit().getDeclarations();
				for (IASTDeclaration decl : decls) {
					IASTDeclaration d = (IASTDeclaration) decl;
					System.out.println("DECL: " + decl);
					// TODO check if memory space of the declared variable is different from the memory space of the right value
					reportProblem("hu.ngms.opencl.editor.checkers.assignment_to_wrong_memory_space", d, d.getRawSignature());
				}
			} else if (node instanceof IASTIfStatement) {
				System.out.println("IF: " + node);
				check(((IASTIfStatement) node).getThenClause());
				if (((IASTIfStatement) node).getElseClause() != null) {
					System.out.println("ELSE: " + node);
					check(((IASTIfStatement) node).getElseClause());
				}
			} else if (node instanceof IASTWhileStatement || node instanceof IASTForStatement) {
				System.out.println("ITERATION: " + node);
				check(node.getChildren());
			} else if (node instanceof IASTExpressionStatement) {
				System.out.println("EXPR: " + node);
				check(node.getChildren());
			} else if (node instanceof IASTCastExpression) {
				// TODO check if memory space of the cast is different from the memory space of the right value
				System.out.println("CAST: " + node);
			}
		}
	}

}
