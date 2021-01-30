package net.fabricmc.optimod.mixin;

import net.fabricmc.optimod.GlBlendSubState;
import net.fabricmc.optimod.ducks.GlBlendStateAccess;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.config.ShaderPackParser;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(GlBlendState.class)
public class GlBlendStateMixin implements GlBlendStateAccess {
    @Shadow(remap = false)
    private boolean enabled;

    @Shadow(remap = false)
    private int srcFactor;

    @Shadow(remap = false)
    private int dstFactor;

    @Shadow(remap = false)
    private int srcFactorAlpha;

    @Shadow(remap = false)
    private int dstFactorAlpha;

    public int getSrcFactor() {
        return srcFactor;
    };

    public int getDstFactor() {
        return dstFactor;
    };

    public int getSrcFactorAlpha() {
        return srcFactorAlpha;
    };

    public int getDstFactorAlpha() {
        return dstFactorAlpha;
    };

    @Unique
    ArrayList<GlBlendSubState> prtStates;

    @Override public ArrayList<GlBlendSubState> getSubStates() {
        return prtStates;
    }

    @Override
    public void clearSubStates() {
        if (prtStates == null) { prtStates = new ArrayList<GlBlendSubState>(); };
        prtStates.clear();
    }

    @Override
    public void addSubState(GlBlendSubState state) {
        if (prtStates == null) { prtStates = new ArrayList<GlBlendSubState>(); };
        GlBlendSubState newState = new GlBlendSubState(state); // clone state for thread safe
        prtStates.add(newState);
    }

    @Override
    public void setSubState(int slot, GlBlendSubState state) {
        if (prtStates == null) { prtStates = new ArrayList<GlBlendSubState>(); };
        if (prtStates.size() <= slot) { prtStates.ensureCapacity(slot+1); };
        GlBlendSubState newState = new GlBlendSubState(state); // clone state for thread safe
        prtStates.set(slot, newState);
    }

    @Override
    public void setSubState(int slot, GlBlendState state) {
        if (prtStates == null) { prtStates = new ArrayList<GlBlendSubState>(); };
        if (prtStates.size() <= slot) { prtStates.ensureCapacity(slot+1); };
        GlBlendSubState newState = new GlBlendSubState(slot, state); // clone state for thread safe
        prtStates.set(slot, newState);
    }

    @Inject(at = @At("TAIL"), method = "setState(Lnet/optifine/render/GlBlendState;)V", remap = false)
    public void setState(GlBlendState state, CallbackInfo info) {
        this.prtStates.clear();
        ArrayList<GlBlendSubState> prtStates = ((GlBlendStateAccess)state).getSubStates();
        if (prtStates != null && prtStates.size() > 0) {
            for (int i = 0; i < prtStates.size(); i++) {
                if (prtStates.get(i) != null) {
                    this.setSubState(i, prtStates.get(i));
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "apply", remap = false)
    public void applyHead(CallbackInfo info) {
        System.out.println("Needs apply NEW alpha blending...");
        for (int i=0;i<8;i++) {
            GL40.glDisablei(GL11.GL_BLEND, i);
        }
    }

    @Inject(at = @At("TAIL"), method = "apply", remap = false)
    public void applyTail(CallbackInfo info) {
        System.out.println("Needs apply NEW alpha blending...");
        if (prtStates != null && prtStates.size() > 0) {
            for (int i = 0; i < prtStates.size(); i++) {
                if (prtStates.get(i) != null) {
                    prtStates.get(i).apply();
                }
            }
        }
    }
}
