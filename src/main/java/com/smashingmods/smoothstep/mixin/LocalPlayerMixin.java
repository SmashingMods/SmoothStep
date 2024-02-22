package com.smashingmods.smoothstep.mixin;

import com.smashingmods.smoothstep.Config;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    @Shadow public abstract boolean isCrouching();
    private static float baseStepHeight = -1.0f;

    @SuppressWarnings("deprecation")
    @Inject(method = "move", at = @At(value = "HEAD"))
    private void enableSmoothStep(MoverType pMoverType, Vec3 pPos, CallbackInfo pCallbackInfo) {
        Entity player = ((Entity) (Object) this);

        if (Config.enableSmoothStep.get() && !Config.requireEnchantment.get() && (!this.isCrouching() || (this.isCrouching() && Config.enableWhileCrouching.get()))) {
            if (baseStepHeight < 0) {
                baseStepHeight = player.getStepHeight();
            }
            player.maxUpStep = Config.maxStepHeight.get().floatValue();
        } else if (baseStepHeight >= 0) {
            player.maxUpStep = baseStepHeight;
        }
    }

    @Inject(method = "canAutoJump", at = @At(value = "HEAD"), cancellable = true)
    private void disableAutoJump(CallbackInfoReturnable<Boolean> pCallbackInfoReturnable) {

        if (Config.enableSmoothStep.get() && (!this.isCrouching() || (this.isCrouching() && Config.enableWhileCrouching.get()))) {
            pCallbackInfoReturnable.setReturnValue(false);
            pCallbackInfoReturnable.cancel();
        }
    }
}
