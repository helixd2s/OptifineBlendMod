package net.fabricmc.optiblendmod.mixin;

import net.fabricmc.optiblendmod.ducks.GlBlendStateAccess;
import net.fabricmc.optiblendmod.ducks.ProgramAccess;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.Program;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Program.class)
public class ProgramMixin implements ProgramAccess {
    @Shadow(remap = false)
    private GlBlendState blendState;

    public void setBlendSubState(int binding, GlBlendState glblendstate) {
        if (blendState != null) {
            ((GlBlendStateAccess) blendState).setSubState(binding, glblendstate);
        }
    }
}
