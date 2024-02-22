package com.smashingmods.smoothstep;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = SmoothStep.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingEquipmentChange(@Nonnull LivingEquipmentChangeEvent pEvent) {

        if (Config.enableSmoothStep.get() && Config.requireEnchantment.get()) {
            AttributeModifier SMOOTH_STEP_MODIFIER = new AttributeModifier(UUID.fromString("47f526d8-ddb3-4bd3-b599-b98d90a450c0"),
                    "Smooth Step Enchantment Modifier",
                    Config.maxStepHeight.get(),
                    AttributeModifier.Operation.ADDITION);

            if (pEvent.getSlot() == EquipmentSlot.FEET) {
                LivingEntity entity = pEvent.getEntity();

                float enchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Registry.SMOOTH_STEP.get(), entity);
                AttributeInstance attribute = Objects.requireNonNull(entity.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get()));

                    if (enchantmentLevel > 0 && !attribute.hasModifier(SMOOTH_STEP_MODIFIER)) {
                        attribute.addTransientModifier(SMOOTH_STEP_MODIFIER);
                    } else if (enchantmentLevel <= 0) {
                        attribute.removeModifier(SMOOTH_STEP_MODIFIER);
                    }
            }
        }
    }
}

