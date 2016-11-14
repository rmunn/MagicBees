package magicbees.util;

import elec332.core.compat.forestry.IIndividualBranch;
import elec332.core.compat.forestry.bee.BeeGenomeTemplate;

import java.awt.*;

/**
 * Created by Elec332 on 19-8-2016.
 */
public interface IMoreBeesBranch extends IIndividualBranch<BeeGenomeTemplate> {

    public boolean enabled();

    public Color getSecondaryColor();

}
