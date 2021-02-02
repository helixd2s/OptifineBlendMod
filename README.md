# Optifine blend modes config mini-mod (Fabric)

## What is it?

This is mod, that works through Optifabric and mixins with Optifine. This is Fabric mod. NOT Forge. Currently here is only source code. In any time code may to be updated, and `.jar` will outdated. 

### `shaders.properties`

I added those really working options.

```
# required for working (TODO to fix it)
blend.<program>=<off|src dst srcA dstA>

# set per render-target blending
blend.<program>.<0-7>=<off|src dst srcA dstA>
```

### Issue tracker of Optifine

- [Optifine feature request](https://github.com/sp614x/optifine/issues/5263)
- [Blend modes](https://www.khronos.org/opengl/wiki/Blending)

## Required mods

- **IMPORTANT!** [OptiFine 1.16.5_HD_U_G7_pre5](https://optifine.net/downloads) 
- [Optifabric](https://minecraft.curseforge.com/projects/optifabric) with **Fabric**

## Setup

1. Download package from [here](https://github.com/helixd2s/OptifineBlendMod/releases), and unzip archive
2. Put `optifine-blend-mod-<version>` into `mods/` directory, or use MultiMC.
3. Install [Optifabric](https://minecraft.curseforge.com/projects/optifabric) and [OptiFine](https://optifine.net/downloads) as `mods/`. 

## License

TODO...
