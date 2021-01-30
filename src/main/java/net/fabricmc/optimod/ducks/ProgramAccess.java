package net.fabricmc.optimod.ducks;

import net.optifine.render.GlBlendState;
import org.spongepowered.asm.mixin.Shadow;

public interface ProgramAccess {


    void setBlendSubState(int binding, GlBlendState glblendstate);
}
