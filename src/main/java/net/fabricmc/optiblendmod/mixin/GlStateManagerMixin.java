package net.fabricmc.optiblendmod.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.optiblendmod.GlBlendSubState;
import net.fabricmc.optiblendmod.ducks.GlBlendStateAccess;
import net.optifine.render.GlBlendState;
import net.optifine.util.LockCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(GlStateManager.class)
public class GlStateManagerMixin {

    @Shadow(remap = false) private static LockCounter blendLock;

    @Inject(at = @At("HEAD"), method = "setBlendState", remap = false)
    private static void setBlendStateHead(CallbackInfo info) {

        if (!blendLock.isLocked())
        {
            //System.out.println("Needs apply NEW alpha blending...");
            for (int i=0;i<8;i++) {
                //GL40.glDisablei(GL11.GL_BLEND, i);
            }
        }

    }

    @Inject(at = @At("TAIL"), method = "setBlendState", remap = false)
    private static void setBlendStateTail(GlBlendState state, CallbackInfo info) {

        if (!blendLock.isLocked())
        {
            //System.out.println("Needs apply NEW alpha blending...");
            ArrayList<GlBlendSubState> prtStates = ((GlBlendStateAccess)state).getSubStates();
            if (prtStates != null && prtStates.size() > 0) {
                for (int i = 0; i < prtStates.size(); i++) {
                    if (prtStates.get(i) != null) {
                        prtStates.get(i).apply();
                    }
                }
            }
        }

    }

}
