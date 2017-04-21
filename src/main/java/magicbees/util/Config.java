package magicbees.util;

import elec332.core.config.Configurable;

/**
 * Created by Elec332 on 4-3-2017.
 */
public class Config {

    @Configurable
    public static boolean magnetSound = true;
    @Configurable
    public static int magnetMaxLevel = 9;
    @Configurable
    public static float magnetBaseRange = 3;
    @Configurable
    public static float magnetLevelMultiplier = 0.75f;

    @Configurable
    public static boolean moonDialShowsPhaseInText = true;

    @Configurable.Class(comment = "Here you can configure certain bees")
    public static class Bees {

        @Configurable
        public static boolean enableGemBees = true;

    }

}
