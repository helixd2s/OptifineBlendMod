package net.fabricmc.optiblendmod.setup;

import org.spongepowered.asm.mixin.Mixins;

public class OptiBlendSetup implements Runnable {
    @Override
    public void run() {
        Mixins.addConfiguration("optiblend.mixins.json");
    }
}
