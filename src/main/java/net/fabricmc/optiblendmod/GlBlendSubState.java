package net.fabricmc.optiblendmod;

import net.fabricmc.optiblendmod.ducks.GlBlendStateAccess;
import net.optifine.render.GlBlendState;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL40;

public class GlBlendSubState {
    private int binding = 0;
    private boolean enabled = false;
    private int srcFactor = 0;
    private int dstFactor = 0;
    private int srcFactorAlpha = 0;
    private int dstFactorAlpha = 0;

    public GlBlendSubState() {
        this(0, false, 1, 0);
    }

    public GlBlendSubState(int binding) {
        this(binding, false, 1, 0);
    }

    public GlBlendSubState(int binding, boolean enabled) {
        this(binding, enabled, 1, 0);
    }

    public GlBlendSubState(int binding, boolean enabled, int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        this.binding = binding;
        this.enabled = enabled;
        this.srcFactor = srcFactor;
        this.dstFactor = dstFactor;
        this.srcFactorAlpha = srcFactorAlpha;
        this.dstFactorAlpha = dstFactorAlpha;
    }

    public GlBlendSubState(int binding, boolean enabled, int srcFactor, int dstFactor) {
        this(binding, enabled, srcFactor, dstFactor, srcFactor, dstFactor);
    }

    public GlBlendSubState(GlBlendSubState state) {
        this.binding = state.binding;
        this.enabled = state.enabled;
        this.srcFactor = state.srcFactor;
        this.dstFactor = state.dstFactor;
        this.srcFactorAlpha = state.srcFactorAlpha;
        this.dstFactorAlpha = state.dstFactorAlpha;
    }

    public GlBlendSubState(int binding, GlBlendState state) {
        this.binding = binding;
        this.enabled = state.isEnabled();
        this.srcFactor = ((GlBlendStateAccess)state).getSrcFactor();
        this.dstFactor = ((GlBlendStateAccess)state).getDstFactor();
        this.srcFactorAlpha = ((GlBlendStateAccess)state).getSrcFactorAlpha();
        this.dstFactorAlpha = ((GlBlendStateAccess)state).getDstFactorAlpha();
    }

    public void setState(int binding, boolean enabled, int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        this.binding = binding;
        this.enabled = enabled;
        this.srcFactor = srcFactor;
        this.dstFactor = dstFactor;
        this.srcFactorAlpha = srcFactorAlpha;
        this.dstFactorAlpha = dstFactorAlpha;
    }

    public void setState(int binding, GlBlendState state) {
        this.binding = binding;
        this.enabled = state.isEnabled();
        this.srcFactor = ((GlBlendStateAccess)state).getSrcFactor();
        this.dstFactor = ((GlBlendStateAccess)state).getDstFactor();
        this.srcFactorAlpha = ((GlBlendStateAccess)state).getSrcFactorAlpha();
        this.dstFactorAlpha = ((GlBlendStateAccess)state).getDstFactorAlpha();
    }

    public void setState(GlBlendSubState state) throws IllegalAccessException {
        this.binding = state.binding;
        this.enabled = state.enabled;
        this.srcFactor = state.srcFactor;
        this.dstFactor = state.dstFactor;
        this.srcFactorAlpha = state.srcFactorAlpha;
        this.dstFactorAlpha = state.dstFactorAlpha;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEnabled() {
        this.enabled = true;
    }

    public void setDisabled() {
        this.enabled = false;
    }

    public void setFactors(int srcFactor, int dstFactor) {
        this.srcFactor = srcFactor;
        this.dstFactor = dstFactor;
        this.srcFactorAlpha = srcFactor;
        this.dstFactorAlpha = dstFactor;
    }

    public void setFactors(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        this.srcFactor = srcFactor;
        this.dstFactor = dstFactor;
        this.srcFactorAlpha = srcFactorAlpha;
        this.dstFactorAlpha = dstFactorAlpha;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public int getSrcFactor() {
        return this.srcFactor;
    }

    public int getDstFactor() {
        return this.dstFactor;
    }

    public int getSrcFactorAlpha() {
        return this.srcFactorAlpha;
    }

    public int getDstFactorAlpha() {
        return this.dstFactorAlpha;
    }

    public boolean isSeparate() {
        return this.srcFactor != this.srcFactorAlpha || this.dstFactor != this.dstFactorAlpha;
    }

    public String toString() {
        return "binding: " + binding + ", enabled: " + this.enabled + ", src: " + this.srcFactor + ", dst: " + this.dstFactor + ", srcAlpha: " + this.srcFactorAlpha + ", dstAlpha: " + this.dstFactorAlpha;
    }

    public void apply() {
        if (!this.isEnabled()) {
            GL40.glDisablei(GL11.GL_BLEND, binding);
        } else {
            GL40.glEnablei(GL11.GL_BLEND, binding);
            GL40.glBlendFuncSeparatei(binding, srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
        }
    }

}
