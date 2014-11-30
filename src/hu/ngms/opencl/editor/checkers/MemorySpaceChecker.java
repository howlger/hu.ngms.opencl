package hu.ngms.opencl.editor.checkers;

import org.eclipse.cdt.codan.core.cxx.model.AbstractIndexAstChecker;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;

public class MemorySpaceChecker extends AbstractIndexAstChecker {

	@Override
	public void processAst(IASTTranslationUnit ast) {
		// TODO
		System.out.println(this.getClass() + ": " + ast.getContainingFilename() + "(" + ast.getRawSignature() + ")");
	}

}
