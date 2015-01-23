package hu.ngms.opencl.editor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import org.eclipse.cdt.core.dom.ast.gnu.c.GCCLanguage;
import org.eclipse.cdt.core.language.settings.providers.ILanguageSettingsProvider;
import org.eclipse.cdt.core.model.ICLanguageKeywords;
import org.eclipse.cdt.core.settings.model.ICConfigurationDescription;
import org.eclipse.cdt.core.settings.model.ICLanguageSettingEntry;
import org.eclipse.cdt.core.settings.model.util.CSettingEntryFactory;
import org.eclipse.core.resources.IResource;

public class OpenCLLanguage extends GCCLanguage implements ICLanguageKeywords, ILanguageSettingsProvider {

	private String[] keywords = new String[] {
			"__global", "global", "__local", "local", "__constant", "constant", "__private", "private",
			"__kernel", "kernel", "__read_only", "read_only", "__write_only", "write_only", "__read_write", "read_write" };
	private String[] types = new String[] { "bool", "half" };
	
	private List<ICLanguageSettingEntry> settingEntries = new CopyOnWriteArrayList<ICLanguageSettingEntry>();

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

	@Override
	public List<ICLanguageSettingEntry> getSettingEntries(ICConfigurationDescription cfgDescription, IResource rc,
			String languageId) {
		if (settingEntries.isEmpty()) {
			// fill cache
			for (String keyword : keywords) {
			}
			for (String type : types) {
				
			}
		}
		return settingEntries;
	}

}
