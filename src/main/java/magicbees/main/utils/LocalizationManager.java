package magicbees.main.utils;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationManager
{
	public static String getLocalizedString(String key)
	{
		String result = LanguageRegistry.instance().getStringLocalization(key);
		if (result.isEmpty())
		{
			result = LanguageRegistry.instance().getStringLocalization(key, "en_US");
		}
		return result;
	}

	public static String getLocalizedString(String key, Object... objects)
	{
		String result = LanguageRegistry.instance().getStringLocalization(key);
		if (result.isEmpty())
		{
			result = LanguageRegistry.instance().getStringLocalization(key, "en_US");
		}
		return String.format(result, objects);
	}
}
