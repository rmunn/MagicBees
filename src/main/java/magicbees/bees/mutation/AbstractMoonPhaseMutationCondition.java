package magicbees.bees.mutation;

import elec332.core.util.MoonPhase;
import elec332.core.util.StatCollector;
import forestry.api.climate.IClimateProvider;
import forestry.api.genetics.IAllele;
import forestry.api.genetics.IGenome;
import forestry.api.genetics.IMutationCondition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Elec332 on 23-8-2016.
 */
public abstract class AbstractMoonPhaseMutationCondition implements IMutationCondition {

    public AbstractMoonPhaseMutationCondition(MoonPhase moonPhaseStart, MoonPhase moonPhaseEnd){
        this.moonPhaseStart = moonPhaseStart;
        this.moonPhaseEnd = moonPhaseEnd;
        this.two = moonPhaseStart != moonPhaseEnd;
    }

    protected final MoonPhase moonPhaseStart, moonPhaseEnd;
    private final boolean two;

    protected boolean isBetweenPhases(World world){
        return MoonPhase.getMoonPhase(world).isBetween(this.moonPhaseStart, this.moonPhaseEnd);
    }

    @Override
    public abstract float getChance(World world, BlockPos blockPos, IAllele iAllele, IAllele iAllele1, IGenome iGenome, IGenome iGenome1, IClimateProvider iClimateProvider);

    @Override
    public String getDescription() {
        if (two) {
            return String.format(StatCollector.translateToLocal("research.bonusPhase"), moonPhaseStart.getLocalizedName(), moonPhaseEnd.getLocalizedName());
        } else {
            return String.format(StatCollector.translateToLocal("research.bonusPhaseSingle"), moonPhaseStart.getLocalizedName());
        }
    }

}
