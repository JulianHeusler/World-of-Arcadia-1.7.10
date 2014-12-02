package com.julian001.arcadia.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import com.julian001.arcadia.blocks.BlocksArcadia;

import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneratorArcadia implements IWorldGenerator {

	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 0: GenerateOverworld(random, chunkX * 16, chunkZ * 16, world); break;
            case 1: GenerateEnd(random, chunkX * 16, chunkZ * 16, world); break;
            case -1: GenerateNether(random, chunkX * 16, chunkZ * 16, world); break;
        }
    }

	private void GenerateOverworld(Random random, int x, int z, World world) {
		this.addOreSpawn(BlocksArcadia.oreArcadia, world, random, x, z, 2, 4, 3, 0, 40);
	}
	
	private void GenerateNether(Random random, int x, int z, World world) {
	}

	private void GenerateEnd(Random random, int x, int z, World world) {
	}
	
	 /**
    *
    * This method adds our block to the world.
    * It randomizes the coordinates, and does that as many times, as defined in spawnChance.
    * Then it gives all the params to WorldGenMinable, which handles the replacing of the ores for us.
    *
    * @param block The block you want to spawn
    * @param world The world
    * @param random The Random
    * @param blockXPos the blockXpos of a chunk
    * @param blockZPos the blockZpos of a chunk
    * @param minVeinSize min vein
    * @param maxVeinSize max vein
    * @param chancesToSpawn the chance to spawn. Usually around 2
    * @param minY lowest point to spawn
    * @param maxY highest point to spawn
    */

	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chancesToSpawn, int minY, int maxY )
	{
		WorldGenMinable minable = new WorldGenMinable(block, (minVeinSize + random.nextInt(maxVeinSize - minVeinSize)), Blocks.stone);
		for(int i = 0; i < chancesToSpawn; i++) {
			int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            minable.generate(world, random, posX, posY, posZ);
		}
	}
	
	private void addOreSpawn(Block block, int metadata, World world, Random random, int blockXPos, int blockZPos, int minVeinSize, int maxVeinSize, int chanceToSpawn, int minY, int maxY)
	{
		for(int i = 0; i < chanceToSpawn; i++) {
			int posX = blockXPos + random.nextInt(16);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(16);
            (new WorldGenMinable(block, maxVeinSize, metadata, Blocks.stone)).generate(world, random, posX, posY, posZ);
		}
	}
}