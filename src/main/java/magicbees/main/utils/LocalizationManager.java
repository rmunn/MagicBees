package magicbees.main.utils;

import net.minecraft.util.text.translation.I18n;

public class LocalizationManager
{
	public static String getLocalizedString(String key)
	{
		if(I18n.canTranslate(key))
		{
			return I18n.translateToLocal(key);
		}
		else
		{
			return I18n.translateToFallback(key);
		}
	}

	public static String getLocalizedString(String key, Object... objects)
	{
		if(I18n.canTranslate(key))
		{
			return String.format(I18n.translateToLocal(key), objects);
		}
		else
		{
			return String.format(I18n.translateToFallback(key), objects);
		}
	}
}
