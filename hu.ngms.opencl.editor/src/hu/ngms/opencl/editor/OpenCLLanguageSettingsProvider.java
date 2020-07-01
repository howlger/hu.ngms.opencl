package hu.ngms.opencl.editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.language.settings.providers.LanguageSettingsBaseProvider;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.model.IMacroEntry;
import org.eclipse.cdt.core.settings.model.ICConfigurationDescription;
import org.eclipse.cdt.core.settings.model.ICLanguageSettingEntry;
import org.eclipse.cdt.core.settings.model.util.CDataUtil;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;

public class OpenCLLanguageSettingsProvider extends LanguageSettingsBaseProvider {

	@Override
	public List<ICLanguageSettingEntry> getSettingEntries(ICConfigurationDescription cfgDescription, IResource rc,
			String languageId) {
		List<ICLanguageSettingEntry> entries = new ArrayList<ICLanguageSettingEntry>();
		for (Map.Entry<String, String> keyword : OpenCLLanguage.keywords.entrySet()) {
			IMacroEntry macroEntry = CoreModel.newMacroEntry(Path.EMPTY, keyword.getKey(), keyword.getValue());
			entries.add(CDataUtil.createCMacroEntry(macroEntry.getMacroName(), macroEntry.getMacroValue(), 0));
		}
		for (Map.Entry<String, String> type : OpenCLLanguage.types.entrySet()) {
			IMacroEntry macroEntry = CoreModel.newMacroEntry(Path.EMPTY, type.getKey(), type.getValue());
			entries.add(CDataUtil.createCMacroEntry(macroEntry.getMacroName(), macroEntry.getMacroValue(), 0));
		}
		return entries;
	}

}
