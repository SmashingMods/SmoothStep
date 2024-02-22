package com.smashingmods.smoothstep;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = SmoothStep.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue enableSmoothStep = BUILDER
            .comment("Enable Smooth Step. Set false to disable mod.")
            .comment("Enabling the mod also disabled Auto-Jump, even if you require the enchantment to be used.")
            .comment("Default: true")
            .define("enableSmoothStep", true);

    public static final ForgeConfigSpec.BooleanValue requireEnchantment = BUILDER
            .comment("Does Smooth Step require boots to be enchanted to use?")
            .comment("Default: false")
            .define("requireEnchantment", false);

    public static final ForgeConfigSpec.BooleanValue enableWhileCrouching = BUILDER
            .comment("Smooth Step is disabled by default while crouching. Enable this to also smooth step while crouched.")
            .comment("This feature doesn't work with enchantment.")
            .comment("Default: false")
            .define("enableWhileCrouching", false);

    public static final ForgeConfigSpec.DoubleValue maxStepHeight = BUILDER
            .comment("Set the maximum step height.")
            .comment("High values will let you do weird things like walk up cliff faces in one step.")
            .comment("Default: 1.25")
            .defineInRange("maxStepHeight", 1.25, 0, 10);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        final CommentedFileConfig configData = CommentedFileConfig.builder(event.getConfig().getFullPath())
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        SPEC.setConfig(configData);
    }
}
