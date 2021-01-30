package net.fabricmc.optiblendmod.ducks;

import net.fabricmc.optiblendmod.GlBlendSubState;
import net.optifine.render.GlBlendState;

import java.util.ArrayList;

public interface GlBlendStateAccess {
    public void clearSubStates();
    public void addSubState(GlBlendSubState state);
    public void setSubState(int slot, GlBlendSubState state);
    public void setSubState(int slot, GlBlendState state);
    public ArrayList<GlBlendSubState> getSubStates();

    public int getSrcFactor();
    public int getDstFactor();
    public int getSrcFactorAlpha();
    public int getDstFactorAlpha();
}
