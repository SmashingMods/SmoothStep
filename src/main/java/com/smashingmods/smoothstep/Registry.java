package com.smashingmods.smoothstep;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registry {

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SmoothStep.MODID);

    public static RegistryObject<Enchantment> SMOOTH_STEP = ENCHANTMENTS.register("smooth_step", SmoothStepEnchantment::new);

    public static void register(IEventBus pEventBus) {
        ENCHANTMENTS.register(pEventBus);
    }
}
