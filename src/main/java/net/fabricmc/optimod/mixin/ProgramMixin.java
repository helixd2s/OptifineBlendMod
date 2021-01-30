package net.fabricmc.optimod.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.optimod.ducks.GlBlendStateAccess;
import net.fabricmc.optimod.ducks.ProgramAccess;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.Program;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Program.class)
public class ProgramMixin implements ProgramAccess {
    @Shadow(remap = false)
    private GlBlendState blendState;

    public void setBlendSubState(int binding, GlBlendState glblendstate) {
        ((GlBlendStateAccess)blendState).setSubState(binding, glblendstate);
    }
}
