package net.fabricmc.optimod;

import net.fabricmc.api.ModInitializer;
import net.optifine.shaders.Program;
import net.optifine.shaders.Shaders;

public class OptiMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        System.out.println("Hello Fabric world!");
        //Program[] ProgramsAll = Shaders.ProgramsAll;
    }
}
