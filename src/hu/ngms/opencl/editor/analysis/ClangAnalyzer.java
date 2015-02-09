package hu.ngms.opencl.editor.analysis;

import org.eclipse.debug.ui.console.IConsole;
import org.eclipse.debug.ui.console.IConsoleLineTracker;
import org.eclipse.jface.text.IRegion;

public class ClangAnalyzer implements IConsoleLineTracker {

	@Override
	public void init(IConsole console) {
		// TODO Call clang++ -analyze
		
	}

	@Override
	public void lineAppended(IRegion line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
