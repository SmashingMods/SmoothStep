package com.smashingmods.smoothstep;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SmoothStepEnchantment extends Enchantment {

    protected SmoothStepEnchantment() {
        super(Rarity.COMMON, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[] { EquipmentSlot.FEET });
    }

    @Override
    public int getMinCost(int pLevel) {
        return pLevel * 5;
    }

    @Override
    public int getMaxCost(int pLevel) {
        return pLevel + 10;
    }
}
