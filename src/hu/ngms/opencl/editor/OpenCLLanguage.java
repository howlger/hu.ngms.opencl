package hu.ngms.opencl.editor;

import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.model.ICLanguageKeywords;

public class OpenCLLanguage extends GCCLanguage implements ICLanguageKeywords {

	private String[] keywords = new String[] { "global", "local", "constant", "kernel", "private" };
	private String[] types = new String[] { "bool" };

	@Override
	public Object getAdapter(Class adapter) {
		if (ICLanguageKeywords.class.equals(adapter)) {
			return this;
		}
		return super.getAdapter(adapter);
	}

	@Override
	public String[] getKeywords() {
		return keywords;
	}

	/**
	 * Get the built-in type names defined for this language.
	 *
	 * @return an array of names, never <code>null</code>
	 */
	@Override
	public String[] getBuiltinTypes() {
		return types;
	}

	/**
	 * Get the preprocessor keywords (directives) defined for this language.
	 *
	 * @return an array of keywords, never <code>null</code>
	 */
	@Override
	public String[] getPreprocessorKeywords() {
		return new String[0];
	}

}
