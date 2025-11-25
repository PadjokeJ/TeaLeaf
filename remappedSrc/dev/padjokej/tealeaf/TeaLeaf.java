package dev.padjokej.tealeaf;

import dev.padjokej.tealeaf.block.ModBlocks;
import dev.padjokej.tealeaf.entity.ModBlockEntities;
import dev.padjokej.tealeaf.item.ModItems;
import dev.padjokej.tealeaf.registry.EffectRegistry;
import dev.padjokej.tealeaf.registry.SoundRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeaLeaf implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tealeaf";
	public static final Logger LOGGER = LoggerFactory.getLogger("tealeaf");

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		EffectRegistry.registerAll();
		SoundRegistry.registerAll();

		LOGGER.info("TeaLeaf initiated B)");
	}
	public static Identifier id(String value) {
		return new Identifier(MOD_ID, value);
	}
}
