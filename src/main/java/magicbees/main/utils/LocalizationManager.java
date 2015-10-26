package magicbees.main.utils;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationManager
{
	public static String getLocalizedString(String key)
	{
		if(StatCollector.canTranslate(key))
		{
			return StatCollector.translateToLocal(key);
		}
		else
		{
			return StatCollector.translateToFallback(key);
		}
	}

	public static String getLocalizedString(String key, Object... objects)
	{
		if(StatCollector.canTranslate(key))
		{
			return String.format(StatCollector.translateToLocal(key), objects);
		}
		else
		{
			return String.format(StatCollector.translateToFallback(key), objects);
		}
	}
}
