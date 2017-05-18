package magicbees.integration.botania;

import elec332.core.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

/**
 * Created by Elec332 on 18-5-2017.
 */
public class BotaniaLexiconEntry extends LexiconEntry implements IAddonEntry {

	public BotaniaLexiconEntry(String unlocalizedName, LexiconCategory category) {
		super(unlocalizedName, category);
		BotaniaAPI.addEntry(this, category);
	}

	@Override
	public String getSubtitle() {
		return StatCollector.translateToLocal("magicbees.botania.lexicon.subtitle");
	}

	@Override
	public String getUnlocalizedName() {
		return unlocalizedName;
	}

}
