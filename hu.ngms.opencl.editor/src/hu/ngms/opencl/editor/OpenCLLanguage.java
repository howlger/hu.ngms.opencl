package hu.ngms.opencl.editor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.model.ICLanguageKeywords;

public class OpenCLLanguage extends GCCLanguage implements ICLanguageKeywords {

	public static Map<String, String> keywords = new HashMap<String, String>() {
		{
			put("__global", "");
			put("global", "");
			put("local", "");
			put("__local", "");
			put("constant", "");
			put("__constant", "");
			put("private", "");
			put("__private", "");
			put("kernel", "");
			put("__kernel", "");
			put("read_only", "");
			put("__read_only", "");
			put("write_only", "");
			put("__write_only", "");
			put("read_write", "");
			put("__read_write", "");
			put("restrict", "");
			put("__restrict", "");
		}
	};
	public static Map<String, String> types = new HashMap<String, String>() {
		{
			put("bool", "char");
			put("half", "float");
		}
	};

	@Override
	public Object getAdapter(Class adapter) {
		if (ICLanguageKeywords.class.equals(adapter)) {
			return this;
		}
		return super.getAdapter(adapter);
	}

	@Override
	public String[] getKeywords() {
		return Stream.concat(Arrays.stream(super.getKeywords()), keywords.keySet().stream()).toArray(String[]::new);
	}

	/**
	 * Get the built-in type names defined for this language.
	 *
	 * @return an array of names, never <code>null</code>
	 */
	@Override
	public String[] getBuiltinTypes() {
		return Stream.concat(Arrays.stream(super.getBuiltinTypes()), types.keySet().stream()).toArray(String[]::new);
	}

	/**
	 * Get the preprocessor keywords (directives) defined for this language.
	 *
	 * @return an array of keywords, never <code>null</code>
	 */
	@Override
	public String[] getPreprocessorKeywords() {
		return Stream.concat(Arrays.stream(super.getPreprocessorKeywords()), keywords.keySet().stream()).toArray(
				String[]::new);
	}

}
