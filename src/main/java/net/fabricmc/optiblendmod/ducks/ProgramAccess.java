package net.fabricmc.optiblendmod.ducks;

import net.optifine.render.GlBlendState;

public interface ProgramAccess {
    public void setBlendSubState(int binding, GlBlendState glblendstate);
}
