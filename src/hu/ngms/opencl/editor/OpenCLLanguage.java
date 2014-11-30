package hu.ngms.opencl.editor;

import java.util.Arrays;
import java.util.stream.Stream;

import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.model.ICLanguageKeywords;

public class OpenCLLanguage extends GCCLanguage implements ICLanguageKeywords {

	private String[] keywords = new String[] {
			"__global", "global", "__local", "local", "__constant", "constant", "__private", "private",
			"__kernel", "kernel", "__read_only", "read_only", "__write_only", "write_only", "__read_write", "read_write" };
	private String[] types = new String[] { "bool", "half" };

	@Override
	public Object getAdapter(Class adapter) {
		if (ICLanguageKeywords.class.equals(adapter)) {
			return this;
		}
		return super.getAdapter(adapter);
	}

	@Override
	public String[] getKeywords() {
		return Stream.concat(Arrays.stream(super.getKeywords()), Arrays.stream(keywords)).toArray(String[]::new);
	}

	/**
	 * Get the built-in type names defined for this language.
	 *
	 * @return an array of names, never <code>null</code>
	 */
	@Override
	public String[] getBuiltinTypes() {
		return Stream.concat(Arrays.stream(super.getBuiltinTypes()), Arrays.stream(types)).toArray(String[]::new);
	}

	/**
	 * Get the preprocessor keywords (directives) defined for this language.
	 *
	 * @return an array of keywords, never <code>null</code>
	 */
	@Override
	public String[] getPreprocessorKeywords() {
		return super.getPreprocessorKeywords();
	}

}
