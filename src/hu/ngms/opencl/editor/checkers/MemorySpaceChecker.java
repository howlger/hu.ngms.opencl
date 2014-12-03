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
	
	private Map<String, String> declarations;
	private int level;

	/**
	 * Note that this is/must be the same checker-id as defined in plugin.xml
	 */
	public static final String CHECKER_ID = "hu.ngms.opencl.editor.checkers.memory_space";

	@Override
	public void processAst(IASTTranslationUnit ast) {
		declarations = new ConcurrentHashMap<String, String>();
		
		IASTDeclaration firstDecl = ast.getDeclarations()[0];
		if (firstDecl instanceof IASTFunctionDefinition) {
			IASTFunctionDefinition funcDecl = (IASTFunctionDefinition) firstDecl;
			check(funcDecl.getBody());
		}
	}

	private void check(IASTStatement body) {
		check(body.getChildren());
	}

	// TODO check if memory space of the declared variable is different from the memory space of the right value
	// TODO check if memory space of the cast is different from the memory space of the right value
	private void check(IASTNode[] children) {
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
				reportProblem("hu.ngms.opencl.editor.checkers.assignment_to_wrong_memory_space", node, node.toString());
			} else {
				System.out.println("NODE: " + node.toString());
			}
			check(node.getChildren());
		}
		level--;
	}

}
