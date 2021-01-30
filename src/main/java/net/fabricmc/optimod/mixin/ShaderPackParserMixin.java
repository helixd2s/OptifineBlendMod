package net.fabricmc.optimod.mixin;

import net.fabricmc.optimod.ducks.ProgramAccess;
import net.fabricmc.optimod.ducks.ShaderPackParserAccess;
import net.minecraft.client.render.WorldRenderer;
import net.optifine.Config;
import net.optifine.render.GlBlendState;
import net.optifine.shaders.Program;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ShaderPackParser;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static java.lang.Integer.parseInt;

@Mixin(ShaderPackParser.class)
public class ShaderPackParserMixin implements ShaderPackParserAccess {

    @Inject(at = @At("HEAD"), method = "parseBlendState(Ljava/lang/String;)Lnet/optifine/render/GlBlendState;", remap = false)
    private static void parseBlendState(String str, CallbackInfoReturnable info) {
        System.out.println("BLEND JUST PARSED!");
    }

    // TODO: place into loop
    @Inject(at = @At("TAIL"), method = "parseBlendStates(Ljava/util/Properties;)V", remap = false)
    private static void parseBlendStates(Properties props, CallbackInfo info)
    {
        for (String s : (Set<String>)(Set<?>)props.keySet())
        {
            String[] astring = Config.tokenize(s, ".");
            if (astring.length == 3)
            {
                String s1 = astring[0];
                String s2 = astring[1];
                String s3 = astring[2];

                if (s1.equals("blend"))
                {
                    Program program = Shaders.getProgram(s2);

                    if (program == null)
                    {
                        SMCLog.severe("Invalid program name: " + s2);
                    }
                    else
                    {
                        String s4 = props.getProperty(s).trim();
                        GlBlendState glblendstate = parseBlendState(s4);

                        if (glblendstate != null)
                        {
                            ((ProgramAccess)program).setBlendSubState(parseInt(s3), glblendstate);
                        }
                    }
                }
            }
        }
    }

    @Invoker(value = "parseBlendState", remap = false)
    private static GlBlendState parseBlendState(String s3) {
        throw new AssertionError();
    }


}
